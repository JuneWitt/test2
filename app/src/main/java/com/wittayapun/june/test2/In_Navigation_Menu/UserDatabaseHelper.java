package com.wittayapun.june.test2.In_Navigation_Menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserProfile_db";
    public static final String TABLE_NAME = "User_Profile";

    public static final String Uid = "UID";
    public static final String COL_1 = "FIRSTNAME";
    public static final String COL_2 = "LASTNAME";
    public static final String COL_3 = "GENDER";
    public static final String COL_4= "AGE";
    public static final String COL_5 = "WEIGHT";
    public static final String COL_6 = "HEIGHT";

    //private final String TAG = getClass().getSimpleName();

    SQLiteDatabase db;

    public UserDatabaseHelper(Context mcontext) {
        super(mcontext, DATABASE_NAME, null, DATABASE_VERSION);
        //this.mcontext = mcontext;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (UID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT, GENDER TEXT, AGE TEXT,WEIGHT TEXT,HEIGHT TEXT)");   //  USER //add tableName
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL( "DROP TABLE IF EXISTS "+TABLE_NAME);
        //Log.i(TAG, "Upgrade Database from " +  i + " to " + i1);
        //onCreate(db);
    }

    public boolean insertData(String fname, String lname, String gender, String age, String w, String h) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, fname);
        cv.put(COL_2, lname);
        cv.put(COL_3, gender);
        cv.put(COL_4, age);
        cv.put(COL_5, w);
        cv.put(COL_6, h);
        db.insert(TABLE_NAME, null, cv);
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        //Check
        if (result == -1) { return false; } else { return true; }
    }

    public Cursor getReadData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.rawQuery("select * from "+TABLE_NAME+ " where UID = 1 ",null);
        return query;
    }

    public boolean updateData(String UID, String fname, String lname, String gender, String age, String w, String h) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Uid,UID);
        cv.put(COL_1, fname);
        cv.put(COL_2, lname);
        cv.put(COL_3, gender);
        cv.put(COL_4, age);
        cv.put(COL_5, w);
        cv.put(COL_6, h);
        int result = db.update(TABLE_NAME, cv, "UID = ?",new String[] {UID});

        if (result == 0) { return false; } else { return true; }
    }


    public Cursor checkIfRecordExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = null;
        String checkQuery = "select "+Uid+" from "+TABLE_NAME;
        query = db.rawQuery(checkQuery,null);
        return query;
    }


    //public void deleteData() { }
}
