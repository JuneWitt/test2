package com.wittayapun.june.test2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ChestDetailActivity extends Activity {

    private DatabaseHelper databaseHelper;
    private TextView tvExerName,tvShowM_MainGroup,tvShowM_SecondGroup,tvShowEquipment,tvShowPosture,tvShowM_Contraction,tvShowRepSet,tvShowRestTime;
    long itemsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_detail);

        //RECEIVE DATA FROM FragmentChest
//        itemsID = getIntent().getStringExtra("ID");
        Toast.makeText(this, getIntent().getStringExtra("ID") + "", Toast.LENGTH_SHORT).show();

        Intent chestintent = getIntent();
        itemsID = chestintent.getExtras().getInt("ID");
        //itemsID = chestintent.getLongExtra("ID",0);

        //  ASSIGN DATA TO THOSE VIEWS
        tvExerName = findViewById(R.id.txtExerName);
        tvShowM_MainGroup = findViewById(R.id.txtShowM_MainGroup);
        tvShowM_SecondGroup = findViewById(R.id.txtShowM_SecondGroup);
        tvShowEquipment = findViewById(R.id.txtShowEquipment);
        tvShowPosture = findViewById(R.id.txtShowPosture);
        tvShowM_Contraction = findViewById(R.id.txtShowM_Contraction);
        tvShowRepSet = findViewById(R.id.txtShowRepSet);
        tvShowRestTime = findViewById(R.id.txtShowRestTime);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();
        databaseHelper.getReadableDatabase();
        Cursor detail = databaseHelper.QueryData("select * from Exer_Describtion");

        //int db_id = detail.getInt(1);
        if (itemsID > 0) {
            Toast pass = Toast.makeText(this, "Now your ID can use: " + itemsID, Toast.LENGTH_SHORT);
            pass.show();
            do {
                //tvExerName.setText(detail.getString(1));
                tvExerName.setText(detail.getString(detail.getColumnIndex("Exer_Name")));
                tvShowM_MainGroup.setText(detail.getString(detail.getColumnIndex("M_MainGroup")));
                tvShowM_SecondGroup.setText(detail.getString(detail.getColumnIndex("M_SecondaryGroup")));
                tvShowEquipment.setText(detail.getString(detail.getColumnIndex("Equipment")));
                tvShowPosture.setText(detail.getString(detail.getColumnIndex("Posture")));
                tvShowM_Contraction.setText(detail.getString(detail.getColumnIndex("M_Contraction")));
                tvShowRepSet.setText(detail.getString(detail.getColumnIndex("RepAndSet")));
                tvShowRestTime.setText(detail.getString(detail.getColumnIndex("RestTime")));
                //Toast.makeText(this,detail.getString(1)+"zzz",Toast.LENGTH_LONG).show();
            }
            while (detail.moveToNext());
        } else {
            Toast error = Toast.makeText(this, "Now cannot use your ID so sad ", Toast.LENGTH_SHORT);
            error.show();
        }
    }
}


        //detail.close();

        /*for(int i=0;i<rows;i++) {

            int db_id = detail.getInt(1); //this belongs to the place where Your id is inside the db

            if (db_id == itemsID) {
                //here You can get all details you need

                break;
            }*/


