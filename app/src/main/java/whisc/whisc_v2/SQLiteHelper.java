package whisc.whisc_v2;

import whisc.whisc_v2.TableData.TableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Columns;
import android.util.Log;

import java.sql.Blob;


public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_MEAL = "meal_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "meal_name";
    private static final String COL3 = "meal_description";
    private static final String COL4 = "prep_time";
    private static final String COL5 = "cook_time";
    private static final String COL6 = "serving_size";
    private static final String COL7 = "meal_directions";
    private static final String COL8 = "meal_image";
    private static final String COL9 = "meat_type";

    private static final String TABLE_INGREDIENTS = "ingredients_table";
    private static final String B_COL1 = "ingredients_id";
    private static final String B_COL2 = "meal_id";
    private static final String B_COL3 = "ingredient_amount";
    private static final String B_COL4 = "ingredient_type";

    private static final String HOLDER_TABLE_INGREDIENTS = "holder_ingredients_table";
    private static final String H_COL1 = "ingredients_id";
    private static final String H_COL2 = "ingredient_amount";
    private static final String H_COL3 = "ingredient_type";

    private static final String MATCHES_TABLE = "matches_table";
    private static final String M_COL1 = "match_id";
    private static final String M_COL2 = "meal_id";

    private static final String DISPLAYED_TABLE = "displayed_table";
    private static final String D_COL1 = "id";
    private static final String D_COL2 = "meal_id";

    private static final String LIKED_TABLE = "liked_table";
    private static final String L_COL1 = "id";
    private static final String L_COL2 = "meal_id";

    private static final String BEEF_TABLE = "beef_table";
    private static final String BE_COL1 = "id";
    private static final String BE_COL2 = "meal_id";

    private static final String PORK_TABLE = "pork_table";
    private static final String P_COL1 = "id";
    private static final String P_COL2 = "meal_id";

    private static final String CHICKEN_TABLE = "chicken_table";
    private static final String C_COL1 = "id";
    private static final String C_COL2 = "meal_id";

    private static final String FISH_TABLE = "fish_table";
    private static final String F_COL1 = "id";
    private static final String F_COL2 = "meal_id";

    private static final String TURKEY_TABLE = "turkey_table";
    private static final String T_COL1 = "id";
    private static final String T_COL2 = "meal_id";

    public SQLiteHelper(Context context) {
        super(context, TABLE_MEAL, null, 1);
    }//end of SQLiteHelper

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createTable = "CREATE TABLE " + TABLE_MEAL + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COL2 +" TEXT)";
//
//        String oldCreateMealTable = "CREATE TABLE " + TABLE_MEAL + " (ID INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
//                COL2 +" INTEGER NOT NULL," + COL3 +" TEXT," + COL4 +" TEXT," + COL5 +" TEXT," + COL6 +" TEXT," + COL7 +" TEXT);";

