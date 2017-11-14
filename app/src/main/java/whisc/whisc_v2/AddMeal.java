package whisc.whisc_v2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMeal extends AppCompatActivity {
    EditText editName, editDescription, editPrep_time, editCook_time, editServing_size, editDirections;
    String meal_name, meal_description, prep_time, cook_time, serving_size, meal_directions;
    Button btnAddData;
    Context ctx = this;
    SQLiteHelper mSQLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        editName = (EditText) findViewById(R.id.addMealName);
        editDescription = (EditText) findViewById(R.id.addMealDescription);
        editPrep_time = (EditText) findViewById(R.id.addPrepTime);
        editCook_time = (EditText) findViewById(R.id.addCookTime);
        editServing_size = (EditText) findViewById(R.id.addServingSize);
        editDirections = (EditText) findViewById(R.id.addMealDirections);
        btnAddData = (Button) findViewById(R.id.btnADD);

        mSQLiteHelper = new SQLiteHelper(this);



        btnAddData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newName = editName.getText().toString();
                String newDescription = editDescription.getText().toString();
                String newPrep = editPrep_time.getText().toString();
                String newCook = editCook_time.getText().toString();
                String newServing = editServing_size.getText().toString();
                String newDirections = editDirections.getText().toString();

                if (editName.length() != 0 && editDescription.length() != 0 && editPrep_time.length() != 0 &&
                        editCook_time.length() != 0 && editServing_size.length() != 0 && editDirections.length() != 0) {

                    AddData(newName, newDescription, newPrep, newCook, newServing, newDirections);

                    editName.setText("");
                    editDescription.setText("");
                    editPrep_time.setText("");
                    editCook_time.setText("");
                    editServing_size.setText("");
                    editDirections.setText("");

                    Intent intent = new Intent(AddMeal.this, MainActivity.class);
                    startActivity(intent);
//                    finish();
                } else {
                    toastMessage("You must put something in all fields!");
                }
            }
        });
    }//end of onCreate

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
                        String newServing, String newDirections) {
        boolean insertData = mSQLiteHelper.addData(newName, newDescription, newPrep, newCook, newServing,
                                                    newDirections);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }//end of AddData


//************************************BACKUP********************************************************
//    public void AddData(String newEntry) {
//        boolean insertData = mSQLiteHelper.addData(newEntry);
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

