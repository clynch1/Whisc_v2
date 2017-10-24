package whisc.whisc_v2;

/**
 * Created by Connor on 10/23/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1Account  extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1account, container, false);
        Button goToAccountSettings = rootView.findViewById(R.id.accountSettingsButton);
        goToAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountSettings.class);
                startActivity(intent);
            }
        });
        Button goToAddMeal = rootView.findViewById(R.id.addMealButton);
        goToAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMeal.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
//    public void newActivity(View view){
//        Intent startAccountSettings = new Intent(this, AccountSettings.class);
//        startActivity(startAccountSettings);
//    }
}
