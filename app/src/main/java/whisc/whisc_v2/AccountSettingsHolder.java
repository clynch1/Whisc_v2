package whisc.whisc_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccountSettingsHolder extends AppCompatActivity {

    private Button addMeals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings_holder);
        addMeals = (Button) findViewById(R.id.accountSettingsHolder_add_demo_meals);

        addMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeals();
                toastMessage("Demo Meals Added");
            }
        });
    }//end of onCreate

    public static void addMeals(){

    }//end of addMeals

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }//end of toastMessage

}
