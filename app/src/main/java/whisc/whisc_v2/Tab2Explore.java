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
    private String selectedMealName, selectedMealDescription, mealId;
    private ListView mListView;
    private View rootView;
    private String selectedMealID;
    public int displayedLength, likedLength, beefLength, chickenLength, porkLength, fishLength, turkeyLength, mealIdInt;
    private boolean isValidMeal;
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
//        imageMeal = (ImageView) rootView.findViewById(R.id.explore_meal_image);
//        mealName = (TextView) rootView.findViewById(R.id.explore_meal_name);
//        mealDescription = (TextView) rootView.findViewById(R.id.explore_meal_description);
        mSQLiteHelper = new SQLiteHelper(getActivity());

        displayedMealIds = setDisplayedMealIds(mSQLiteHelper);
        likedMealIds = setLikedMealIds(mSQLiteHelper);
        chickenMeals = setLikedMealIds(mSQLiteHelper);
        beefMeals = setBeefMeals(mSQLiteHelper);
        porkMeals = setPorkMeals(mSQLiteHelper);
        fishMeals = setFishMeals(mSQLiteHelper);
        turkeyMeals = setTurkeyMeals(mSQLiteHelper);

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
            }
        });
        Button supLikeButton = rootView.findViewById(R.id.suplikeButton);
        supLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidMeal){
                    String mealId = String.valueOf(mealIdInt);
                    Toast.makeText(getActivity(), "You Matched With " + mealName.getText(), Toast.LENGTH_LONG).show();
                    mSQLiteHelper.addMatchesData(mealId);
                    mSQLiteHelper.dropDispayedLikedTables();
                    displayedMealIds = new LinkedList<>();
                    displayedLength = 0;
                    likedMealIds = new LinkedList<>();
                    likedLength = 0;
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }//end of id
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
            }
        });

        return rootView;
    }//end of onCreate

    public void nextMeal(SQLiteHelper mSQLiteHelper){
        if(displayedLength == 5){
            //choose a match
            if(likedLength != 0){
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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }//end of if
            else{
                displayedLength = 0;
                displayedMealIds = new LinkedList<>();
            }//end of else
        }//end of if
        setMeal(mSQLiteHelper);
    }//end of nextMeal

    public void setMeal(SQLiteHelper mSQLiteHelper){
        imageMeal = (ImageView) rootView.findViewById(R.id.explore_meal_image);
        mealName = (TextView) rootView.findViewById(R.id.explore_meal_name);
        mealDescription = (TextView) rootView.findViewById(R.id.explore_meal_description);
        mListView = (ListView) rootView.findViewById(R.id.explore_meal_ingredients);

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

            populateListView(String.valueOf(itemID));

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
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            nextMeal(mSQLiteHelper);
        }//end of else if
        else{
            mealName.setText("NO DATA");
            mealDescription.setText("There are meal in the database.  Please try to add a meal and then try again.");
            isValidMeal = false;
        }//end of else
    }//end of set meal

    public int matchingAlg(){
        return 1;
//        JSONObject obj = JSONUtils.getJSONObjectFromFile("/obj.json");
//
//        JSONArray jsonArray = obj.getJSONArray("Chicken");
//        int chicken = jsonArray.length();
//        int rnd1 = new Random().nextInt(jsonArray.length());
//        //System.out.println(chicken);
//
//        JSONArray jsonArray2 = obj.getJSONArray("Beef");
//        int beef = jsonArray2.length();
//        int rnd2 = new Random().nextInt(jsonArray2.length());
//        //System.out.println(beef);
//
//        JSONArray jsonArray3 = obj.getJSONArray("Pork");
//        int pork = jsonArray3.length();
//        int rnd3 = new Random().nextInt(jsonArray3.length());
//        //System.out.println(pork);
//
//        JSONArray jsonArray4 = obj.getJSONArray("Fish");
//        int fish = jsonArray4.length();
//        int rnd4 = new Random().nextInt(jsonArray4.length());
//        //System.out.println(fish);
//
//        JSONArray jsonArray5 = obj.getJSONArray("Turkey");
//        int turkey = jsonArray5.length();
//        int rnd5 = new Random().nextInt(jsonArray5.length());
//        //System.out.println(turkey);
//
//        JSONArray matchedMeals = obj.getJSONArray("Matched Meals");
//
//        if ((turkey >= chicken) && (turkey >= beef) && (turkey >= pork) && (turkey >= fish)) {
//            System.out.println ("Your most desired meal is " + "turkey. " + "You liked " + turkey + " turkey meals.");
//            System.out.println ("We recommend meal " + rnd5 + ".");
//            matchedMeals.put(rnd5);
//        } else if ((fish >= chicken) && (fish >= beef) && (fish >= pork)) {
//            System.out.println ("Your most desired meal is " + "fish. " + "You liked " + fish + " fish meals.");
//            System.out.println ("We recommend meal " + rnd4 + ".");
//            matchedMeals.put(rnd4);
//        } else if ((chicken >= beef) && (chicken >= pork)) {
//            System.out.println ("Your most desired meal is " + "chicken. " + "You liked " + chicken + " chicken meals.");
//            System.out.println ("We recommend meal " + rnd1 + ".");
//            matchedMeals.put(rnd1);
//
//        } else if (beef >= pork) {
//            System.out.println ("Your most desired meal is " + "beef. " + "You liked " + beef + " beef meals.");
//            System.out.println ("We recommend meal " + rnd2 + ".");
//            matchedMeals.put(rnd2);
//        } else {
//            System.out.println ("Your most desired meal is " + "pork. " + "You liked " + pork + " pork meals.");
//            System.out.println ("We recommend meal " + rnd3 + ".");
//            matchedMeals.put(rnd3);
//        }
//
//        System.out.println(obj);
    }//end of matchingAlg

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
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(ingredientData.getString(3));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
        }

}

