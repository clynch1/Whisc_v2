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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

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
//    private String selectedMealName, selectedMealDescription;
    private ListView mListView;
    private View rootView;
//    private String selectedMealID;
    public int displayedLength, likedLength, beefLength, chickenLength, porkLength, fishLength, turkeyLength, finalMealID;
    private boolean isValidMeal;
    private Cursor allMealData;
    LinkedList<Integer> displayedMealIds;
    LinkedList<Integer> likedMealIds;
    LinkedList<Integer> chickenMeals;
    LinkedList<Integer> beefMeals;
    LinkedList<Integer> porkMeals;
    LinkedList<Integer> fishMeals;
    LinkedList<Integer> turkeyMeals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab2explore, container, false);
        mSQLiteHelper = new SQLiteHelper(getActivity());
        allMealData = mSQLiteHelper.getMealData();

        setLists();

        isValidMeal = true;
        finalMealID = setMeal();

        Button likeButton = rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMeal){
                    displayedMealIds.push(finalMealID);
                    displayedLength ++;
                    likedMealIds.push(finalMealID);
                    likedLength ++;
                    setMealMeat(finalMealID);
                    mSQLiteHelper.addDisplayedData(finalMealID);
                    mSQLiteHelper.addLikedData(finalMealID);
                    Toast.makeText(getActivity(), "You Liked " + mealName.getText().toString(), Toast.LENGTH_SHORT).show();
                    finalMealID = nextMeal();
                }//end of id
            }
        });
        Button supLikeButton = rootView.findViewById(R.id.suplikeButton);
        supLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMeal){
                    itsAMatch(finalMealID);
                }//end of id
            }//end of onClick
        });
        Button dislikeButton = rootView.findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMeal){
                    displayedMealIds.push(finalMealID);
                    displayedLength ++;
                    mSQLiteHelper.addDisplayedData(finalMealID);
                    Toast.makeText(getActivity(), "You Disliked " +  mealName.getText(), Toast.LENGTH_SHORT).show();
                    mealName.setText("TEST");
                    finalMealID = nextMeal();
                }//end of if
            }
        });

        return rootView;
    }//end of onCreate

    public int nextMeal(){
        allMealData = mSQLiteHelper.getMealData();
        if(displayedLength == 5){                                                                   //this value sets the session length
            if(likedLength != 0){
                itsAMatch(-2);//pass in 0 so that the passed meal id is for a valid meal and it will choose a match
            }//end of if
            else{
                displayedLength = 0;
                displayedMealIds = new LinkedList<>();
            }//end of else
        }//end of if
        int mealId = setMeal();
        return mealId;
    }//end of nextMeal

    public int setMeal(){
        imageMeal = (ImageView) rootView.findViewById(R.id.explore_meal_image);
        mealName = (TextView) rootView.findViewById(R.id.explore_meal_name);
        mealDescription = (TextView) rootView.findViewById(R.id.explore_meal_description);
        mListView = (ListView) rootView.findViewById(R.id.explore_meal_ingredients);

        int newMealId = getRandomMealId();

        int validMealID = -1;
        int matchIdFlag = -2;

        if(newMealId > validMealID){
//            Cursor data = mSQLiteHelper.getMealData(); //get all the meals
            allMealData.moveToPosition(newMealId);

            mealName.setText(allMealData.getString(1));
            mealDescription.setText(allMealData.getString(2));

            populateListView(String.valueOf(newMealId + 1));

            Cursor imgData = mSQLiteHelper.getMealImg(String.valueOf(newMealId + 1));
            imgData.moveToLast();
            byte[] mealImage = imgData.getBlob(0);

            Bitmap bitmap = BitmapFactory.decodeByteArray(mealImage, 0, mealImage.length);
            imageMeal.setImageBitmap(bitmap);
            isValidMeal = true;
        }//end of if
        else if(newMealId == matchIdFlag){
            itsAMatch(newMealId);
        }//end of else if
        else{
            mealName.setText("NO DATA");
            mealDescription.setText("There are meal in the database.  Please try to add a meal and then try again.");
            isValidMeal = false;
        }//end of else
        return newMealId;
    }//end of set meal

    //In setLists i am setting up the linked list that will be used to keep track of the values in this class.
    //I am setting all of the lengths equal to 0 before I call the method to actually set up the tables if they
    //need to be populated with something.  After they have been set the values in the the lengths may change
    //if they are filled with values.  All of the methods have meaninful names and should be easy to figure out what
    //it is they do.
    public void setLists(){
        displayedLength = 0;
        displayedMealIds = setDisplayedMealIds(mSQLiteHelper);

        likedLength = 0;
        likedMealIds = setLikedMealIds(mSQLiteHelper);

        beefLength = 0;
        beefMeals = setBeefMeals(mSQLiteHelper);

        porkLength = 0;
        porkMeals = setPorkMeals(mSQLiteHelper);

        chickenLength = 0;
        chickenMeals = setChickenMeals(mSQLiteHelper);

        fishLength = 0;
        fishMeals = setFishMeals(mSQLiteHelper);

        turkeyLength = 0;
        turkeyMeals = setTurkeyMeals(mSQLiteHelper);
    }//end of setIngredients

    public void itsAMatch(int passedId){
        int matchedMealId;
        if(passedId != -2){                             //this is for when you use the super like
            matchedMealId = passedId;
        }//end of if flag
        else{                                           //this should only be done if the match needs to be calculated
            matchedMealId = matchingAlg();
            Cursor mealData = mSQLiteHelper.getMealData();
            mealData.moveToPosition(matchedMealId);
        }//end of else

        //cleanup
        mSQLiteHelper.addMatchesData(String.valueOf(matchedMealId));
        mSQLiteHelper.dropDispayedLikedTables();
        mSQLiteHelper.dropMeatsTables();
        clean();

        //change intent
        Intent intent = new Intent(getActivity(), ViewMatch.class);
        intent.putExtra("id",String.valueOf(matchedMealId + 1));
        startActivity(intent);
    }//end of itsAMatch

    public void setMealMeat(int mealIdInt){
        allMealData.moveToPosition(mealIdInt);                                                  //questionable +1 used here
        String meatType = allMealData.getString(8);
        switch (meatType){
            case "beef":{
                beefMeals.push(mealIdInt);
                mSQLiteHelper.addBeefData(mealIdInt);
                beefLength ++;
                break;
            }//end of beef
            case "pork":{
                porkMeals.push(mealIdInt);
                mSQLiteHelper.addPorkData(mealIdInt);
                porkLength ++;
                break;
            }//end of pork
            case "chicken":{
                chickenMeals.push(mealIdInt);
                mSQLiteHelper.addChickenData(mealIdInt);
                chickenLength ++;
                break;
            }//end of chicken
            case "fish":{
                fishMeals.push(mealIdInt);
                mSQLiteHelper.addFishData(mealIdInt);
                fishLength ++;
                break;
            }//end of fish
            case "turkey":{
                turkeyMeals.push(mealIdInt);
                mSQLiteHelper.addTurkeyData(mealIdInt);
                turkeyLength ++;
                break;
            }//end of turkey
        }//end of switch
    }//end of setMealMeat

    public int matchingAlg(){
        int rndBeef = 0;
        int rndTurkey = 0;
        int rndFish = 0;
        int rndChicken = 0;
        int rndPork = 0;
        if(beefLength > 0){
            rndBeef = new Random().nextInt(beefLength);
        }//end of if
        if(chickenLength > 0){
            rndChicken = new Random().nextInt(chickenLength);
        }//end of if
        if(porkLength > 0){
            rndPork = new Random().nextInt(porkLength);
        }//end of if
        if(fishLength > 0){
            rndFish = new Random().nextInt(fishLength);
        }//end of if
        if(turkeyLength > 0){
            rndTurkey = new Random().nextInt(turkeyLength);
        }//end of if


        int mealInt = 0;
        if ((turkeyLength != 0) && (turkeyLength >= chickenLength) && (turkeyLength >= beefLength) && (turkeyLength >= porkLength) && (turkeyLength >= fishLength)) {
            mealInt = turkeyMeals.get(rndTurkey);
        }//end of if
        else if ((fishLength != 0) && (fishLength >= chickenLength) && (fishLength >= beefLength) && (fishLength >= porkLength)) {
            mealInt = fishMeals.get(rndFish);
        }//end of else if
        else if ((chickenLength != 0) && (chickenLength >= beefLength) && (chickenLength >= porkLength)) {
            mealInt = chickenMeals.get(rndChicken);
        }//end of else if
        else if (beefLength != 0 && beefLength >= porkLength) {
            mealInt = beefMeals.get(rndBeef);
        }//end of else if
        else if(porkLength != 0){
            mealInt = porkMeals.get(rndPork);
        }//end of else if

        return mealInt;
    }//end of matchingAlg

    public void clean(){
        mSQLiteHelper.dropDispayedLikedTables();
        mSQLiteHelper.dropMeatsTables();
        displayedMealIds = new LinkedList<>();
        displayedLength = 0;
        likedMealIds = new LinkedList<>();
        likedLength = 0;
        beefMeals = new LinkedList<>();
        beefLength = 0;
        porkMeals = new LinkedList<>();
        porkLength = 0;
        chickenMeals = new LinkedList<>();
        chickenLength = 0;
        fishMeals = new LinkedList<>();
        fishLength = 0;
    }//end of clean

    //This method gets the id for the random meal.  It has the following three possible returns:
    //              -2:     This is returned if there are no more meals to show, meaning the number
    //                      of displayed meals is equal to the number of total meals.
    //              -1:     This is returned when there are no meals at all.  This will only occur if
    //                      there are no meals in the meals table or in the future when there is
    //                      no connection to the server.
    //               0:     This is returned when there is only one meal in the meal table.  If there
    //                      is only one meal then only one meal can be displayed.
    //           other:     Other values that may be returned are the actual id values.
    public int getRandomMealId(){
        int randomId;
        int numberOfMeals = mSQLiteHelper.getMealDataLength();

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

    public LinkedList<Integer> setBeefMeals(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> beefMeals = new LinkedList<>();
        Cursor beefData = mSQLiteHelper.getBeefData();
        int numberOfBeefMeals = beefData.getCount();
        beefData.moveToFirst();
        if(numberOfBeefMeals!= 0){
            for(int i = 0; i < numberOfBeefMeals; i++){
                beefMeals.push(beefData.getInt(1));
                beefLength ++;
                beefData.moveToNext();
            }//end of for
        }//end of if
        return beefMeals;
    }//end of setBeefMeals

    public LinkedList<Integer> setPorkMeals(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> porkMeals = new LinkedList<>();
        Cursor porkData = mSQLiteHelper.getPorkData();
        int numberOfPorkMeals = porkData.getCount();
        porkData.moveToFirst();
        if(numberOfPorkMeals != 0){
            for(int i = 0; i < numberOfPorkMeals; i++){
                porkMeals.push(porkData.getInt(1));
                porkLength++;
                porkData.moveToNext();
            }//end of for
        }//end of if
        return porkMeals;
    }//end of setPorkMeals

    public LinkedList<Integer> setChickenMeals(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> chickenMeals = new LinkedList<>();
        Cursor chickenData = mSQLiteHelper.getChickenData();
        int numberOfChickenMeals = chickenData.getCount();
        chickenData.moveToFirst();
        if(numberOfChickenMeals != 0){
            for(int i = 0; i < numberOfChickenMeals; i++){
                chickenMeals.push(chickenData.getInt(1));
                chickenLength++;
                chickenData.moveToNext();
            }//end of for
        }//end of if
        return chickenMeals;
    }//end of setPorkMeals

    public LinkedList<Integer> setFishMeals(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> fishMeals = new LinkedList<>();
        Cursor fishData = mSQLiteHelper.getFishData();
        int numberOfLikedMeals = fishData.getCount();
        fishData.moveToFirst();
        if(numberOfLikedMeals != 0){
            for(int i = 0; i < numberOfLikedMeals; i++){
                fishMeals.push(fishData.getInt(1));
                fishLength ++;
                fishData.moveToNext();
            }//end of for
        }//end of if
        return fishMeals;
    }//end of setFishMeals

    public LinkedList<Integer> setTurkeyMeals(SQLiteHelper mSQLiteHelper){
        LinkedList<Integer> turkeyMeals = new LinkedList<>();
        Cursor turkeyData = mSQLiteHelper.getTurkeyData();
        int numberOfLikedMeals = turkeyData.getCount();
        turkeyData.moveToFirst();
        if(numberOfLikedMeals != 0){
            for(int i = 0; i < numberOfLikedMeals; i++){
                turkeyMeals.push(turkeyData.getInt(1));
                turkeyLength ++;
                turkeyData.moveToNext();
            }//end of for
        }//end of if
        return turkeyMeals;
    }//end of setTurkeyMeals

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }//end of imageViewToByte

    private void populateListView(String mealId) {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        //get the data and append to a list
        Cursor ingredientData = mSQLiteHelper.getMealIngredients(mealId);
        ArrayList<String> listData = new ArrayList<>();
        while(ingredientData.moveToNext()){
            listData.add(ingredientData.getString(3));
        }//end of while
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }//end of populateListView

}

