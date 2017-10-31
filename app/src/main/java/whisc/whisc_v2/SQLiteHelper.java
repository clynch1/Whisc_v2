package whisc.whisc_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Connor on 10/30/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_recipe_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "PREP_TIME";
    public static final String COL_5 = "COOK_TIME";
    public static final String COL_6 = "SERVING_SIZE";
    public static final String COL_7 = "DIRECTIONS";

//    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DESCRIPTION TEXT,PREP_TIME TEXT,COOK_TIME TEXT,SERVING_SIZE TEXT,DIRECTIONS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String description, String prep_time, String cook_time, String serving_size, String directions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, prep_time);
        contentValues.put(COL_5, cook_time);
        contentValues.put(COL_6, serving_size);
        contentValues.put(COL_7, directions);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }
}
