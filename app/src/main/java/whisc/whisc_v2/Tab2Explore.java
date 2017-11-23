package whisc.whisc_v2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by Connor on 10/23/2017.
 */

public class Tab2Explore extends Fragment {
    private static final String TAG = "Tab2Activity";
    SQLiteHelper mSQLiteHelper;

    private ImageView imageMeal;
    private TextView mealName, mealDescription;
    private String selectedMealName, selectedMealDescription, mealId;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2explore, container, false);
        imageMeal = (ImageView) rootView.findViewById(R.id.explore_meal_image);
        mealName = (TextView) rootView.findViewById(R.id.explore_meal_name);
        mealDescription = (TextView) rootView.findViewById(R.id.explore_meal_description);
        mSQLiteHelper = new SQLiteHelper(getActivity());



        Cursor data = mSQLiteHelper.getMealData(); //get all the meals
        int itemID = 0;
        data.moveToPosition(itemID);

        mealId = data.getString(0);
        selectedMealName = data.getString(1);
        selectedMealDescription = data.getString(2);




        mealName.setText(selectedMealName);
        mealDescription.setText(selectedMealDescription);


//        imageMeal.setImageResource(R.mipmap.ic_camera);
        Cursor imgData = mSQLiteHelper.getMealImg(mealId);
        imgData.moveToFirst();
        byte[] mealImage = imgData.getBlob(0);

//        while(imgData.moveToNext()){
//            mealImage = data.getBlob(0);
//        }//end of while


        Bitmap bitmap = BitmapFactory.decodeByteArray(mealImage, 0, mealImage.length);
        imageMeal.setImageBitmap(bitmap);

        Button likeButton = rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = mSQLiteHelper.getMealData();
                mSQLiteHelper.addMatchesData(mealId);
                Toast.makeText(getActivity(), "You Liked " + mealName.getText(), Toast.LENGTH_LONG).show();
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

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}

