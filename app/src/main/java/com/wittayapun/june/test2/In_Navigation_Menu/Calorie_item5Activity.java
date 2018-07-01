package com.wittayapun.june.test2.In_Navigation_Menu;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.sevenheaven.segmentcontrol.SegmentControl;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie.femaleCalorieFragment;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie.maleCalorieFragment;
import com.wittayapun.june.test2.R;

public class Calorie_item5Activity extends AppCompatActivity {
    private UserDatabaseHelper userDB;
    String sex = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_item5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Basal Metabolic Rate (BMR)");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        userDB = new UserDatabaseHelper(this);

        Cursor cursor = userDB.getReadData();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    sex = cursor.getString(3);
                } while (cursor.moveToNext());
            }
        }
        onOpenWindow();
    }

    private void onOpenWindow() {

        Fragment fragment = null;

        if (sex.equals("ชาย")) {
            fragment = new maleCalorieFragment();
        } else {
            fragment = new femaleCalorieFragment();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.placefragment,fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        this.finish();
        return true;
    }
}
