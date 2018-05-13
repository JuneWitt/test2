package com.wittayapun.june.test2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "Exer_Name_list.db";
    private static String DB_PATH = "";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 16) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
        /*
        NEW


         */
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void checkAndCopyDatabase() {
        boolean dbExist = checkDatabase();
        if (dbExist) {
            Log.d("TAG", "database already exist");
        } else {
            this.getReadableDatabase();
        }
        try {
            copyDatabase();
        } catch (IOException e) {
            Log.d("TAG", "Error copy database");
        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLiteException e) {
        }
            if (checkDB !=null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void copyDatabase()throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase(){
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public synchronized void close(){
        if (myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query){
        return myDatabase.rawQuery(query, null);
    }


}

