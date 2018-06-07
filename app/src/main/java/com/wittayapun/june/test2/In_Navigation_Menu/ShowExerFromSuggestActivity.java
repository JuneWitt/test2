package com.wittayapun.june.test2.In_Navigation_Menu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wittayapun.june.test2.ChestDetailActivity;
import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.R;
import com.wittayapun.june.test2.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowExerFromSuggestActivity extends Activity {

    String show;
    //SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;

    TextView showSuggest;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;    // Store header data.
    HashMap<String, List<String>> listDataChild;    // Store child data. Key is the group value, Value is the child data in a list.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exer_from_suggest);
/*
        //  Get intent
        Intent intentForAge = getIntent();
        int Age = intentForAge.getIntExtra("Data from age",0);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.ExpandLV);
        showSuggest = findViewById(R.id.tvDes);

        //int forAge = Integer.parseInt(res.getString(4));
        //String show;
        if (Age <=0) { showSuggest.setText("Error Data(age) "+",No exercise recommend for you");prepareListData();// preparing list data in expandable listView
        } else if (Age < 15 ){showSuggest.setText(" ในช่วงอยุของท่าน การเจริญเติบโต และพัฒนาการด้านร่างกายยังไม่แข็งแรงมาก แต่สามารถเล่นได้ทุกท่า ด้วยน้ำหนักเบาถึงเบามาก");prepareListData();
        } else if (Age > 15 && Age <=39) { showSuggest.setText(" ในช่วงอยุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อแน่น และมวลกระดูก อยู่ในระดับดีมาก สามารถเล่นได้ทุกท่า โดยใช้น้ำหนักมากได้");prepareListData();
        } else if (Age > 39 && Age <=55) {showSuggest.setText(" ในช่วงอยุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อ และมวลกระดูก อยู่ในระดับปานกลาง สามารถเล่นได้ทุกท่า และควรใช้น้ำหนักปานกลาง");prepareListData();
        } else if (Age > 55 && Age <=64) { showSuggest.setText(" ในช่วงอยุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อ และมวลกระดูก อยู่ในระดับค่อนข้างน้อย สามารถเล่นได้ทุกท่า แต่ควรใช้น้ำหนักน้อยถึงปานกลาง");prepareListData();
        } else if (Age >65) { showSuggest.setText(" ในช่วงอยุของท่าน มีความแข็งแรงของกล้ามเนื้อ มวลกล้ามเนื้อ และมวลกระดูก อยู่ในระดับน้อยถึงน้อยมาก สามารถเล่นได้บางท่า และต้องใช้น้ำหนักน้อยถึงน้อยมาก"); prepareListDatafor65Plus();}

        /* Create an ExpandableListAdapter object, this object
        will be used to provide data to ExpandableListView.
        listAdapter = new com.wittayapun.june.test2.ExpandableListAdapter.ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(ShowExerFromSuggestActivity.this, ChestDetailActivity.class);
                intent.putExtra("ID",childPosition);
                return true;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Header
        listDataHeader.add("CHEST");
        listDataHeader.add("BACK");
        listDataHeader.add("SHOULDERS");
        listDataHeader.add("ABS");
        listDataHeader.add("ARMS");
        listDataHeader.add("LEGS");
        listDataHeader.add("CALF");

        dbHelper = new DatabaseHelper(this);
        try {
            //databaseHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        // Adding child of data
        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Chest'");
        List<String> childChest = new ArrayList<String>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));

                    childChest.add(item);

                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Back'");
        List<Item> childBack = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));

                    childBack.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Shoulders'");
        List<Item> childShoulder = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childShoulder.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Abs'");
        List<Item> childAbs = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childAbs.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Arms'");
        List<Item> childArm = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childArm.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Legs'");
        List<Item> childLeg = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childLeg.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Calf'");
        List<Item> childCalf = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childCalf.add(item);
                } while (cursor.moveToNext());
            }
        }

        listDataChild.put(listDataHeader.get(0), childChest);
        listDataChild.put(listDataHeader.get(1), childBack);
        listDataChild.put(listDataHeader.get(2), childShoulder);
        listDataChild.put(listDataHeader.get(3), childAbs);
        listDataChild.put(listDataHeader.get(4), childArm);
        listDataChild.put(listDataHeader.get(5), childLeg);
        listDataChild.put(listDataHeader.get(6), childCalf);
    }

    private void prepareListDatafor65Plus() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Item>>();

        // Adding Header
        listDataHeader.add("CHEST");
        listDataHeader.add("BACK");
        listDataHeader.add("SHOULDERS");
        listDataHeader.add("ABS");
        listDataHeader.add("ARMS");
        listDataHeader.add("LEGS");
        listDataHeader.add("CALF");

        dbHelper = new DatabaseHelper(this);
        try {
            //databaseHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        // Adding child of data
        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Chest' and Exer_ID = 2, 5, 6, 7, 8 ");
        List<Item> childChest = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));

                    childChest.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Back' and Exer_ID = 12, 13 ");
        List<Item> childBack = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));

                    childBack.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Shoulders' and Exer_ID = 17, 20 ");
        List<Item> childShoulder = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childShoulder.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Abs' and Exer_ID = 22, 26 ");
        List<Item> childAbs = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childAbs.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Arms' and Exer_ID = 27, 28, 31, 32 ");
        List<Item> childArm = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childArm.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Legs' and Exer_ID = 36, 37 ");
        List<Item> childLeg = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childLeg.add(item);
                } while (cursor.moveToNext());
            }
        }

        cursor = dbHelper.QueryData("select * from Exer_list where M_Group = 'Calf' and Exer_ID = 39 ");
        List<Item> childCalf = new ArrayList<Item>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setExer_name(cursor.getString(1));
                    childCalf.add(item);
                } while (cursor.moveToNext());
            }
        }

        listDataChild.put(listDataHeader.get(0), childChest);
        listDataChild.put(listDataHeader.get(1), childBack);
        listDataChild.put(listDataHeader.get(2), childShoulder);
        listDataChild.put(listDataHeader.get(3), childAbs);
        listDataChild.put(listDataHeader.get(4), childArm);
        listDataChild.put(listDataHeader.get(5), childLeg);
        listDataChild.put(listDataHeader.get(6), childCalf);
    }

    */
    }
}

