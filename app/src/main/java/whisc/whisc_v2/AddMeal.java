package whisc.whisc_v2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMeal extends AppCompatActivity {
    SQLiteHelper sqliteDB;
    EditText editName, editDescription, editPrep_time, editCook_time, editServing_size, editDirections;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        sqliteDB = new SQLiteHelper(this);

        editName = (EditText) findViewById(R.id.addMealName);
        editDescription = (EditText) findViewById(R.id.addMealDescription);
        editPrep_time = (EditText) findViewById(R.id.addPrepTime);
        editCook_time = (EditText) findViewById(R.id.addCookTime);
        editServing_size = (EditText) findViewById(R.id.addServingSize);
        editDirections = (EditText) findViewById(R.id.addDirections);
        btnAddData = (Button) findViewById(R.id.addMealButton);
//        AddData();
    }
//    public void AddData (){
//        btnAddData.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        boolean isInserted = sqliteDB.insertData(editName.getText().toString(),
//                                editDescription.getText().toString(),
//                                editPrep_time.getText().toString(),
//                                editCook_time.getText().toString(),
//                                editServing_size.getText().toString(),
//                                editDirections.getText().toString());
//                        if(isInserted = true){
//                            Toast.makeText(AddMeal.this, "Recipe Added", Toast.LENGTH_LONG).show();
//                        }
//                        else{
//                            Toast.makeText(AddMeal.this, "Recipe Not Added", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//        );
//    }
}
