package whisc.whisc_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddIngredient extends AppCompatActivity {
    EditText editAmount, editName;
    Button btnADD;
    SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        editAmount = (EditText) findViewById(R.id.editIngredientAmount);
        editName = (EditText) findViewById(R.id.editIngredientName);
        btnADD = (Button) findViewById(R.id.btnAddIngredientToMeal);
        mSQLiteHelper = new SQLiteHelper(this);

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newAmount = editAmount.getText().toString();
                String newName = editName.getText().toString();

                if (editAmount.length() != 0 && editName.length() != 0) {

                    Intent intent = new Intent(AddIngredient.this, MainActivity.class);

                    Intent receivedIntent = getIntent();
                        String type = receivedIntent.getStringExtra("add_type");

                    switch (type){
                        case "edit":
                            String mealID = receivedIntent.getStringExtra("meal_id");
                            AddData(type, mealID, newAmount, newName);
                            intent = new Intent(AddIngredient.this, EditMeal.class);
                            intent.putExtra("id", Integer.parseInt(receivedIntent.getStringExtra("meal_id")));
                            break;
                        case "add":
                            AddData(type, null, newAmount, newName);
                            intent = new Intent(AddIngredient.this, AddMeal.class);
                            break;
                    }//end of switch


                    editAmount.setText("");
                    editName.setText("");

                    intent.putExtra("editName",receivedIntent.getStringExtra("editName"));
                    intent.putExtra("editDescription",receivedIntent.getStringExtra("editDescription"));
                    intent.putExtra("editPrep",receivedIntent.getStringExtra("editPrep"));
                    intent.putExtra("editCook",receivedIntent.getStringExtra("editCook"));
                    intent.putExtra("editServing",receivedIntent.getStringExtra("editServing"));
                    intent.putExtra("editMeat",receivedIntent.getStringExtra("editMeat"));
                    intent.putExtra("editDirection",receivedIntent.getStringExtra("editDirection"));
                    intent.putExtra("start","i");
                    startActivity(intent);
//                    finish();
                } else {
                    toastMessage("You must put something in all fields!");
                }
                finish();
            }
        });



    }//end of onCreate

    public void AddData(String type, String mealID, String newAmount, String newName) {
        boolean insertData = false;
        switch (type){
            case "edit":
                insertData = mSQLiteHelper.addIngredientsData(mealID, newAmount, newName);
                break;
            case "add":
                insertData = mSQLiteHelper.addHolderIngredientsData(newAmount, newName);
                break;
        }//end of switch

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }//end of AddData


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }//end of toastMessage
}
