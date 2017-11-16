package whisc.whisc_v2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditIngredient extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_amount, editable_name;

    SQLiteHelper mSQLiteHelper;

    private String selectedAmount, selectedName, table_type;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);
        btnSave = (Button) findViewById(R.id.btnSaveIngredients);
        btnDelete = (Button) findViewById(R.id.btnDeleteIngredient);
        editable_amount = (EditText) findViewById(R.id.editIngredientAmount);
        editable_name = (EditText) findViewById(R.id.editIngredientName);
        mSQLiteHelper = new SQLiteHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedAmount = receivedIntent.getStringExtra("amount");
        selectedName = receivedIntent.getStringExtra("name");
        table_type = receivedIntent.getStringExtra("table_type");



        //set the text to show the current selected name
        editable_amount.setText(selectedAmount);
        editable_name.setText(selectedName);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent receivedIntent = getIntent();
                String amount = editable_amount.getText().toString();
                String name = editable_name.getText().toString();
                if(!name.equals("") && !amount.equals("")){
                    Intent intent = new Intent(EditIngredient.this, MainActivity.class);
                    switch (table_type) {
                        case "holder":
                            mSQLiteHelper.updateHolderIngredients(selectedID,amount,selectedAmount,
                                    name, selectedName);
                            intent = new Intent(EditIngredient.this, AddMeal.class);
                            break;
                        case "table":
                            mSQLiteHelper.updateIngredients(selectedID,amount,selectedAmount,
                                    name, selectedName);
                            intent = new Intent(EditIngredient.this, EditMeal.class);
                            intent.putExtra("id", Integer.parseInt(receivedIntent.getStringExtra("meal_id")));
                            break;
                    }//end of switch
                        intent.putExtra("editName",receivedIntent.getStringExtra("editName"));
                        intent.putExtra("editDescription",receivedIntent.getStringExtra("editDescription"));
                        intent.putExtra("editPrep",receivedIntent.getStringExtra("editPrep"));
                        intent.putExtra("editCook",receivedIntent.getStringExtra("editCook"));
                        intent.putExtra("editServing",receivedIntent.getStringExtra("editServing"));
                        intent.putExtra("editDirection",receivedIntent.getStringExtra("editDirection"));
                        intent.putExtra("start","i");
                    startActivity(intent);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditIngredient.this, MainActivity.class);
                switch (table_type) {
                    case "holder":
                        mSQLiteHelper.deleteHolderIngredient(selectedID,selectedName);
                        editable_name.setText("");
                        toastMessage("removed from database");
                        intent = new Intent(EditIngredient.this, AddMeal.class);
                        break;
                    case "table":
                        mSQLiteHelper.deleteIngredient(selectedID,selectedName);
                        editable_name.setText("");
                        toastMessage("removed from database");
                        intent = new Intent(EditIngredient.this, MainActivity.class);
                        break;
                }//end of switch

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