package com.wittayapun.june.test2.In_Navigation_Menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.sevenheaven.segmentcontrol.SegmentControl;
import com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat.female_lbm_Fragment;
import com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat.male_lbm_Fragment;
import com.wittayapun.june.test2.R;

public class Bodyfat_item3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyfat_item3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Lean Body Mass (LBM)");
        //.setBackgroundColor(Color.zzz);

        SegmentControl segment = (SegmentControl)findViewById(R.id.segment);

        segment.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Fragment fragment = null;
                if (index == 0) {
                    fragment = new male_lbm_Fragment();
                } else {
                    fragment = new female_lbm_Fragment();
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
