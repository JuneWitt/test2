package com.wittayapun.june.test2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;

import com.wittayapun.june.test2.In_Navigation_Menu.Bmi_item2Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat_item3Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie_item5Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Suggest_itemActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.UserActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.Weight_item4Activity;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

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

//Navigation drawer
        initInstances();
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open, R.string.close);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        nvDrawer = findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.user_activity:
                        Intent user = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(user);
                        break;
                    case R.id.suggest:
                        // user check
                        Intent suggest = new Intent(MainActivity.this,Suggest_itemActivity.class);
                        startActivity(suggest);
                        break;
                    case R.id.bmi:
                        Intent bmi = new Intent(MainActivity.this,Bmi_item2Activity.class);
                        startActivity(bmi);
                        break;
                    case R.id.fatpercentage:
                        Intent fat = new Intent(MainActivity.this,Bodyfat_item3Activity.class);
                        startActivity(fat);
                        break;
                    case R.id.weight:
                        Intent weight = new Intent(MainActivity.this,Weight_item4Activity.class);
                        startActivity(weight);
                        break;
                    case R.id.calorie:
                        Intent calorie = new Intent(MainActivity.this,Calorie_item5Activity.class);
                        startActivity(calorie);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        /*switch (item.getItemId()){
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);*/
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 1500 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
