package com.wittayapun.june.test2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wittayapun.june.test2.In_Navigation_Menu.Bmi_item2Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat_item3Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie_item5Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Suggest_itemActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.Weight_item4Activity;

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
