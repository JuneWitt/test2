package com.wittayapun.june.test2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ChestDetailActivity extends Activity {

    private DatabaseHelper databaseHelper;
    private TextView txtExerName,txtShowM_MainGroup,txtShowM_SecondGroup,txtShowEquipment,txtShowPosture,txtShowM_Contraction,txtShowRepSet,txtShowRestTime;
    long itemsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_detail);


        itemsID = getIntent().getExtras().getLong("ID",0);
        //Intent chestintent = getIntent();a
        //itemsID = chestintent.getExtras().getInt("ID");a

        //RECEIVE DATA FROM FragmentChest(context)

        //  ASSIGN DATA TO THOSE VIEWS
        //Toast.makeText(getApplicationContext(),"position = "+ id,Toast.LENGTH_SHORT).show();

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();



        //if (itemsID > 0) {
            Cursor detail = databaseHelper.QueryData("select * from Exer_Describtion");
            int rows = detail.getCount();
            detail.moveToFirst();


        /*for(int i=0;i<rows;i++) {

            int db_id = detail.getInt(1); //this belongs to the place where Your id is inside the db

            if (db_id == itemsID) {
                //here You can get all details you need

                break;
            }
        }*/

        init();
    }

    private void init() {
    }
}
