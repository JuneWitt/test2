package com.wittayapun.june.test2.In_Navigation_Menu;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.MainActivity;
import com.wittayapun.june.test2.R;

import java.util.ArrayList;
import java.util.List;

public class Suggest_itemActivity extends AppCompatActivity {

    UserDatabaseHelper dbHelper;
    TextView tvDes, tvdetail;
    ImageView imvfat;
    Button suggestExer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Exercise Recommend");
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dbHelper = new UserDatabaseHelper(this);
    }

    private void showDetailInstance() {
        Cursor res = dbHelper.getReadData();
        //Cursor res = dbhelper.rawQuery("select * from User ",null);
        if (res.getCount() == 0) {
            // show message
           Toast.makeText(this,"Error, Nothing found data",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("FirstName: " + res.getString(1) + "\n");
            buffer.append("Surname: " + res.getString(2) + "\n");
            buffer.append("Gender:" + res.getString(3) + "\n");
            buffer.append("Age: " + res.getString(4) + "\n");
            buffer.append("Weight: " + res.getString(5) + "\n");
            buffer.append("Height: " + res.getString(6));
        }

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add(res.getString(2));
        comingSoon.add("The Smurfs 2");

        tvDes = findViewById(R.id.tvDescribtion);
        tvDes = new TextView(this);
        tvDes.setText(buffer.toString());
        setContentView(tvdetail);

        tvdetail = findViewById(R.id.tvDetail);
        imvfat = findViewById(R.id.imvShape);

        //  for intent
        final int forAge = Integer.parseInt(res.getString(4));

        if (tvDes != null) {
            double w =  Integer.parseInt(res.getString(5));
            double h = Integer.parseInt(res.getString(6));
            String sex = res.getString(3);

            h = h / 100;  //  cm => m
            double ans = w / (h * h);

            String shape,thin1,thin2,thin3,good,fat1,fat2,fat3;
             shape = "รูปร่างของคุณ: ";
             thin1 = "ผอม";
             thin2 = "ค่อนข้างผอม";
             thin3 = "ผอมมาก";
             good = "สมส่วน ";
             fat1 = "ค่อนข้างผอม";
             fat2 = "ค่อนข้างผอม";
             fat3 = "อ้วนมาก";

            if (ans < 16.00) {
                tvdetail.setText(shape + thin3);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape1);}else{ imvfat.setImageResource(R.drawable.Fshape1);}

            } else if (ans < 17.00) {
                tvdetail.setText(shape + thin2);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape1);}else{ imvfat.setImageResource(R.drawable.Fshape1);}

            } else if (ans < 18.50) {
                tvdetail.setText(shape + thin1);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape1);}else{ imvfat.setImageResource(R.drawable.Fshape1);}

            } else if (ans < 23.00) {
                tvdetail.setText(shape + good);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape2);}else{ imvfat.setImageResource(R.drawable.Fshape2);}

            } else if (ans < 25.00) {
                tvdetail.setText(shape + thin2);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape3);}else{ imvfat.setImageResource(R.drawable.Fshape3);}

            } else if (ans < 30.00) {
                tvdetail.setText(shape + fat1);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape3);}else{ imvfat.setImageResource(R.drawable.Fshape3);}

            } else if (ans < 40.00) {
                tvdetail.setText(shape + fat2);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape3);}else{ imvfat.setImageResource(R.drawable.Fshape3);}

            } else if (ans > 39.99) {
                tvdetail.setText(shape + fat3);
                if (sex == "ชาย"){ imvfat.setImageResource(R.drawable.Mshape3);}else{ imvfat.setImageResource(R.drawable.Fshape3);}
            }
        }

        suggestExer = findViewById(R.id.btnExerSuggest);

        showDetailInstance();
        suggestExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Suggest_itemActivity.this,ShowExerFromSuggestActivity.class);
                intent.putExtra("Data from age",forAge+"");
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}