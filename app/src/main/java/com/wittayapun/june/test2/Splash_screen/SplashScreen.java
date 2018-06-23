package com.wittayapun.june.test2.Splash_screen;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.In_Navigation_Menu.UserActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.MainActivity;
import com.wittayapun.june.test2.R;

public class SplashScreen extends Activity {

    UserDatabaseHelper myDB;

    private Handler objhandler;
    private Runnable objrunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        objhandler = new Handler();

        objrunnable = new Runnable() {
            @Override
            public void run() {
                //Check Data is exist
                myDB = new UserDatabaseHelper(SplashScreen.this);
                Cursor recordExist = myDB.checkIfRecordExist();
                if (recordExist != null) {
                    if (recordExist.getCount() > 0) {
                        recordExist.close();
                        Intent intentToMain = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intentToMain);
                    } else {
                        Intent intentToCreateProfile = new Intent(SplashScreen.this, UserActivity.class);
                        startActivity(intentToCreateProfile);
                    }
                }
                finish();
            }
        };
    }

    protected void onResume() {
        super.onResume();
        objhandler.postDelayed(objrunnable, 1500);
    }

    public void onStop() {
        super.onStop();
        objhandler.removeCallbacks(objrunnable);
    }
}
