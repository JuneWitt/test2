package com.wittayapun.june.test2;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout)findViewById(R.id.tablayout_id);
        appBarLayout = (AppBarLayout)findViewById(R.id.appbarid);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);
        viewPager.setOffscreenPageLimit(7);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //  Adding Fragment
        adapter.AddFragment(new FragmentChest(),"CHEST");
        adapter.AddFragment(new FragmentBack(),"BACK");
        adapter.AddFragment(new FragmentShoulder(),"SHOULDERS");
        adapter.AddFragment(new FragmentAbs(),"ABS");
        adapter.AddFragment(new FragmentArms(),"ARMS");
        adapter.AddFragment(new FragmentLeg(),"LEGS");
        adapter.AddFragment(new FragmentCalf(),"CALF");
        //adapter Setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
