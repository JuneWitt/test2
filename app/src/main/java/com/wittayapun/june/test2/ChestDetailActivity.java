package com.wittayapun.june.test2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ChestDetailActivity extends Activity {

    private DatabaseHelper databaseHelper;
    private TextView tvExerName, tvShowM_MainGroup, tvShowM_SecondGroup, tvShowEquipment, tvShowPosture, tvShowM_Contraction, tvShowRepSet, tvShowRestTime;
    //private int db_id;
    //private String db_id;
    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_detail);
    }
        @Override
        public void onStart () {
            super.onStart();
            Intent intent = getIntent();
            String ID = intent.getStringExtra("ID");

            //RECEIVE DATA FROM FragmentChest //USE DATABASE
            databaseHelper = new DatabaseHelper(this);
            //databaseHelper.openDatabase();
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            String sql = "SELECT * FROM Exer_Describtion" +
                    " WHERE Exer_ID = " + ID;

            Cursor cursor = db.rawQuery(sql,null);
            //Cursor detail = databaseHelper.QueryData("select * from Exer_Describtion");

            //  ASSIGN DATA TO THOSE VIEWS
            tvExerName = findViewById(R.id.txtExerName);
            // GIFPATH Coming soon
            tvShowM_MainGroup = findViewById(R.id.txtShowM_MainGroup);
            tvShowM_SecondGroup = findViewById(R.id.txtShowM_SecondGroup);
            tvShowEquipment = findViewById(R.id.txtShowEquipment);
            tvShowPosture = findViewById(R.id.txtShowPosture);
            tvShowM_Contraction = findViewById(R.id.txtShowM_Contraction);
            tvShowRepSet = findViewById(R.id.txtShowRepSet);
            tvShowRestTime = findViewById(R.id.txtShowRestTime);

                if (cursor.moveToNext()) {
                //ID = bundle.getInt()
                    //int db_id = cursor.getInt(0);
                    Toast.makeText(this, "Now your ID is: " + ID, Toast.LENGTH_SHORT).show();

                    tvExerName.setText(cursor.getString(1));
                    //Gifpath.setImageBitmap(getImage(curcor.getBlob(3)));
                    tvShowM_MainGroup.setText(cursor.getString(3));
                    tvShowM_SecondGroup.setText(cursor.getString(4));
                    tvShowEquipment.setText(cursor.getString(5));
                    tvShowPosture.setText(cursor.getString(6));
                    tvShowM_Contraction.setText(cursor.getString(7));
                    tvShowRepSet.setText(cursor.getString(8));
                    tvShowRestTime.setText(cursor.getString(9));
                } else {
                    Toast.makeText(this,"Query fail try again",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
    }
}
    //  itemsID = getIntent().getStringExtra("ID");
    //Toast.makeText(this, getIntent().getStringExtra("ID") + "", Toast.LENGTH_SHORT).show();
    //itemsID = chestintent.getLongExtra("ID",0);

    //Intent chestintent = getIntent();
    //long _id = chestintent.getExtras().getInt("ID");








