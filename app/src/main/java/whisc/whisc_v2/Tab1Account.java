package whisc.whisc_v2;

/**
 * Created by Connor on 10/23/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tab1Account  extends Fragment {
    Context CTX = getActivity();
    private static final String TAG = "Tab1Activity";

    SQLiteHelper mSQLiteHelper;

    private ListView mListView;

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
                    intent.putExtra("start","start");
                startActivity(intent);
            }
        });

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_data_layout);

        mListView = (ListView) rootView.findViewById(R.id.listView);
        mSQLiteHelper = new SQLiteHelper(getActivity());

        populateListView();

        return rootView;
    }


    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mSQLiteHelper.getMealData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mSQLiteHelper.getMealID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(getActivity(), EditMeal.class);

                    Cursor dba = mSQLiteHelper.getMealData();
                    dba.moveToPosition(itemID - 1);


                    String meaName = dba.getString(1);
                    String Description = dba.getString(2);
                    String Prep = dba.getString(3);
                    String Cook = dba.getString(4);
                    String Serving = dba.getString(5);
                    String Directions = dba.getString(6);

                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",meaName);
                    editScreenIntent.putExtra("description",Description);
                    editScreenIntent.putExtra("prep",Prep);
                    editScreenIntent.putExtra("cook",Cook);
                    editScreenIntent.putExtra("serving",Serving);
                    editScreenIntent.putExtra("directions",Directions);
                    editScreenIntent.putExtra("start","start");


                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }//end of toastMessage
}
