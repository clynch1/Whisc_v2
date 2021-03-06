package whisc.whisc_v2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import java.util.ArrayList;

public class AddMeal extends AppCompatActivity {
    EditText editName, editDescription, editPrep_time, editCook_time, editServing_size, editDirections, editMeat_Type;
    String meal_name, meal_description, prep_time, cook_time, serving_size, meal_directions;
    ImageView imageMeal;
    Button btnAddIngredient, btnAddData, btnAddImg;
    Context ctx = this;
    private static final String TAG = "AddMeal";
    private ListView mListView;
    SQLiteHelper mSQLiteHelper;
    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        editName = (EditText) findViewById(R.id.addMealName);
        editDescription = (EditText) findViewById(R.id.addMealDescription);
        editPrep_time = (EditText) findViewById(R.id.addPrepTime);
        editCook_time = (EditText) findViewById(R.id.addCookTime);
        editServing_size = (EditText) findViewById(R.id.addServingSize);
        editMeat_Type = (EditText) findViewById(R.id.addMeatType);
        editDirections = (EditText) findViewById(R.id.addMealDirections);
        btnAddIngredient = (Button) findViewById(R.id.btnAddIngredient);
        btnAddData = (Button) findViewById(R.id.btnADD);
        btnAddImg = (Button) findViewById(R.id.btnAddImg);
        imageMeal = (ImageView) findViewById(R.id.imageMeal);

        Intent receivedIntent = getIntent();
            String type = receivedIntent.getStringExtra("start");

        switch (type){
            case "i":
                editName.setText(receivedIntent.getStringExtra("editName"));
                editDescription.setText(receivedIntent.getStringExtra("editDescription"));
                editPrep_time.setText(receivedIntent.getStringExtra("editPrep"));
                editCook_time.setText(receivedIntent.getStringExtra("editCook"));
                editServing_size.setText(receivedIntent.getStringExtra("editServing"));
                editMeat_Type.setText(receivedIntent.getStringExtra("editMeat"));
                editDirections.setText(receivedIntent.getStringExtra("editDirection"));
                break;
        }//end of switch

        mSQLiteHelper = new SQLiteHelper(this);
        mListView = (ListView) findViewById(R.id.listIngredients);
        populateListView();

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(AddMeal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        });

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editName = (EditText) findViewById(R.id.addMealName);
                editDescription = (EditText) findViewById(R.id.addMealDescription);
                editPrep_time = (EditText) findViewById(R.id.addPrepTime);
                editCook_time = (EditText) findViewById(R.id.addCookTime);
                editServing_size = (EditText) findViewById(R.id.addServingSize);
                editMeat_Type = (EditText) findViewById(R.id.addMeatType);
                editDirections = (EditText) findViewById(R.id.addMealDirections);

                Intent intent = new Intent(AddMeal.this, AddIngredient.class);
                    intent.putExtra("editName",editName.getText().toString());
                    intent.putExtra("editDescription",editDescription.getText().toString());
                    intent.putExtra("editPrep",editPrep_time.getText().toString());
                    intent.putExtra("editCook",editCook_time.getText().toString());
                    intent.putExtra("editServing",editServing_size.getText().toString());
                    intent.putExtra("editMeat",editMeat_Type.getText().toString());
                    intent.putExtra("editDirection",editDirections.getText().toString());
                    intent.putExtra("add_type","add");
                startActivity(intent);
            }
        });


        btnAddData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newName = editName.getText().toString();
                String newDescription = editDescription.getText().toString();
                String newPrep = editPrep_time.getText().toString();
                String newCook = editCook_time.getText().toString();
                String newServing = editServing_size.getText().toString();
                String newMeatType = editMeat_Type.getText().toString();
                String newDirections = editDirections.getText().toString();


                if (editName.length() != 0 && editDescription.length() != 0 && editPrep_time.length() != 0 &&
                        editCook_time.length() != 0 && editServing_size.length() != 0 && editMeat_Type.length() != 0 && editDirections.length() != 0) {

                    AddData(newName, newDescription, newPrep, newCook, newServing, newDirections, imageViewToByte(imageMeal), newMeatType);
                    Cursor idData = mSQLiteHelper.getMealID(newName);
                    int mealID_int = -1;
                    while(idData.moveToNext()){
                        mealID_int = idData.getInt(0);
                    }
                    String mealID = Integer.toString(mealID_int);
                    addIngredients(mealID);

                    editName.setText("");
                    editDescription.setText("");
                    editPrep_time.setText("");
                    editCook_time.setText("");
                    editServing_size.setText("");
                    editDirections.setText("");
                    imageMeal.setImageResource(R.mipmap.ic_camera);

                    Intent intent = new Intent(AddMeal.this, MainActivity.class);
                    startActivity(intent);
//                    finish();
                } else {
                    toastMessage("You must put something in all fields!");
                }
            }
        });
    }//end of onCreate


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
//******************************************BACKUP**************************************************
//    @Override
//    public void onClick(View v) {
//        String newEntry = editName.getText().toString();
//        if (editName.length() != 0) {
//            AddData(newEntry);
//            editName.setText("");
//            Intent intent = new Intent(AddMeal.this, MainActivity.class);
//            startActivity(intent);
////                    finish();
//        } else {
//            toastMessage("You must put something in the text field!");
//        }
//    }
//});
//        }//end of onCreate

