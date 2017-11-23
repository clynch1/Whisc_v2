package whisc.whisc_v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * Created by Connor on 10/23/2017.
 */

public class Tab3Matches extends Fragment {
    private static final String TAG = "Tab1Activity";

    SQLiteHelper mSQLiteHelper;

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3matches, container, false);

        mListView = (ListView) rootView.findViewById(R.id.matches_list_view);
        mSQLiteHelper = new SQLiteHelper(getActivity());

        populateListView();

        return rootView;
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor matchesData = mSQLiteHelper.getMatchesData();
        Cursor data = mSQLiteHelper.getMealData();
        ArrayList<String> listData = new ArrayList<>();
        int dataMealIdInt = 0;
        while(matchesData.moveToNext()){
            dataMealIdInt = Integer.parseInt(matchesData.getString(1));
            data.moveToPosition(dataMealIdInt - 1);    //the meal id is stored one off so to get the correct value must subtract 1
            listData.add(data.getString(1));
        }//end of while
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
                    Intent editScreenIntent = new Intent(getActivity(), ViewMeal.class);

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

