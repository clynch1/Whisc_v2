package whisc.whisc_v2;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;


public class EditMeal extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete, btnAddIngredients, btnAddImg;
    private EditText editable_name, editable_description, editable_prep, editable_cook, editable_serving, editable_directions;
    private ImageView imageMeal;

    private ListView mListView;
    SQLiteHelper mSQLiteHelper;

    private String selectedName, selectedDescription, selectedPrep, selectedCook, selectedServing, selectedDirections, selectedMealID;
    private int selectedID;

    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);
        btnSave = (Button) findViewById(R.id.btnEditSave);
        btnDelete = (Button) findViewById(R.id.btnEditDel);
        btnAddIngredients = (Button) findViewById(R.id.btnEditAddIngredient);
        btnAddImg = (Button)findViewById(R.id.btnAddImg);
        editable_name = (EditText) findViewById(R.id.editMealName);
        editable_description = (EditText) findViewById(R.id.editMealDescription);
        editable_prep = (EditText) findViewById(R.id.editPrepTime);
        editable_cook = (EditText) findViewById(R.id.editCookTime);
        editable_serving = (EditText) findViewById(R.id.editServingSize);
        editable_directions = (EditText) findViewById(R.id.editMealDirections);
        imageMeal = (ImageView) findViewById(R.id.imageMeal);
        mSQLiteHelper = new SQLiteHelper(this);


        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra

                                                                                        //        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value
                                                                                        //
                                                                                        //        mListView = (ListView) findViewById(R.id.listIngredients);
                                                                                        //        final String mealId = Integer.toString(selectedID);
                                                                                        //        populateListView(mealId);

//***********************MAY ADD BACK***************************************************************
//        Cursor dba = mSQLiteHelper.getMealData();
//        dba.moveToPosition(selectedID - 1);
//        toastMessage("ID: " + selectedID);
//
//
//        selectedName = dba.getString(1);
//        selectedDescription = dba.getString(2);
//        selectedPrep = dba.getString(3);
//        selectedCook = dba.getString(4);
//        selectedServing = dba.getString(5);
//        selectedDirections = dba.getString(6);
//**************************************************************************************************





        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedDescription = receivedIntent.getStringExtra("description");
        selectedPrep = receivedIntent.getStringExtra("prep");
        selectedCook = receivedIntent.getStringExtra("cook");
        selectedServing = receivedIntent.getStringExtra("serving");
        selectedDirections = receivedIntent.getStringExtra("directions");



        //set the text to show the current selected name
        String type = receivedIntent.getStringExtra("start");

        switch (type){
            case "i":
                editable_name.setText(receivedIntent.getStringExtra("editName"));
                editable_description.setText(receivedIntent.getStringExtra("editDescription"));
                editable_prep.setText(receivedIntent.getStringExtra("editPrep"));
                editable_cook.setText(receivedIntent.getStringExtra("editCook"));
                editable_serving.setText(receivedIntent.getStringExtra("editServing"));
                editable_directions.setText(receivedIntent.getStringExtra("editDirection"));
                selectedID = receivedIntent.getIntExtra("id",-1);
                break;
            case "start":
                editable_name.setText(selectedName);
                editable_description.setText(selectedDescription);
                editable_prep.setText(selectedPrep);
                editable_cook.setText(selectedCook);
                editable_serving.setText(selectedServing);
                editable_directions.setText(selectedDirections);
                selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value
                break;
        }//end of switch

        mListView = (ListView) findViewById(R.id.listIngredients);
        final String mealId = Integer.toString(selectedID);
        selectedMealID = mealId;
        populateListView(mealId);

        imageMeal.setImageResource(R.mipmap.ic_camera);
        Cursor data= mSQLiteHelper.getMealImg(selectedMealID);
        byte[] mealImage = imageViewToByte(imageMeal);
        while(data.moveToNext()){
            mealImage = data.getBlob(0);
        }

        final byte[] oldMealImg = mealImage;

        Bitmap bitmap = BitmapFactory.decodeByteArray(mealImage, 0, mealImage.length);
        imageMeal.setImageBitmap(bitmap);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editable_name.getText().toString();
                String description = editable_description.getText().toString();
                String prep = editable_prep.getText().toString();
                String cook = editable_cook.getText().toString();
                String serving = editable_serving.getText().toString();
                String directions = editable_directions.getText().toString();
                if(!name.equals("")){
                    mSQLiteHelper.updateMeal(selectedID,name,selectedName,
                            description, selectedDescription,
                            prep, selectedPrep,
                            cook, selectedCook,
                            serving, selectedServing,
                            directions, selectedDirections,
                            imageViewToByte(imageMeal), oldMealImg);
                    imageMeal.setImageResource(R.mipmap.ic_camera);
                    Intent intent = new Intent(EditMeal.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSQLiteHelper.deleteMeal(selectedID,selectedName);
                editable_name.setText("");
                toastMessage("removed from database");
                Intent intent = new Intent(EditMeal.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAddIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editable_name.getText().toString();
                String description = editable_description.getText().toString();
                String prep = editable_prep.getText().toString();
                String cook = editable_cook.getText().toString();
                String serving = editable_serving.getText().toString();
                String directions = editable_directions.getText().toString();

                Intent intent = new Intent(EditMeal.this, AddIngredient.class);
                    intent.putExtra("editName",name.toString());
                    intent.putExtra("editDescription",description.toString());
                    intent.putExtra("editPrep",prep.toString());
                    intent.putExtra("editCook",cook.toString());
                    intent.putExtra("editServing",serving.toString());
                    intent.putExtra("editDirection",directions.toString());
                    intent.putExtra("add_type", "edit");
                    intent.putExtra("meal_id", mealId);
                startActivity(intent);
            }
        });


        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(EditMeal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        });
    }

    private void populateListView(final String mealID) {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mSQLiteHelper.getMealIngredients(mealID);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(3));
        }
        //create the list adapter and set the adapter
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        mListView.setAdapter(adapter);

        CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mSQLiteHelper.getIngredientID(mealID, name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(EditMeal.this, EditIngredient.class);

                    Cursor dba = mSQLiteHelper.getIngredientData();
                    dba.moveToPosition(itemID - 1);

                    String ingredientAmount = dba.getString(2);
                    String ingredientName = dba.getString(3);

                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("amount",ingredientAmount);
                    editScreenIntent.putExtra("name",ingredientName);

                    String p_name = editable_name.getText().toString();
                    String p_description = editable_description.getText().toString();
                    String p_prep = editable_prep.getText().toString();
                    String p_cook = editable_cook.getText().toString();
                    String p_serving = editable_serving.getText().toString();
                    String p_directions = editable_directions.getText().toString();

                    editScreenIntent.putExtra("editName",p_name.toString());
                    editScreenIntent.putExtra("editDescription",p_description.toString());
                    editScreenIntent.putExtra("editPrep",p_prep.toString());
                    editScreenIntent.putExtra("editCook",p_cook.toString());
                    editScreenIntent.putExtra("editServing",p_serving.toString());
                    editScreenIntent.putExtra("editDirection",p_directions.toString());
                    editScreenIntent.putExtra("add_type", "edit");
                    editScreenIntent.putExtra("meal_id", selectedMealID);

                    editScreenIntent.putExtra("table_type","table");


                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int i =0;
        if(requestCode == REQUEST_CODE_GALLERY){
//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(i == 0){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageMeal.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            Cursor data = mSQLiteHelper.getMealIngredients(selectedMealID);
            return data.getCount();
        }

        @Override
        public String getItem(int i) {
            Cursor data = mSQLiteHelper.getMealIngredients(selectedMealID);
            data.moveToPosition(i);
            String name = data.getString(3);
            Log.d(TAG, "addMealData: get item " + name);
            return name;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.ingredient_layout, null);

            TextView amount = (TextView) view.findViewById(R.id.ingredient_layout_amount);
            TextView ingredient = (TextView) view.findViewById(R.id.ingredient_layout_ingredient);

            String newAmount;
            String newIngredient;
            Cursor data = mSQLiteHelper.getMealIngredients(selectedMealID);
            data.moveToPosition(i);
            newAmount = data.getString(2);
            newIngredient = data.getString(3);

            amount.setText(newAmount);
            ingredient.setText(newIngredient);

            return view;
        }
    }//end of CustomAdapter

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}