// **************************OLD********************************************************************
//
//                meal_name = editName.toString();
//                meal_description = editDescription.toString();
//                prep_time = editPrep_time.toString();
//                cook_time = editCook_time.toString();
//                serving_size = editServing_size.toString();
//                meal_directions = editDirections.toString();
//
//                SQLiteHelper DB = new SQLiteHelper(ctx);
//                DB.putInformation(DB, meal_name, meal_description, prep_time, cook_time,
//                        serving_size, meal_directions);
//
//                Toast.makeText(getBaseContext(), "Meal Added", Toast.LENGTH_LONG).show();
//                finish();

    public void AddData(String newName, String newDescription, String newPrep, String newCook,
                        String newServing, String newDirections, byte[] meal_image, String meatType) {
        boolean insertData = mSQLiteHelper.addMealData(newName, newDescription, newPrep, newCook, newServing,
                newDirections, meal_image, meatType);

        if (insertData) {
            toastMessage(newName + " Added!");
        } else {
            toastMessage("Something went wrong");
        }
    }//end of AddData

    public void addIngredients(String i_mealID){
        Cursor data = mSQLiteHelper.getIngredientHolderData();
        while(data.moveToNext()){
            String i_amount = data.getString(1);
            String i_name = data.getString(2);

            mSQLiteHelper.addIngredientsData(i_mealID, i_amount, i_name);
        }//end of while
        mSQLiteHelper.dropHolderTable();
    }//end of addIngredients

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
//        Cursor data = mSQLiteHelper.getIngredientHolderData();
//        ArrayList<String> listData = new ArrayList<>();
//        while(data.moveToNext()){
//            //get the value from the database in column 1
//            //then add it to the ArrayList
//            listData.add(data.getString(2));
//        }
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

                Cursor data = mSQLiteHelper.getHolderIngredientID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(AddMeal.this, EditIngredient.class);

                    Cursor dba = mSQLiteHelper.getIngredientHolderData();
                    dba.moveToPosition(itemID - 1);

                    String ingredientAmount = dba.getString(1);
                    String ingredientName = dba.getString(2);

                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("amount",ingredientAmount);
                    editScreenIntent.putExtra("name",ingredientName);

                    String p_name = editName.getText().toString();
                    String p_description = editDescription.getText().toString();
                    String p_prep = editPrep_time.getText().toString();
                    String p_cook = editCook_time.getText().toString();
                    String p_serving = editServing_size.getText().toString();
                    String p_directions = editDirections.getText().toString();

                    editScreenIntent.putExtra("editName",p_name.toString());
                    editScreenIntent.putExtra("editDescription",p_description.toString());
                    editScreenIntent.putExtra("editPrep",p_prep.toString());
                    editScreenIntent.putExtra("editCook",p_cook.toString());
                    editScreenIntent.putExtra("editServing",p_serving.toString());
                    editScreenIntent.putExtra("editDirection",p_directions.toString());
                    editScreenIntent.putExtra("add_type", "edit");
                    editScreenIntent.putExtra("meal_id", "");

                    editScreenIntent.putExtra("table_type","holder");

                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            Cursor data = mSQLiteHelper.getIngredientHolderData();
            return data.getCount();
        }

        @Override
        public String getItem(int i) {
            Cursor data = mSQLiteHelper.getIngredientHolderData();
            data.moveToPosition(i);
            String name = data.getString(2);
            Log.d(TAG, "addMealData: get item " + name);
            return name;
        }

        @Override
        public long getItemId(int i) {
            Log.d(TAG, "addMealData: get item id " + i);
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.ingredient_layout, null);

            TextView amount = (TextView) view.findViewById(R.id.ingredient_layout_amount);
            TextView ingredient = (TextView) view.findViewById(R.id.ingredient_layout_ingredient);

            String newAmount;
            String newIngredient;
            Cursor data = mSQLiteHelper.getIngredientHolderData();
            data.moveToPosition(i);
            newAmount = data.getString(1);
            newIngredient = data.getString(2);

            amount.setText(newAmount);
            ingredient.setText(newIngredient);

            return view;
        }
    }//end of CustomAdapter


//************************************BACKUP********************************************************
//    public void AddData(String newEntry) {
//        boolean insertData = mSQLiteHelper.addMealData(newEntry);
//
//        if (insertData) {
//            toastMessage("Data Successfully Inserted!");
//        } else {
//            toastMessage("Something went wrong");
//        }
//    }//end of AddData


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }//end of toastMessage
}//end of main






//**************************************OLD*********************************************************


//    public void AddData (){
//        btnAddData.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////        btnAddData.setOnClickListener(
////                new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
//
//
//               //new
//                        meal_name = editName.toString();
//                        meal_description = editDescription.toString();
//                        prep_time = editPrep_time.toString();
//                        cook_time = editCook_time.toString();
//                        serving_size = serving_size.toString();
//                        meal_directions = editDirections.toString();
//
//                        SQLiteHelper DB = new SQLiteHelper(ctx);
//                        DB.putInformation(DB, meal_name, meal_description, prep_time, cook_time,
//                                serving_size, meal_directions);
//
//                        Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_LONG).show();
//                        finish();
//
//
//
//                //old
////                        boolean isInserted = sqliteDB.insertData(editName.getText().toString(),
////                                editDescription.getText().toString(),
////                                editPrep_time.getText().toString(),
////                                editCook_time.getText().toString(),
////                                editServing_size.getText().toString(),
////                                editDirections.getText().toString());
////                        if(isInserted = true){
////                            Toast.makeText(AddMeal.this, "Recipe Added", Toast.LENGTH_LONG).show();
////                        }
////                        else{
////                            Toast.makeText(AddMeal.this, "Recipe Not Added", Toast.LENGTH_LONG).show();
////                        }
//                    }
//                }
//        );
//    }

