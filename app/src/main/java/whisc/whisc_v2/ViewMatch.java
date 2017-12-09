package whisc.whisc_v2;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ViewMatch extends AppCompatActivity {


    private static final String TAG = "EditDataActivity";

    private Button btnReview;
    private TextView set_name, set_description, set_prep, set_cook, set_serving, set_directions;
    private ImageView imageMeal;

    private ListView mListView;
    SQLiteHelper mSQLiteHelper;

    private String selectedName, selectedDescription, selectedPrep, selectedCook, selectedServing, selectedDirections, selectedMealID;
    private int selectedID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_match);
        btnReview = (Button) findViewById(R.id.matchReviewLater);
        set_name = (TextView) findViewById(R.id.viewMealName);
        set_description = (TextView) findViewById(R.id.viewMealDescription);
        set_prep = (TextView) findViewById(R.id.viewPrepTime);
        set_cook = (TextView) findViewById(R.id.viewCookTime);
        set_serving = (TextView) findViewById(R.id.viewServingSize);
        set_directions = (TextView) findViewById(R.id.viewMealDirections);
        imageMeal = (ImageView) findViewById(R.id.viewImageMeal);
        mSQLiteHelper = new SQLiteHelper(this);


        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        int mealID = Integer.parseInt(receivedIntent.getStringExtra("id"));
        Cursor mealData = mSQLiteHelper.getMealData();
        mealData.moveToPosition(mealID - 1);

        //now get the name we passed as an extra
        selectedName = mealData.getString(1);
        selectedDescription = mealData.getString(2);
        selectedPrep = mealData.getString(3);
        selectedCook = mealData.getString(4);
        selectedServing = mealData.getString(5);
        selectedDirections = mealData.getString(6);

        set_name.setText(selectedName);
        set_description.setText(selectedDescription);
        set_prep.setText(selectedPrep);
        set_cook.setText(selectedCook);
        set_serving.setText(selectedServing);
        set_directions.setText(selectedDirections);
        selectedID = mealID;

        mListView = (ListView) findViewById(R.id.listIngredients);
        final String mealId = Integer.toString(selectedID);
        selectedMealID = mealId;
        populateListView(mealId);

        Cursor data= mSQLiteHelper.getMealImg(receivedIntent.getStringExtra("id"));
        data.moveToLast();
        byte[] mealImage = data.getBlob(0);

        Bitmap bitmap = BitmapFactory.decodeByteArray(mealImage, 0, mealImage.length);
        imageMeal.setImageBitmap(bitmap);

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMatch.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }//end of on create

    private void populateListView(final String mealID) {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);
    }//end of populate list view

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            Cursor data = mSQLiteHelper.getMealIngredients(selectedMealID);
            return data.getCount();
        }

        @Override
        public String getItem(int i) {
            Cursor data = mSQLiteHelper.getMealIngredients(selectedMealID);
            data.moveToPosition(i);
            String name = data.getString(3);
            Log.d(TAG, "addMealData: get item " + name);
            return name;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.ingredient_layout, null);

            TextView amount = (TextView) view.findViewById(R.id.ingredient_layout_amount);
            TextView ingredient = (TextView) view.findViewById(R.id.ingredient_layout_ingredient);

            String newAmount;
            String newIngredient;
            Cursor data = mSQLiteHelper.getMealIngredients(selectedMealID);
            data.moveToPosition(i);
            newAmount = data.getString(2);
            newIngredient = data.getString(3);

            amount.setText(newAmount);
            ingredient.setText(newIngredient);

            return view;
        }
    }//end of CustomAdapter

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}