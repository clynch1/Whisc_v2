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
    public static final int database_version = 1;
    public String CREATE_T1_QUERY = "CREATE TABLE "+TableInfo.TABLE_1_NAME+"("+ TableInfo.MEAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                                            +TableInfo.MEAL_NAME+" TEXT,"
                                                                            +TableInfo.MEAL_DESCRIPTION+" TEXT,"
                                                                            +TableInfo.PREP_TIME+" TEXT,"
                                                                            +TableInfo.COOK_TIME+" TEXT,"
                                                                            +TableInfo.SERVING_SIZE+" TEXT"
                                                                            +TableInfo.MEAL_DIRECTIONS
                                                                            +" TEXT);";

    public SQLiteHelper(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations", "Meal Database Created");
    }//end of SQLiteHelper

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_T1_QUERY);
        Log.d("Database operations", "Table created");
    }//end of onCreate

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }//end of onUpgrade

    public void putInformation(SQLiteHelper dop, String meal_name, String meal_description, String prep_time,
                               String cook_time, String serving_size, String meal_directions) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.MEAL_NAME, meal_name);
        cv.put(TableInfo.MEAL_DESCRIPTION, meal_description);
        cv.put(TableInfo.PREP_TIME, prep_time);
        cv.put(TableInfo.COOK_TIME, cook_time);
        cv.put(TableInfo.SERVING_SIZE, serving_size);
        cv.put(TableInfo.MEAL_DIRECTIONS, meal_directions);

        long k = SQ.insert(TableInfo.TABLE_1_NAME, null, cv);
        Log.d("Database operations", "One Row Inserted In Meal");

    }//end of putInformation

    public Cursor getInformation(SQLiteHelper dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] t1_col = {TableInfo.MEAL_ID, TableInfo.MEAL_NAME, TableInfo.MEAL_DESCRIPTION,
                            TableInfo.PREP_TIME, TableInfo.COOK_TIME, TableInfo.SERVING_SIZE,
                            TableInfo.MEAL_DIRECTIONS};
        Cursor T1_CR = SQ.query(TableInfo.TABLE_1_NAME,t1_col, null, null, null, null, null);
        return T1_CR;
    }//end of getInformation

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

}//end of mainMethod







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
