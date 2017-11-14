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



public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "meal_table";
//    private static final String TABLE_NAME = "meal_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "meal_name";
    private static final String COL3 = "meal_description";
    private static final String COL4 = "prep_time";
    private static final String COL5 = "cook_time";
    private static final String COL6 = "serving_size";
    private static final String COL7 = "meal_directions";


    public SQLiteHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }//end of SQLiteHelper

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT)";

        String newCreateTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," + COL3 +" TEXT," + COL4 +" TEXT," + COL5 +" TEXT," + COL6 +" TEXT," + COL7 +" TEXT);";
//
//        String newNewCreateTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "meal_name TEXT" + "meal_description TEXT" + "prep_time TEXT" + "cook_time TEXT" + "serving_size TEXT" + "meal_directions TEXT)";

        db.execSQL(newCreateTable);
        Log.d(TAG, "Created Table " + TABLE_NAME);
    }//end of onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d(TAG, "Upgraded Table " + TABLE_NAME);
        onCreate(db);
    }//end of onUpgrade

    public boolean addData(String meal_name, String meal_description, String prep_time, String cook_time,
                           String serving_size, String meal_directions) {
        SQLiteDatabase db = this.getWritableDatabase();
//        onUpgrade(db,1,1);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, meal_name);
        contentValues.put(COL3, meal_description);
        contentValues.put(COL4, prep_time);
        contentValues.put(COL5, cook_time);
        contentValues.put(COL6, serving_size);
        contentValues.put(COL7, meal_directions);

        Log.d(TAG, "addData: Adding " + meal_name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }//end of addData

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getData

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }//end of getItemID

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(int id, String newName, String oldName,
                                    String newDescription, String oldDescription,
                                    String newPrepTime, String oldPrepTime,
                                    String newCookTime, String oldCookTime,
                                    String newServingSize, String oldServingSize,
                                    String newDirections, String oldDirections){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);

        String descriptionQuery = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newDescription + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldDescription + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting description to " + newDescription);

        String prepTimeQuery = "UPDATE " + TABLE_NAME + " SET " + COL4 +
                " = '" + newPrepTime + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + oldPrepTime + "'";
        Log.d(TAG, "updateName: query: " + descriptionQuery);
        Log.d(TAG, "updateName: Setting prep time to " + newPrepTime);

        String cookTimeQuery = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + newCookTime + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL5 + " = '" + oldCookTime + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting cook time to " + newCookTime);

        String servingSizeQuery = "UPDATE " + TABLE_NAME + " SET " + COL6 +
                " = '" + newServingSize + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL6 + " = '" + oldServingSize + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting serving size to " + newServingSize);

        String mealDirectionsQuery = "UPDATE " + TABLE_NAME + " SET " + COL7 +
                " = '" + newDirections + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL7 + " = '" + oldDirections + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting meal directions to " + newDirections);

        db.execSQL(query);
        db.execSQL(descriptionQuery);
        db.execSQL(prepTimeQuery);
        db.execSQL(cookTimeQuery);
        db.execSQL(servingSizeQuery);
        db.execSQL(mealDirectionsQuery);
    }//end of updateNames

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }//end of deleteName

}//end of main

//*******************************************BACKUP*************************************************
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }//end of onUpgrade
//
//    public boolean addData(String item) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL2, item);
//
//        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);
//
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        //if date as inserted incorrectly it will return -1
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }//end of addData
//
//    /**
//     * Returns all the data from database
//     * @return
//     */
//    public Cursor getData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }//end of getData
//
//    /**
//     * Returns only the ID that matches the name passed in
//     * @param name
//     * @return
//     */
//    public Cursor getItemID(String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
//                " WHERE " + COL2 + " = '" + name + "'";
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }//end of getItemID
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
//    public void deleteName(int id, String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
//                + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + name + "'";
//        Log.d(TAG, "deleteName: query: " + query);
//        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
//        db.execSQL(query);
//    }//end of deleteName
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
