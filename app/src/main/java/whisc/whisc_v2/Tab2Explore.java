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
import java.util.LinkedList;
import java.util.Random;

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
    public int displayedLength, likedLength, mealIdInt;
    private boolean isValidMeal;
    LinkedList<Integer> displayedMealIds;
    LinkedList<Integer> likedMealIds;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2explore, container, false);
//        imageMeal = (ImageView) rootView.findViewById(R.id.explore_meal_image);
//        mealName = (TextView) rootView.findViewById(R.id.explore_meal_name);
//        mealDescription = (TextView) rootView.findViewById(R.id.explore_meal_description);
        mSQLiteHelper = new SQLiteHelper(getActivity());

        displayedMealIds = setDisplayedMealIds(mSQLiteHelper);
        likedMealIds = setLikedMealIds(mSQLiteHelper);

        isValidMeal = true;
        setMeal(mSQLiteHelper);

        Button likeButton = rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMeal){
                    displayedMealIds.push(mealIdInt);
                    displayedLength ++;
                    likedMealIds.push(mealIdInt);
                    likedLength ++;
                    mSQLiteHelper.addDisplayedData(mealIdInt);
                    mSQLiteHelper.addLikedData(mealIdInt);
                    Toast.makeText(getActivity(), "You Liked " + mealName.getText(), Toast.LENGTH_SHORT).show();
                    nextMeal(mSQLiteHelper);
                }//end of id

//                Intent intent = new Intent(getActivity(), AccountSettings.class);
//                startActivity(intent);
            }
        });
        Button dislikeButton = rootView.findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMeal){
                    displayedMealIds.push(mealIdInt);
                    displayedLength ++;
                    mSQLiteHelper.addDisplayedData(mealIdInt);
                    Toast.makeText(getActivity(), "You Disliked " +  mealName.getText(), Toast.LENGTH_SHORT).show();
                    mealName.setText("TEST");
                    nextMeal(mSQLiteHelper);
                }//end of if

//                Intent intent = new Intent(getActivity(), AccountSettings.class);
//                startActivity(intent);
            }
        });

        return rootView;
    }//end of onCreate

    public void nextMeal(SQLiteHelper mSQLiteHelper){
        if(displayedLength == 5){
            //choose a match
            int matchedMealIdInt = likedMealIds.peekLast();
            String macthedMealId = String.valueOf(matchedMealIdInt);
            Cursor mealData =mSQLiteHelper.getMealData();
            mealData.move(matchedMealIdInt);
            String mealName = mealData.getString(1);
            Toast.makeText(getActivity(), "You Matched With " + mealName, Toast.LENGTH_LONG).show();
            mSQLiteHelper.addMatchesData(macthedMealId);
            mSQLiteHelper.dropDispayedLikedTables();
            displayedMealIds = new LinkedList<>();
            displayedLength = 0;
            likedMealIds = new LinkedList<>();
            likedLength = 0;
        }//end of if
        setMeal(mSQLiteHelper);
    }//end of nextMeal

    public void setMeal(SQLiteHelper mSQLiteHelper){
        imageMeal = (ImageView) rootView.findViewById(R.id.explore_meal_image);
        mealName = (TextView) rootView.findViewById(R.id.explore_meal_name);
        mealDescription = (TextView) rootView.findViewById(R.id.explore_meal_description);

        int newMealId = getRandomMealId(mSQLiteHelper);

        int validMealID = -1;
        int matchIdFlag = -2;

        if(newMealId > validMealID){
            Cursor data = mSQLiteHelper.getMealData(); //get all the meals
            int itemID = newMealId;
            data.moveToPosition(itemID);

            mealId = data.getString(0);
            selectedMealName = data.getString(1);
            selectedMealDescription = data.getString(2);

            mealName.setText(selectedMealName);
            mealDescription.setText(selectedMealDescription);

            Cursor imgData = mSQLiteHelper.getMealImg(mealId);
            imgData.moveToFirst();
            byte[] mealImage = imgData.getBlob(0);

            Bitmap bitmap = BitmapFactory.decodeByteArray(mealImage, 0, mealImage.length);
            imageMeal.setImageBitmap(bitmap);
            mealIdInt = itemID;
            isValidMeal = true;
        }//end of if
        else if(newMealId == matchIdFlag){
            //match meal
            int matchedMealIdInt = likedMealIds.peekLast();
            String macthedMealId = String.valueOf(matchedMealIdInt);
            Cursor mealData =mSQLiteHelper.getMealData();
            mealData.move(matchedMealIdInt + 1);
            String mealName = mealData.getString(1);
            Toast.makeText(getActivity(), "You Matched With " + mealName, Toast.LENGTH_LONG).show();
            mSQLiteHelper.addMatchesData(macthedMealId);
            mSQLiteHelper.dropDispayedLikedTables();
            mSQLiteHelper.dropDispayedLikedTables();
            displayedLength = 0;
            displayedMealIds = new LinkedList<>();
            likedLength = 0;
            likedMealIds = new LinkedList<>();
            isValidMeal = true;
            nextMeal(mSQLiteHelper);
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
        }//end of else if
        else{
            mealName.setText("NO DATA");
            mealDescription.setText("There are meal in the database.  Please try to add a meal and then try again.");
            isValidMeal = false;
        }//end of else
    }//end of set meal

    public int getRandomMealId(SQLiteHelper mSQLiteHelper){
        int randomId;
        Cursor data = mSQLiteHelper.getMealData();
        int numberOfMeals = data.getCount();

        if(numberOfMeals != 0){
            if(numberOfMeals > 1 && numberOfMeals != displayedLength){
                boolean foundValidId = false;
                randomId = 0;
                while (!foundValidId){
                    foundValidId = true;
                    randomId = 0 + (int)(Math.random() * ((numberOfMeals - 0) + 0));
                    for(int i = 0; i < displayedLength; i++) {
                        if(randomId == displayedMealIds.get(i)){
                            foundValidId = false;
                            break;
                        }//end of if
                    }//end of for
                }//end of while
            }//end of if
            else if(numberOfMeals == displayedLength){
                randomId = -2;
            }//end of else if
            else{
                randomId = 0;
            }//end of else
        }//end of if

        else{
            randomId = -1;
        }//end of else

        return randomId;
    }//end of getRandomMealID

    public LinkedList<Integer> setDisplayedMealIds(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> displayedMealIds = new LinkedList<>();
        Cursor displayedData = mSQLiteHelper.getDisplayedData();
        int numberOfDisplayedMeals = displayedData.getCount();
        displayedData.moveToFirst();
        if(numberOfDisplayedMeals != 0){
            for(int i = 0; i < numberOfDisplayedMeals; i++){
                displayedMealIds.push(displayedData.getInt(1));
                displayedLength ++;
                displayedData.moveToNext();
            }//end of for
        }//end of if
        return displayedMealIds;
    }//end of setDisplayedMealIds

    public LinkedList<Integer> setLikedMealIds(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> likedMealIds = new LinkedList<>();
        Cursor likedData = mSQLiteHelper.getLikedData();
        int numberOfLikedMeals = likedData.getCount();
        likedData.moveToFirst();
        if(numberOfLikedMeals != 0){
            for(int i = 0; i < numberOfLikedMeals; i++){
                likedMealIds.push(likedData.getInt(1));
                likedLength ++;
                likedData.moveToNext();
            }//end of for
        }//end of if
        return likedMealIds;
    }//end of setDisplayedMealIds

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }//end of imageViewToByte
}

