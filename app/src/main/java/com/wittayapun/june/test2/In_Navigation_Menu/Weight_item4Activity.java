package com.wittayapun.june.test2.In_Navigation_Menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sevenheaven.segmentcontrol.SegmentControl;
import com.wittayapun.june.test2.In_Navigation_Menu.WeightForHeight.femaleWeightFragment;
import com.wittayapun.june.test2.In_Navigation_Menu.WeightForHeight.maleWeightFragment;
import com.wittayapun.june.test2.R;

public class Weight_item4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_item4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Body Weight Standard (BWS)");

        SegmentControl segmentControl = (SegmentControl) findViewById(R.id.segmentBWS);
        segmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Fragment fragment = null;
                if (index == 0) {
                    fragment = new maleWeightFragment();
                } else {
                    fragment = new femaleWeightFragment();
                }

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentPlace,fragment);
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
