package whisc.whisc_v2;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ViewMeal extends AppCompatActivity {


    private static final String TAG = "EditDataActivity";

    private Button btnDownload,btnDelete;
    private TextView set_name, set_description, set_prep, set_cook, set_serving, set_directions;
    private ImageView imageMeal;

    private ListView mListView;
    SQLiteHelper mSQLiteHelper;

    private String selectedName, selectedDescription, selectedPrep, selectedCook, selectedServing, selectedDirections, selectedMealID;
    private int selectedID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meal);
        btnDownload = (Button) findViewById(R.id.btnDownloadMeal);
        btnDelete = (Button) findViewById(R.id.btnViewDel);
        set_name = (TextView) findViewById(R.id.viewMealName);
        set_description = (TextView) findViewById(R.id.viewMealDescription);
        set_prep = (TextView) findViewById(R.id.viewPrepTime);
        set_cook = (TextView) findViewById(R.id.viewCookTime);
        set_serving = (TextView) findViewById(R.id.viewServingSize);
        set_directions = (TextView) findViewById(R.id.viewMealDirections);
        imageMeal = (ImageView) findViewById(R.id.viewImageMeal);
        mSQLiteHelper = new SQLiteHelper(this);


        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedDescription = receivedIntent.getStringExtra("description");
        selectedPrep = receivedIntent.getStringExtra("prep");
        selectedCook = receivedIntent.getStringExtra("cook");
        selectedServing = receivedIntent.getStringExtra("serving");
        selectedDirections = receivedIntent.getStringExtra("directions");




        //set the text to show the current selected name
//        set_name.setText("Name");
//        set_description.setText("d");
//        set_prep.setText("p");
//        set_cook.setText("c");
//        set_serving.setText("s");
//        set_directions.setText("d");
        set_name.setText(selectedName);
        set_description.setText(selectedDescription);
        set_prep.setText(selectedPrep);
        set_cook.setText(selectedCook);
        set_serving.setText(selectedServing);
        set_directions.setText(selectedDirections);
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        mListView = (ListView) findViewById(R.id.listIngredients);
        final String mealId = Integer.toString(selectedID);
        selectedMealID = mealId;
        populateListView(mealId);

        Cursor data= mSQLiteHelper.getMealImg(selectedMealID);
        data.moveToFirst();
        byte[] mealImage = data.getBlob(0);

        Bitmap bitmap = BitmapFactory.decodeByteArray(mealImage, 0, mealImage.length);
        imageMeal.setImageBitmap(bitmap);


//        btnDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editable_name.getText().toString();
//                String description = editable_description.getText().toString();
//                String prep = editable_prep.getText().toString();
//                String cook = editable_cook.getText().toString();
//                String serving = editable_serving.getText().toString();
//                String directions = editable_directions.getText().toString();
//                if(!name.equals("")){
//                    mSQLiteHelper.updateMeal(selectedID,name,selectedName,
//                            description, selectedDescription,
//                            prep, selectedPrep,
//                            cook, selectedCook,
//                            serving, selectedServing,
//                            directions, selectedDirections,
//                            imageViewToByte(imageMeal), oldMealImg);
//                    imageMeal.setImageResource(R.mipmap.ic_camera);
//                    Intent intent = new Intent(EditMeal.this, MainActivity.class);
//                    startActivity(intent);
//                }else{
//                    toastMessage("You must enter a name");
//                }
//            }
//        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSQLiteHelper.deleteMeal(selectedID,selectedName);
//                editable_name.setText("");
//                toastMessage("removed from database");
//                Intent intent = new Intent(EditMeal.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }//end of on create

    private void populateListView(final String mealID) {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
//        Cursor data = mSQLiteHelper.getMealIngredients(mealID);
//        ArrayList<String> listData = new ArrayList<>();
//        while(data.moveToNext()){
//            //get the value from the database in column 1
//            //then add it to the ArrayList
//            listData.add(data.getString(3));
//        }
        //create the list adapter and set the adapter
//        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        mListView.setAdapter(adapter);

        CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);

        //set an onItemClickListener to the ListView
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String name = adapterView.getItemAtPosition(i).toString();
//                Log.d(TAG, "onItemClick: You Clicked on " + name);
//
//                Cursor data = mSQLiteHelper.getIngredientID(mealID, name); //get the id associated with that name
//                int itemID = -1;
//                while(data.moveToNext()){
//                    itemID = data.getInt(0);
//                }
//                if(itemID > -1){
//                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
//                    Intent editScreenIntent = new Intent(EditMeal.this, EditIngredient.class);
//
//                    Cursor dba = mSQLiteHelper.getIngredientData();
//                    dba.moveToPosition(itemID - 1);
//
//                    String ingredientAmount = dba.getString(2);
//                    String ingredientName = dba.getString(3);
//
//                    editScreenIntent.putExtra("id",itemID);
//                    editScreenIntent.putExtra("amount",ingredientAmount);
//                    editScreenIntent.putExtra("name",ingredientName);
//
//                    String p_name = editable_name.getText().toString();
//                    String p_description = editable_description.getText().toString();
//                    String p_prep = editable_prep.getText().toString();
//                    String p_cook = editable_cook.getText().toString();
//                    String p_serving = editable_serving.getText().toString();
//                    String p_directions = editable_directions.getText().toString();
//
//                    editScreenIntent.putExtra("editName",p_name.toString());
//                    editScreenIntent.putExtra("editDescription",p_description.toString());
//                    editScreenIntent.putExtra("editPrep",p_prep.toString());
//                    editScreenIntent.putExtra("editCook",p_cook.toString());
//                    editScreenIntent.putExtra("editServing",p_serving.toString());
//                    editScreenIntent.putExtra("editDirection",p_directions.toString());
//                    editScreenIntent.putExtra("add_type", "edit");
//                    editScreenIntent.putExtra("meal_id", selectedMealID);
//
//                    editScreenIntent.putExtra("table_type","table");
//
//
//                    startActivity(editScreenIntent);
//                }
//                else{
//                    toastMessage("No ID associated with that name");
//                }
//            }
//        });
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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