//        String createMealTable = "CREATE TABLE " + TABLE_MEAL + " (ID INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
//                COL2 +" INTEGER NOT NULL," + COL3 +" TEXT," + COL4 +" TEXT," + COL5 +" TEXT," + COL6 +" TEXT," + COL7 + " TEST," +
//                COL8 + " BLOB);";

        String createMealTable = "CREATE TABLE " + TABLE_MEAL + " (ID INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" INTEGER NOT NULL," + COL3 +" TEXT," + COL4 +" TEXT," + COL5 +" TEXT," + COL6 +" TEXT," + COL7 + " TEST," +
                COL8 + " BLOB," + COL9 + " TEXT);";

        String createIngredientsTable = "CREATE TABLE " + TABLE_INGREDIENTS + " (ingredients_id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                B_COL2 +" TEXT," + B_COL3 +" TEXT," + B_COL4 +" TEXT);";

        String createMatchesTable = "CREATE TABLE " + MATCHES_TABLE + " (ingredients_id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                M_COL2 +" TEXT);";

        db.execSQL(createMealTable);
        Log.d(TAG, "Created Table " + TABLE_MEAL);

        db.execSQL(createIngredientsTable);
        Log.d(TAG, "Created Table " + TABLE_INGREDIENTS);

        db.execSQL(createMatchesTable);
        Log.d(TAG, "Created Table " + MATCHES_TABLE);

        createDisplayedTable(db);
        createLikedTable(db);
        createHolderTable(db);
        createBeefTable(db);
        createPorkTable(db);
        createChickenTable(db);
        createFishTable(db);
        createTurkeyTable(db);
    }//end of onCreate

    public void createDisplayedTable(SQLiteDatabase db){
        String createDispayedTable = "CREATE TABLE " + DISPLAYED_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                D_COL2 +" INT);";

        db.execSQL(createDispayedTable);
        Log.d(TAG, "Created Table " + DISPLAYED_TABLE);
    }//end of createDisplayedTable

    public void createLikedTable(SQLiteDatabase db){
        String createLikedTable = "CREATE TABLE " + LIKED_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                L_COL2 +" INT);";

        db.execSQL(createLikedTable);
        Log.d(TAG, "Created Table " + LIKED_TABLE);
    }//end of createLikedTable

    public void createBeefTable(SQLiteDatabase db){
        String createBeefTable = "CREATE TABLE " + BEEF_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                BE_COL2 +" INT);";

        db.execSQL(createBeefTable);
        Log.d(TAG, "Created Table " + BEEF_TABLE);
    }//end of createHolderTable

    public void createPorkTable(SQLiteDatabase db){
        String createPorkTable = "CREATE TABLE " + PORK_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                P_COL2 +" INT);";

        db.execSQL(createPorkTable);
        Log.d(TAG, "Created Table " + PORK_TABLE);
    }//end of createHolderTable

    public void createChickenTable(SQLiteDatabase db){
        String createChickenTable = "CREATE TABLE " + CHICKEN_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                C_COL2 +" INT);";

        db.execSQL(createChickenTable);
        Log.d(TAG, "Created Table " + CHICKEN_TABLE);
    }//end of createHolderTable

    public void createFishTable(SQLiteDatabase db){
        String createFishTable = "CREATE TABLE " + FISH_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                F_COL2 +" INT);";

        db.execSQL(createFishTable);
        Log.d(TAG, "Created Table " + FISH_TABLE);
    }//end of createHolderTable

    public void createTurkeyTable(SQLiteDatabase db){
        String createTurkeyTable = "CREATE TABLE " + TURKEY_TABLE + " (id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                T_COL2 +" INT);";

        db.execSQL(createTurkeyTable);
        Log.d(TAG, "Created Table " + TURKEY_TABLE);
    }//end of createHolderTable

    public void createHolderTable(SQLiteDatabase db){
        String createIngredientsHolderTable = "CREATE TABLE " + HOLDER_TABLE_INGREDIENTS + " (ingredients_id INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                H_COL2 +" TEXT," + H_COL3 +" TEXT);";

        db.execSQL(createIngredientsHolderTable);
        Log.d(TAG, "Created Table " + HOLDER_TABLE_INGREDIENTS);
    }//end of createHolderTable

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + MATCHES_TABLE);
        onCreate(db);
    }//end of onUpgrade

    public void dropHolderTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + HOLDER_TABLE_INGREDIENTS);
        createHolderTable(db);
    }//end of dropHolderTable

    public void dropDispayedLikedTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DISPLAYED_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LIKED_TABLE);
        createDisplayedTable(db);
        createLikedTable(db);
    }//end of dropHolderTable

    public void dropMeatsTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + BEEF_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PORK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CHICKEN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FISH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TURKEY_TABLE);
        createBeefTable(db);
        createPorkTable(db);
        createChickenTable(db);
        createFishTable(db);
        createTurkeyTable(db);
    }//end of dropHolderTable

    public boolean addMealData(String meal_name, String meal_description, String prep_time, String cook_time,
                               String serving_size, String meal_directions, byte[] meal_image, String meat_type) {
        SQLiteDatabase db = this.getWritableDatabase();
//        onUpgrade(db,1,1);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, meal_name);
        contentValues.put(COL3, meal_description);
        contentValues.put(COL4, prep_time);
        contentValues.put(COL5, cook_time);
        contentValues.put(COL6, serving_size);
        contentValues.put(COL7, meal_directions);
        contentValues.put(COL8, meal_image);
        contentValues.put(COL9, meat_type);

        Log.d(TAG, "addMealData: Adding " + meal_name + " to " + TABLE_MEAL);

        long result = db.insert(TABLE_MEAL, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addIngredientsData(String meal_id, String ingredient_amount, String ingredient_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(B_COL2, meal_id);
        contentValues.put(B_COL3, ingredient_amount);
        contentValues.put(B_COL4, ingredient_name);

        Log.d(TAG, "addMealData: Adding " + ingredient_name + " to " + TABLE_INGREDIENTS);

        long result = db.insert(TABLE_INGREDIENTS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addHolderIngredientsData(String ingredient_amount, String ingredient_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(H_COL2, ingredient_amount);
        contentValues.put(H_COL3, ingredient_name);

        Log.d(TAG, "addMealData: Adding " + ingredient_name + " to " + HOLDER_TABLE_INGREDIENTS);

        long result = db.insert(HOLDER_TABLE_INGREDIENTS, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addDisplayedData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(D_COL2, mealID);

        Log.d(TAG, "addDisplayedData: Adding " + mealID + " to " + DISPLAYED_TABLE);

        long result = db.insert(DISPLAYED_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addLikedData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(L_COL2, mealID);

        Log.d(TAG, "addLikedData: Adding " + mealID + " to " + LIKED_TABLE);

        long result = db.insert(LIKED_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addBeefData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BE_COL2, mealID);

        Log.d(TAG, "addBeefData: Adding " + mealID + " to " + BEEF_TABLE);

        long result = db.insert(BEEF_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addPorkData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_COL2, mealID);

        Log.d(TAG, "addPorkData: Adding " + mealID + " to " + PORK_TABLE);

        long result = db.insert(PORK_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addChickenData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_COL2, mealID);

        Log.d(TAG, "addChickenData: Adding " + mealID + " to " + CHICKEN_TABLE);

        long result = db.insert(CHICKEN_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addFishData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(F_COL2, mealID);

        Log.d(TAG, "addFishData: Adding " + mealID + " to " + FISH_TABLE);

        long result = db.insert(FISH_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addTurkeyData(int mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_COL2, mealID);

        Log.d(TAG, "addTurkeyData: Adding " + mealID + " to " + TURKEY_TABLE);

        long result = db.insert(TURKEY_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    public boolean addMatchesData(String meal_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(M_COL2, meal_id);

        Log.d(TAG, "addMealData: Adding " + meal_id + " to " + MATCHES_TABLE);

        long result = db.insert(MATCHES_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addMealData

    /**
     * Returns all the data from database
     * @return
     */
    public int getMealDataLength(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEAL;
        Cursor data = db.rawQuery(query, null);
        int mealDataLength = data.getCount();
        return mealDataLength;
    }//end of getMealData
    public Cursor getMealData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEAL;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getIngredientData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENTS;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getIngredientHolderData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + HOLDER_TABLE_INGREDIENTS;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getDisplayedData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DISPLAYED_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getLikedData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + LIKED_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getBeefData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + BEEF_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getPorkData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PORK_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getChickenData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CHICKEN_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getFishData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + FISH_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getTurkeyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TURKEY_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    public Cursor getMatchesData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + MATCHES_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealData

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getMealID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_MEAL +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID

    public Cursor getMealName(String mealId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_MEAL +
                " WHERE " + COL1 + " = '" + mealId + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID

    public Cursor getIngredientID(String mealID, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + B_COL1 + " FROM " + TABLE_INGREDIENTS +
                " WHERE " + B_COL4 + " = '" + name + "' AND " + B_COL2 + " = '" + mealID + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID

    public Cursor getHolderIngredientID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + H_COL1 + " FROM " + HOLDER_TABLE_INGREDIENTS +
                " WHERE " + H_COL3 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID

    public Cursor getMealIngredients(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENTS +
                " WHERE " + B_COL2 + " = '" + mealID + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID

    public Cursor getMealMeatType(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL9 +" FROM " + TABLE_MEAL +
                " WHERE " + COL1 + " = '" + mealID + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID

    public Cursor getMealImg(String meal_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL8 + " FROM " + TABLE_MEAL +
                " WHERE " + COL1 + " = '" + meal_id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getMealID
    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateMeal(int id, String newName, String oldName,
                                    String newDescription, String oldDescription,
                                    String newPrepTime, String oldPrepTime,
                                    String newCookTime, String oldCookTime,
                                    String newServingSize, String oldServingSize,
                                    String newDirections, String oldDirections,
                                    byte[] newImage, byte[] oldImage,
                                    String newMeatType, String oldMeatType){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_MEAL + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);

        String descriptionQuery = "UPDATE " + TABLE_MEAL + " SET " + COL3 +
                " = '" + newDescription + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldDescription + "'";
        Log.d(TAG, "updateName: query: " + descriptionQuery);
        Log.d(TAG, "updateName: Setting description to " + newDescription);

        String prepTimeQuery = "UPDATE " + TABLE_MEAL + " SET " + COL4 +
                " = '" + newPrepTime + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + oldPrepTime + "'";
        Log.d(TAG, "updateName: query: " + prepTimeQuery);
        Log.d(TAG, "updateName: Setting prep time to " + newPrepTime);

        String cookTimeQuery = "UPDATE " + TABLE_MEAL + " SET " + COL5 +
                " = '" + newCookTime + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL5 + " = '" + oldCookTime + "'";
        Log.d(TAG, "updateName: query: " + cookTimeQuery);
        Log.d(TAG, "updateName: Setting cook time to " + newCookTime);

        String servingSizeQuery = "UPDATE " + TABLE_MEAL + " SET " + COL6 +
                " = '" + newServingSize + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL6 + " = '" + oldServingSize + "'";
        Log.d(TAG, "updateName: query: " + servingSizeQuery);
        Log.d(TAG, "updateName: Setting serving size to " + newServingSize);

        String mealDirectionsQuery = "UPDATE " + TABLE_MEAL + " SET " + COL7 +
                " = '" + newDirections + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL7 + " = '" + oldDirections + "'";
        Log.d(TAG, "updateName: query: " + mealDirectionsQuery);
        Log.d(TAG, "updateName: Setting meal directions to " + newDirections);

        String mealImageQuery = "UPDATE " + TABLE_MEAL + " SET " + COL8 +
                " = '" + newImage + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL8 + " = '" + oldImage + "'";
        Log.d(TAG, "updateName: query: Updated Image");

        String meatTypeQuery = "UPDATE " + TABLE_MEAL + " SET " + COL9 +
                " = '" + newMeatType + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL9 + " = '" + oldMeatType + "'";
        Log.d(TAG, "updateName: query: " + meatTypeQuery);
        Log.d(TAG, "updateName: Setting meat type to " + newMeatType);

        db.execSQL(query);
        db.execSQL(descriptionQuery);
        db.execSQL(prepTimeQuery);
        db.execSQL(cookTimeQuery);
        db.execSQL(servingSizeQuery);
        db.execSQL(mealDirectionsQuery);
        db.execSQL(mealImageQuery);
        db.execSQL(meatTypeQuery);
    }//end of updateMeal

    public void updateIngredients(int id, String newAmount, String oldAmount,
                           String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String amountQuery = "UPDATE " + TABLE_INGREDIENTS + " SET " + B_COL3 +
                " = '" + newAmount + "' WHERE " + B_COL1 + " = '" + id + "'" +
                " AND " + B_COL3 + " = '" + oldAmount + "'";
        Log.d(TAG, "updateName: query: " + amountQuery);
        Log.d(TAG, "updateName: Setting amount to " + newAmount);

        String nameQuery = "UPDATE " + TABLE_INGREDIENTS + " SET " + B_COL4 +
                " = '" + newName + "' WHERE " + B_COL1 + " = '" + id + "'" +
                " AND " + B_COL4 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + nameQuery);
        Log.d(TAG, "updateName: Setting name to " + newName);

        db.execSQL(amountQuery);
        db.execSQL(nameQuery);
    }//end of updateIngredients

    public void updateHolderIngredients(int id, String newAmount, String oldAmount,
                                  String newName, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String amountQuery = "UPDATE " + HOLDER_TABLE_INGREDIENTS + " SET " + H_COL2 +
                " = '" + newAmount + "' WHERE " + H_COL1 + " = '" + id + "'" +
                " AND " + H_COL2 + " = '" + oldAmount + "'";
        Log.d(TAG, "updateName: query: " + amountQuery);
        Log.d(TAG, "updateName: Setting amount to " + newAmount);

        String nameQuery = "UPDATE " + HOLDER_TABLE_INGREDIENTS + " SET " + H_COL3 +
                " = '" + newName + "' WHERE " + H_COL1 + " = '" + id + "'" +
                " AND " + H_COL3 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + nameQuery);
        Log.d(TAG, "updateName: Setting name to " + newName);

        db.execSQL(amountQuery);
        db.execSQL(nameQuery);
    }//end of updateHolderIngredients
    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteMeal(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteMealQ = "DELETE FROM " + TABLE_MEAL + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteMeal: query: " + deleteMealQ);
        Log.d(TAG, "deleteMeal: Deleting " + name + " from database.");

        String deleteIngredientsQ = "DELETE FROM " + TABLE_INGREDIENTS + " WHERE "
                + B_COL2 + " = '" + id + "'";
        db.execSQL(deleteMealQ);
        db.execSQL(deleteIngredientsQ);
    }//end of deleteMeal

    public void deleteIngredient(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteIngredientQ = "DELETE FROM " + TABLE_INGREDIENTS + " WHERE "
                + B_COL1 + " = '" + id + "'" +
                " AND " + B_COL4 + " = '" + name + "'";
        Log.d(TAG, "deleteMeal: query: " + deleteIngredientQ);
        Log.d(TAG, "deleteMeal: Deleting " + name + " from database.");
        db.execSQL(deleteIngredientQ);
    }//end of deleteMeal

    public void deleteHolderIngredient(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteHolderIngredientQ = "DELETE FROM " + HOLDER_TABLE_INGREDIENTS + " WHERE "
                + H_COL1 + " = '" + id + "'" +
                " AND " + H_COL3 + " = '" + name + "'";
        Log.d(TAG, "deleteMeal: query: " + deleteHolderIngredientQ);
        Log.d(TAG, "deleteMeal: Deleting " + name + " from database.");
        db.execSQL(deleteHolderIngredientQ);
    }//end of deleteMeal

}//end of main

//*******************************************BACKUP*************************************************
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }//end of onUpgrade
//
//    public boolean addMealData(String item) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL2, item);
//
//        Log.d(TAG, "addMealData: Adding " + item + " to " + TABLE_NAME);
//
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        //if date as inserted incorrectly it will return -1
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }//end of addMealData
//
//    /**
//     * Returns all the data from database
//     * @return
//     */
//    public Cursor getMealData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }//end of getMealData
//
//    /**
//     * Returns only the ID that matches the name passed in
//     * @param name
//     * @return
//     */
//    public Cursor getMealID(String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
//                " WHERE " + COL2 + " = '" + name + "'";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }//end of getMealID
//
//    /**
//     * Updates the name field
//     * @param newName
//     * @param id
//     * @param oldName
//     */
//    public void updateName(String newName, int id, String oldName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
//                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + oldName + "'";
//        Log.d(TAG, "updateName: query: " + query);
//        Log.d(TAG, "updateName: Setting name to " + newName);
//        db.execSQL(query);
//    }//end of updateNames
//
//    /**
//     * Delete from database
//     * @param id
//     * @param name
//     */
//    public void deleteMeal(int id, String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
//                + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + name + "'";
//        Log.d(TAG, "deleteMeal: query: " + query);
//        Log.d(TAG, "deleteMeal: Deleting " + name + " from database.");
//        db.execSQL(query);
//    }//end of deleteMeal
//
//}//end of main


//**************************************THIS IS OLD*************************************************




//    public Cursor getUserPass(SQLiteHelper DOP, String user) {
//        SQLiteDatabase SQ = DOP.getReadableDatabase();
//        String selection = TableInfo.USER_NAME +" LIKE ?";
//        String coloumns[] = {TableInfo.USER_PASS};
//        String args[] = {user};
//        Cursor CR = SQ.query(TableInfo.TABLE_NAME, coloumns, selection, args, null, null, null);
//        return CR;
//
//    }
//
//    public void deleteUser(SQLiteHelper DOP, String user, String pass)
//    {
//        String selection = TableInfo.USER_NAME+ " LIKE ? AND "+TableInfo.USER_PASS +" LIKE ?";
//        //String coloumns[] = {TableInfo.USER_PASS};
//        String args[] = {user,pass};
//        SQLiteDatabase SQ = DOP.getWritableDatabase();
//        SQ.delete(TableInfo.TABLE_NAME, selection, args);
//
//    }
//
//    public void updateUserInfo(SQLiteHelper DOP, String user_name, String user_pass, String new_user_name )
//    {
//        SQLiteDatabase SQ  = DOP.getWritableDatabase();
//        String selection = TableInfo.USER_NAME+ " LIKE ? AND "+TableInfo.USER_PASS +" LIKE ?";
//        String args[] = {user_name,user_pass};
//        ContentValues values = new ContentValues();
//        values.put(TableInfo.USER_NAME, new_user_name);
//        SQ.update(TableInfo.TABLE_NAME, values, selection, args);
//
//
//    }

//}//end of mainMethod







//**************************************THIS IS OLD*************************************************


//import android.content.ContentValues;
//import android.content.Context;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by Connor on 10/30/2017.
// */
//
//public class SQLiteHelper extends SQLiteOpenHelper{
//    public static final String DATABASE_NAME = "User.db";
//    public static final String TABLE_NAME = "user_recipe_table";
//    public static final String COL_1 = "ID";
//    public static final String COL_2 = "NAME";
//    public static final String COL_3 = "DESCRIPTION";
//    public static final String COL_4 = "PREP_TIME";
//    public static final String COL_5 = "COOK_TIME";
//    public static final String COL_6 = "SERVING_SIZE";
//    public static final String COL_7 = "DIRECTIONS";
//
////    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
////        super(context, name, factory, version);
////    }
//
//    public SQLiteHelper(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT,PREP_TIME TEXT,COOK_TIME TEXT,SERVING_SIZE TEXT,DIRECTIONS TEXT)");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//
//    public boolean insertData(String name, String description, String prep_time, String cook_time, String serving_size, String directions) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, description);
//        contentValues.put(COL_4, prep_time);
//        contentValues.put(COL_5, cook_time);
//        contentValues.put(COL_6, serving_size);
//        contentValues.put(COL_7, directions);
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        if (result == -1){
//            return false;
//        }
//        else {
//            return true;
//        }
//
//    }
//}
