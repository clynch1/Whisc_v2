package whisc.whisc_v2;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class EditMeal extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item, editable_description, editable_prep, editable_cook, editable_serving, editable_directions;

    SQLiteHelper mSQLiteHelper;

    private String selectedName, selectedDescription, selectedPrep, selectedCook, selectedServing, selectedDirections;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        editable_description = (EditText) findViewById(R.id.editMealDescription);
        editable_prep = (EditText) findViewById(R.id.editPrepTime);
        editable_cook = (EditText) findViewById(R.id.editCookTime);
        editable_serving = (EditText) findViewById(R.id.editServingSize);
        editable_directions = (EditText) findViewById(R.id.editMealDirections);
        mSQLiteHelper = new SQLiteHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

//***********************MAY ADD BACK***************************************************************
//        Cursor dba = mSQLiteHelper.getData();
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
        editable_item.setText(selectedName);
        editable_description.setText(selectedDescription);
        editable_prep.setText(selectedPrep);
        editable_cook.setText(selectedCook);
        editable_serving.setText(selectedServing);
        editable_directions.setText(selectedDirections);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editable_item.getText().toString();
                String description = editable_description.getText().toString();
                String prep = editable_prep.getText().toString();
                String cook = editable_cook.getText().toString();
                String serving = editable_serving.getText().toString();
                String directions = editable_directions.getText().toString();
                if(!name.equals("")){
                    mSQLiteHelper.updateName(selectedID,name,selectedName,
                            description, selectedDescription,
                            prep, selectedPrep,
                            cook, selectedCook,
                            serving, selectedServing,
                            directions, selectedDirections);
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
                mSQLiteHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("removed from database");
                Intent intent = new Intent(EditMeal.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

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
//                mSQLiteHelper.deleteName(selectedID,selectedName);
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