//************************************BACKUP********************************************************
//
//public class EditMeal extends AppCompatActivity {
//
//    private static final String TAG = "EditDataActivity";
//
//    private Button btnSave,btnDelete;
//    private EditText editable_item, editable_description, editable_prep, editable_cook, editable_serving, editable_directions;
//
//    SQLiteHelper mSQLiteHelper;
//
//    private String selectedName, selectedDescription, selectedPrep, selectedCook, selectedServing, selectedDirections;
//    private int selectedID;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_meal);
//        btnSave = (Button) findViewById(R.id.btnSave);
//        btnDelete = (Button) findViewById(R.id.btnDelete);
//        editable_item = (EditText) findViewById(R.id.editable_item);
//        editable_description = (EditText) findViewById(R.id.editMealDescription);
//        editable_prep = (EditText) findViewById(R.id.editPrepTime);
//        editable_cook = (EditText) findViewById(R.id.editCookTime);
//        editable_serving = (EditText) findViewById(R.id.editServingSize);
//        editable_directions = (EditText) findViewById(R.id.editMealDirections);
//        mSQLiteHelper = new SQLiteHelper(this);
//
//        //get the intent extra from the ListDataActivity
//        Intent receivedIntent = getIntent();
//
//        //now get the itemID we passed as an extra
//        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value
//
//        //now get the name we passed as an extra
//        selectedName = receivedIntent.getStringExtra("name");
//        selectedDescription = receivedIntent.getStringExtra("meal_description");
//        selectedPrep = receivedIntent.getStringExtra("prep_time");
//        selectedCook = receivedIntent.getStringExtra("cook_time");
//        selectedServing = receivedIntent.getStringExtra("serving_size");
//        selectedDirections = receivedIntent.getStringExtra("meal_directions");
//
//
//        //set the text to show the current selected name
//        editable_item.setText(selectedName);
//        editable_description.setText(selectedDescription);
//        editable_prep.setText(selectedPrep);
//        editable_cook.setText(selectedCook);
//        editable_serving.setText(selectedServing);
//        editable_directions.setText(selectedDirections);
//
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String item = editable_item.getText().toString();
//                if(!item.equals("")){
//                    mSQLiteHelper.updateName(selectedID,item,selectedName,
//                            null, null,
//                            null, null,
//                            null, null,
//                            null, null,
//                            null, null);
//                    Intent intent = new Intent(EditMeal.this, MainActivity.class);
//                    startActivity(intent);
//                }else{
//                    toastMessage("You must enter a name");
//                }
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSQLiteHelper.deleteMeal(selectedID,selectedName);
//                editable_item.setText("");
//                toastMessage("removed from database");
//                Intent intent = new Intent(EditMeal.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    /**
//     * customizable toast
//     * @param message
//     */
//    private void toastMessage(String message){
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
//    }
//}