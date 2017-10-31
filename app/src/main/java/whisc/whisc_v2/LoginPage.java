package whisc.whisc_v2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {


    Button GoToHomeScreen;
    Button GoToCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        GoToHomeScreen = (Button)findViewById(R.id.button1);
        GoToCreateAccount = (Button)findViewById(R.id.textView4);


        GoToHomeScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Intent code for open new activity through intent.

                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);

            }
        });

        GoToCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Intent code for open new activity through intent.

                Intent intent = new Intent(LoginPage.this, CreateAccount.class);
                startActivity(intent);

            }
        });
    }
}
