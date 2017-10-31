package whisc.whisc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Connor on 10/23/2017.
 */

public class Tab2Explore extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2explore, container, false);

        Button likeButton = rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You Liked This Meal", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), AccountSettings.class);
//                startActivity(intent);
            }
        });
        Button dislikeButton = rootView.findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You Disliked This Meal", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getActivity(), AccountSettings.class);
//                startActivity(intent);
            }
        });

        return rootView;
    }
}

