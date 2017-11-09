package whisc.whisc_v2;

import android.content.Context;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        editName = (EditText) findViewById(R.id.addMealName);
        editDescription = (EditText) findViewById(R.id.addMealDescription);
        editPrep_time = (EditText) findViewById(R.id.addPrepTime);
        editCook_time = (EditText) findViewById(R.id.addCookTime);
        editServing_size = (EditText) findViewById(R.id.addServingSize);
        editDirections = (EditText) findViewById(R.id.addDirections);
        btnAddData = (Button) findViewById(R.id.add_meal_btn);



        btnAddData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                meal_name = editName.toString();
                meal_description = editDescription.toString();
                prep_time = editPrep_time.toString();
                cook_time = editCook_time.toString();
                serving_size = editServing_size.toString();
                meal_directions = editDirections.toString();

                SQLiteHelper DB = new SQLiteHelper(ctx);
                DB.putInformation(DB, meal_name, meal_description, prep_time, cook_time,
                        serving_size, meal_directions);

                Toast.makeText(getBaseContext(), "Meal Added", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
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
}
