package com.wittayapun.june.test2.In_Navigation_Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.R;

import java.text.DecimalFormat;

public class Bmi_item2Activity extends AppCompatActivity {

    private double h, w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_item2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText edtWeight = findViewById(R.id.edt1);
        final EditText edtHeight = findViewById(R.id.edt2);
        final TextView tvresult = findViewById(R.id.tvresult);
        final TextView check1 = findViewById(R.id.tvError1);
        final TextView check2 = findViewById(R.id.tvError2);
        Button result = findViewById(R.id.btnresult);
        Button reset = findViewById(R.id.btnreset);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Check null
                if (edtWeight.getText().toString().length() < 1) {
                    check1.setVisibility(EditText.VISIBLE);
                    check2.setVisibility(EditText.GONE);
                    tvresult.setText(null);
                    Toast.makeText(getApplicationContext(),"if1",Toast.LENGTH_SHORT).show();

                    if (edtHeight.getText().toString().length() < 1) {
                        //check1.setVisibility(EditText.INVISIBLE);
                        check2.setVisibility(EditText.VISIBLE);
                        Toast.makeText(getApplicationContext(), "if1.1", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                if (edtHeight.getText().toString().length() < 1) {
                    check2.setVisibility(EditText.VISIBLE);
                    check1.setVisibility(EditText.GONE);
                    tvresult.setText(null);
                    Toast.makeText(getApplicationContext(),"if2",Toast.LENGTH_SHORT).show();

                    if (edtWeight.getText().toString().length() < 1) {
                        check1.setVisibility(EditText.VISIBLE);
                        Toast.makeText(getApplicationContext(),"if2.2",Toast.LENGTH_SHORT).show();

                    }
                    return;
                }

                    double w = Integer.parseInt(edtWeight.getText().toString());
                    double h = Integer.parseInt(edtHeight.getText().toString());

                    h = h / 100;  //  cm => m}
                    double ans = w / (h * h);
                    DecimalFormat decimalFormat = new DecimalFormat("##.##");

                    if (ans < 16.00) {
                        tvresult.setText("BMI: " + decimalFormat.format(ans) + "\n" + "ผอม (Underweight)\n" + "น้ำหนักน้อยระดับ 3");
                        check1.setVisibility(TextView.GONE);
                        check2.setVisibility(TextView.GONE);
                    } else if (ans < 17.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ผอม (Underweight)\n" + "น้ำหนักน้อยระดับ 2");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    } else if (ans < 18.50) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ผอม (Underweight)\n" + "น้ำหนักน้อยระดับ 1");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    } else if (ans < 23.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ค่าน้ำหนักปกติ");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    } else if (ans < 25.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ค่าน้ำหนักเกิน (Overweight)");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    } else if (ans < 30.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "อ้วน (Obesity)\n" + "โรคอ้วนระดับ 1");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    } else if (ans < 40.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "อ้วน (Obesity)\n" + "โรคอ้วนระดับ 2");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    } else if (ans > 39.99) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "อ้วน (Obesity)\n" + "โรคอ้วนระดับ 3\n" + "เสี่ยงต่อความดันโลหิตสูง\nไขมันในเลือดสูง\nข้อเข่าเสื่อม\nนิ่วในถุงน้ำดี\nตับอักเสบจากไขมันสะสม\n เบาหวาน...");
                        check1.setVisibility(TextView.INVISIBLE);
                        check2.setVisibility(TextView.INVISIBLE);
                    }
                    return;
                }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtWeight.getText().clear();
                edtHeight.getText().clear();
                check1.setVisibility(EditText.GONE);
                check2.setVisibility(EditText.GONE);
                tvresult.setText(null);
            }
        });

            /*try {
                w = Double.parseDouble(edtWeight.getText().toString());
            } catch (NumberFormatException e) {
            }
            try {
                h = Double.parseDouble(edtHeight.getText().toString());
            } catch (NumberFormatException e) {
            }
             else if (edtWeight.getText().toString() == null || edtWeight.getText().length() == 0){
                    check1.setVisibility(EditText.VISIBLE);
                } else if (edtHeight.getText().toString() == null || edtHeight.getText().length() == 0) {
                    check2.setVisibility(EditText.VISIBLE);
                } else {
                    tvresult.setText("กรุณากรอกข้อมูลให้ครบ");
                }*/
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
