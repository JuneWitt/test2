package com.wittayapun.june.test2;

import android.content.Intent;
import android.database.Cursor;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.In_Navigation_Menu.ActivityWhenHaveUser.EditDetailActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.Bmi_item2Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat_item3Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Calorie_item5Activity;
import com.wittayapun.june.test2.In_Navigation_Menu.Suggest_itemActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.UserActivity;
import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.In_Navigation_Menu.Weight_item4Activity;
import com.wittayapun.june.test2.item.imageViewpager.ImageAbsFragment;
import com.wittayapun.june.test2.item.imageViewpager.ImageArmsFragment;
import com.wittayapun.june.test2.item.imageViewpager.ImageBackFragment;
import com.wittayapun.june.test2.item.imageViewpager.ImageCalfFragment;
import com.wittayapun.june.test2.item.imageViewpager.ImageChestFragment;
import com.wittayapun.june.test2.item.imageViewpager.ImageLegFragment;
import com.wittayapun.june.test2.item.imageViewpager.ImageShoulderFragment;


public class MainActivity extends AppCompatActivity {

    UserDatabaseHelper mydb;
    //NAV
    TextView n1,n2,n3,n4,n5,n11;
    int forAge;

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager,imvviewPager;

    private DrawerLayout mDrawer;
    public NavigationView nvDrawer;
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
        ViewPagerAdapter adapter2 = new ViewPagerAdapter(getSupportFragmentManager());
        //  Adding Fragment
        adapter.AddFragment(new FragmentChest(),"CHEST");
        adapter.AddFragment(new FragmentBack(),"BACK");
        adapter.AddFragment(new FragmentShoulder(),"SHOULDERS");
        adapter.AddFragment(new FragmentAbs(),"ABS");
        adapter.AddFragment(new FragmentArms(),"ARMS");
        adapter.AddFragment(new FragmentLeg(),"LEGS");
        adapter.AddFragment(new FragmentCalf(),"CALF");

        imvviewPager = findViewById(R.id.imvViewpager);

        adapter2.AddFragment(new ImageChestFragment(),"CHEST");
        adapter2.AddFragment(new ImageBackFragment(),"Back");
        adapter2.AddFragment(new ImageShoulderFragment(),"SHOULDERS");
        adapter2.AddFragment(new ImageAbsFragment(),"ABS");
        adapter2.AddFragment(new ImageArmsFragment(),"ARMS");
        adapter2.AddFragment(new ImageLegFragment(),"LEGS");
        adapter2.AddFragment(new ImageCalfFragment(),"CALF");

        //adapter Setup
        viewPager.setAdapter(adapter);
        imvviewPager.setAdapter(adapter2);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int  mScrollState = ViewPager.SCROLL_STATE_IDLE;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mScrollState == ViewPager.SCROLL_STATE_IDLE){
                    return;
                }imvviewPager.scrollTo(viewPager.getScrollX(),imvviewPager.getScrollY());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    imvviewPager.setCurrentItem(viewPager.getCurrentItem(),false);
                }
            }
        });
        imvviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int mScrollState = ViewPager.SCROLL_STATE_IDLE;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                    return;
                }
                viewPager.scrollTo(imvviewPager.getScrollX(), viewPager.getScrollY());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.setCurrentItem(imvviewPager.getCurrentItem(), false);
                }
            }
        });



//Navigation drawer
        nvDrawer = findViewById(R.id.nvView);
        View header = nvDrawer.getHeaderView(0);
        mydb = new UserDatabaseHelper(this);

        // NAV
        n1 = header.findViewById(R.id.FirstShow);
        n11 = header.findViewById(R.id.LastShow);
        n2 = header.findViewById(R.id.genderShow);
        n3 = header.findViewById(R.id.ageShow);
        n4 = header.findViewById(R.id.WShow);
        n5 = header.findViewById(R.id.HShow);

        initInstances();

        ImageButton ib = (ImageButton)header.findViewById(R.id.imvbtnEdit);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(getApplicationContext(),EditDetailActivity.class);
                startActivity(edit);
            }
        });
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open, R.string.close)
        {
            public void onDrawerOpened(View drawerView)
            {
                Cursor res = mydb.getReadData();

                if (res != null && res.moveToFirst()) {
                    n1.setText(res.getString(1));
                    n11.setText(res.getString(2));
                    n2.setText(res.getString(3));
                    n3.setText(res.getString(4));
                    forAge = Integer.parseInt(res.getString(4));
                    n4.setText(res.getString(5));
                    n5.setText(res.getString(6));
                    res.close();
                }
            }
        };
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        //nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.user_activity:
                        // user check
                        Intent user = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(user);

                        break;

                    case R.id.suggest:
                        Intent suggest = new Intent(MainActivity.this,Suggest_itemActivity.class);
                        suggest.putExtra("Data from age",forAge);
                        Toast.makeText(MainActivity.this,"intent = "+forAge,Toast.LENGTH_SHORT).show();
                        mDrawer.closeDrawer(GravityCompat.START);
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
