package whisc.whisc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Connor on 10/23/2017.
 */

public class Tab3Matches extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3matches, container, false);
//        Button goToViewMeal = rootView.findViewById(R.id.viewMealButton);
//        goToViewMeal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ViewMeal.class);
//                startActivity(intent);
//            }
//        });
        return rootView;
    }
}

