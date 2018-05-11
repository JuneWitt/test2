package com.wittayapun.june.test2.In_Navigation_Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.R;

import java.text.DecimalFormat;

public class Bmi_item2Activity extends AppCompatActivity {

    private EditText edtWeight, edtHeight;
    private Button result, reset;
    private double h, w;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String weightInput = edtWeight.getText().toString().trim();
            String heightInput = edtHeight.getText().toString().trim();

            result.setEnabled(!weightInput.isEmpty() && !heightInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_item2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Body Mass Index (BMI)");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        edtWeight = findViewById(R.id.edt1);
        edtHeight = findViewById(R.id.edt2);
        final TextView tvresult = findViewById(R.id.tvresult);
        result = findViewById(R.id.btnresult);
        reset = findViewById(R.id.btnreset);

        edtWeight.addTextChangedListener(textWatcher);
        edtHeight.addTextChangedListener(textWatcher);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                w = Integer.parseInt(edtWeight.getText().toString());
                h = Integer.parseInt(edtHeight.getText().toString());

                    h = h / 100;  //  cm => m}
                    double ans = w / (h * h);
                    DecimalFormat decimalFormat = new DecimalFormat("##.##");

                    if (ans < 16.00) {
                        tvresult.setText("BMI: " + decimalFormat.format(ans) + "\n" + "ผอม (Underweight)\n" + "น้ำหนักน้อยระดับ 3");
                    } else if (ans < 17.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ผอม (Underweight)\n" + "น้ำหนักน้อยระดับ 2");
                    } else if (ans < 18.50) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ผอม (Underweight)\n" + "น้ำหนักน้อยระดับ 1");
                    } else if (ans < 23.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ค่าน้ำหนักปกติ");
                    } else if (ans < 25.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "ค่าน้ำหนักเกิน (Overweight)");
                    } else if (ans < 30.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "อ้วน (Obesity)\n" + "โรคอ้วนระดับ 1");
                    } else if (ans < 40.00) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "อ้วน (Obesity)\n" + "โรคอ้วนระดับ 2");
                    } else if (ans > 39.99) {
                        tvresult.setText("ค่า BMI คือ: " + decimalFormat.format(ans) + "\n" + "อ้วน (Obesity)\n" + "โรคอ้วนระดับ 3\n" + "เสี่ยงต่อความดันโลหิตสูง\nไขมันในเลือดสูง\nข้อเข่าเสื่อม\nนิ่วในถุงน้ำดี\nตับอักเสบจากไขมันสะสม\n เบาหวาน...");
                    }
                    return;
                }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtWeight.getText().clear();
                edtHeight.getText().clear();
                tvresult.setText(null);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
