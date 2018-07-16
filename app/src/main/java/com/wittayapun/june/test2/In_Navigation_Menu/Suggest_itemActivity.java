package com.wittayapun.june.test2.In_Navigation_Menu;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.ChestDetailActivity;
import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.ExpandableListAdapter.ChildItem;
import com.wittayapun.june.test2.ExpandableListAdapter.ExpandableListAdapter;
import com.wittayapun.june.test2.ExpandableListAdapter.GroupItem;
import com.wittayapun.june.test2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Suggest_itemActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Exercise Recommend");

        //  Get intent
        Intent intentForAge = getIntent();
        int Age = intentForAge.getIntExtra("Data from age",0);

        TextView showSuggest = findViewById(R.id.tvDes);

        if (Age <=0) { showSuggest.setText(Html.fromHtml("<b>อายุของท่านไม่ถูกต้อง ,โปรดใส่อายุที่เป็นความจริง</b>"));// preparing list data in expandable listView
        } else if (Age < 15 ){showSuggest.setText(Html.fromHtml("<b>     ในช่วงอายุของท่าน การเจริญเติบโต และพัฒนาการด้านร่างกายยังไม่แข็งแรงมาก แต่สามารถเล่นได้ทุกท่าด้วยน้ำหนักเบาถึงเบามาก</b>"));prepareListData();
        } else if (Age > 15 && Age <=39) { showSuggest.setText(Html.fromHtml("<b>        ในช่วงอายุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อแน่น และมวลกระดูก อยู่ในระดับดีมาก สามารถเล่นได้ทุกท่า ้วยใช้น้ำหนักมากได้</b>"));prepareListData();
        } else if (Age > 39 && Age <=55) {showSuggest.setText(Html.fromHtml("<b>         ในช่วงอายุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อ และมวลกระดูก อยู่ในระดับปานกลาง สามารถเล่นได้ทุกท่า และควรใช้น้ำหนักปานกลาง</b>"));prepareListData();
        } else if (Age > 55 && Age <65) { showSuggest.setText(Html.fromHtml("<b>        ในช่วงอายุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อ และมวลกระดูก อยู่ในระดับค่อนข้างน้อย สามารถเล่นได้ทุกท่า แต่ควรใช้น้ำหนักน้อยถึงปานกลาง</b>"));prepareListData();
        } else if (Age >64) { showSuggest.setText(Html.fromHtml("<b>         ในช่วงอายุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อ และมวลกระดูก อยู่ในระดับน้อยถึงน้อยมาก สามารถเล่นได้บางท่า และต้องใช้น้ำหนักน้อยถึงน้อยมาก</b>")); prepareListDatafor65Plus();
        }
    }

    private void prepareListData() {

        // get the listview
        ExpandableListView expListView = findViewById(R.id.ExpandLV);

        final ArrayList<GroupItem> groupArray = new ArrayList<>();
        GroupItem groupItem;
        String groupName;
        ArrayList<ChildItem> childArrayList;

        groupName = "CHEST";
        childArrayList = new ArrayList<>();

        dbHelper = new DatabaseHelper(this);
        dbHelper.openDatabase();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Chest'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    //childArrayList.addAll(Arrays.asList(item));
                    //childArrayList.addAll(Collections.<ChildItem>emptyList());
                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "BACK";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Back'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "SHOULDERS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Shoulder'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "ABS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Abs'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "ARMS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Arms'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "LEGS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Leg'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "CALF";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Calf'");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

//  Adapter
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groupArray);
        expListView.setAdapter(adapter);





     // On ChildClick
        final ArrayList<ChildItem> ChildArrayList = childArrayList;    // ChildArrayList is add final
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int gPos, int cPos, long l) {
                //String item = groupArray.get(gPos).childItems.get(cPos).text;
                Intent intent = new Intent(Suggest_itemActivity.this, ChestDetailActivity.class);
                intent.putExtra("ID",groupArray.get(gPos).childItems.get(cPos).get_id()+"");
                startActivity(intent);
                return true;
            }
        });
    }

    private void prepareListDatafor65Plus() {

        // get the listview
        ExpandableListView expListView = findViewById(R.id.ExpandLV);

        final ArrayList<GroupItem> groupArray = new ArrayList<>();
        GroupItem groupItem;
        String groupName;
        ArrayList<ChildItem> childArrayList;

        groupName = "CHEST";
        childArrayList = new ArrayList<>();

        dbHelper = new DatabaseHelper(this);
        dbHelper.openDatabase();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Chest' and Exer_ID in (2, 5, 6, 7, 8)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "BACK";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Back' and Exer_ID in (12, 13)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "SHOULDERS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Shoulder' and Exer_ID in (17, 20)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "ABS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Abs' and Exer_ID in (22, 26)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "ARMS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Arms' and Exer_ID in (27, 28 ,31 ,32)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "LEGS";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Leg' and Exer_ID in (34, 35)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

        groupName = "CALF";
        childArrayList = new ArrayList<>();

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Calf' and Exer_ID in (37)");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    ChildItem item = new ChildItem();
                    item.set_id(cursor.getInt(0));
                    item.setText(cursor.getString(1));

                    childArrayList.add(item);

                } while (cursor.moveToNext());
            }
        }
        groupItem = new GroupItem(groupName, childArrayList);
        groupArray.add(groupItem);

//  Adapter
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groupArray);
        expListView.setAdapter(adapter);

        // On ChildClick
        final ArrayList<ChildItem> ChildArrayList = childArrayList;    // ChildArrayList is add final
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int gPos, int cPos, long l) {
                //String item = groupArray.get(gPos).childItems.get(cPos).text;
                Intent intent = new Intent(Suggest_itemActivity.this, ChestDetailActivity.class);
                intent.putExtra("ID",groupArray.get(gPos).childItems.get(cPos).get_id()+"");
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}