package com.wittayapun.june.test2.In_Navigation_Menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sevenheaven.segmentcontrol.SegmentControl;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie.femaleCalorieFragment;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie.maleCalorieFragment;
import com.wittayapun.june.test2.R;

public class Calorie_item5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_item5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Basal Metabolic Rate (BMR)");

        SegmentControl segmentControl = (SegmentControl) findViewById(R.id.segmentCal);
        segmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Fragment fragment = null;
                if (index == 0) {
                    fragment = new maleCalorieFragment();
                } else {
                    fragment = new femaleCalorieFragment();
                }

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.placefragment,fragment);
                transaction.commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
