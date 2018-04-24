package com.wittayapun.june.test2;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wittayapun.june.test2.In_Navigation_Menu.Bmi_item2Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat_item3Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie_item5Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Suggest_itemActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.Weight_item4Activity;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private FloatingActionsMenu floatingActionsMenu;
    //private View shadowView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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

        // open new activity
        final com.getbase.floatingactionbutton.FloatingActionButton Suggest_bt = findViewById(R.id.fab1);
        Suggest_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Suggest_itemActivity.class);
                startActivity(intent);
            }
        });

        final com.getbase.floatingactionbutton.FloatingActionButton bmi_bt = findViewById(R.id.fab2);
        bmi_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Bmi_item2Activity.class);
                startActivity(intent);
            }
        });

        final com.getbase.floatingactionbutton.FloatingActionButton bodyfat_bt = findViewById(R.id.fab3);
        bodyfat_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Bodyfat_item3Activity.class);
                startActivity(intent);
            }
        });

        final com.getbase.floatingactionbutton.FloatingActionButton weight_bt = findViewById(R.id.fab4);
        weight_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Weight_item4Activity.class);
                startActivity(intent);
            }
        });

        final com.getbase.floatingactionbutton.FloatingActionButton cal_bt = findViewById(R.id.fab5);
        cal_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Calorie_item5Activity.class);
                startActivity(intent);
            }
        });


     // FloatingButton
        setFloatingButtonControls();
    }
//  SHADOW
    private void setFloatingButtonControls() {
        final View v1 = (View) findViewById(R.id.shadowView);

        this.floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions_down);
        this.floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                v1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onMenuCollapsed() {
                v1.setVisibility(View.GONE);
            }
        });
    }
}
