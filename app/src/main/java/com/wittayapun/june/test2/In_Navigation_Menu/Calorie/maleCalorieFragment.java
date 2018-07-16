package com.wittayapun.june.test2.In_Navigation_Menu.Calorie;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class maleCalorieFragment extends Fragment {
    UserDatabaseHelper userDB;
    private EditText edtAge,edtW,edtH;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4,rb5;
    private Button reset,result;
    private TextView tvresult;
    private double age,w,h;

    public maleCalorieFragment() {
        // Required empty public constructor
    }

    private TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
/*
            String ageInput = edtAge.getText().toString().trim();
            String weightInput = edtW.getText().toString().trim();
            String heightInput = edtH.getText().toString().trim();

            result.setEnabled(!ageInput.isEmpty() &&!weightInput.isEmpty() && !heightInput.isEmpty());
            */
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_male_calorie, container, false);

        edtAge = view.findViewById(R.id.edt1);
        edtW = view.findViewById(R.id.edt2);
        edtH = view.findViewById(R.id.edt3);
        radioGroup = view.findViewById(R.id.RdG);
        rb1 = view.findViewById(R.id.rdbt1);
        rb2 = view.findViewById(R.id.rdbt2);
        rb3 = view.findViewById(R.id.rdbt3);
        rb4 = view.findViewById(R.id.rdbt4);
        rb5 = view.findViewById(R.id.rdbt5);
        reset = view.findViewById(R.id.btnreset);
        result = view.findViewById(R.id.btnresult);
        tvresult = view.findViewById(R.id.tvresult);

        userDB = new UserDatabaseHelper(getActivity());

        onWindowStart();

        edtAge.addTextChangedListener(textWatcher);
        edtW.addTextChangedListener(textWatcher);
        edtH.addTextChangedListener(textWatcher);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(result.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
  //CALCULATE
                try{
                age =Integer.parseInt(edtAge.getText().toString().trim());
                }catch (NumberFormatException e){
                }
                try {
                    w = Integer.parseInt(edtW.getText().toString().trim());
                }catch (NumberFormatException e){
                }
                try {
                    h = Integer.parseInt(edtH.getText().toString().trim());
                }catch (NumberFormatException e){
                }
                //66 + (13.7 x น้ำหนักตัว (กิโลกรัม))+(5 x ส่วนสูง (เซนติเมตร))-(6.8 x อายุ)
                double BMR = 66+(13.7*w)+(5*h)-(6.8*age);

                double activity1 = BMR*1.2;
                double activity2 = BMR*1.375;
                double activity3 = BMR*1.55;
                double activity4 = BMR*1.725;
                double activity5 = BMR*1.9;
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
/*
                if (edtAge.getText() == null || edtAge.getText().toString().equals("") || edtW.getText() == null || edtW.getText().toString().equals("")
                        || edtH.getText() == null || edtH.getText().toString().equals("") ){
                    tvresult.setText("กรุณากรอกข้อมูลให้ครบ");*/


                //if (edtAge.getText() != null || edtW.getText() != null || edtH.getText() != null){
                    //tvresult.setText("กรุณากรอกข้อมูลให้ครบ");
                    if (rb1.isChecked()) {
                        tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ " + decimalFormat.format(activity1) + " กิโลแคลอรี่");
                    } else if (rb2.isChecked()) {
                        tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ " + decimalFormat.format(activity2) + " กิโลแคลอรี่");
                    } else if (rb3.isChecked()) {
                        tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ " + decimalFormat.format(activity3) + " กิโลแคลอรี่");
                    } else if (rb4.isChecked()) {
                        tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ " + decimalFormat.format(activity4) + " กิโลแคลอรี่");
                    } else if (rb5.isChecked()) {
                        tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ " + decimalFormat.format(activity5) + " กิโลแคลอรี่");
                    }else {
                          tvresult.setText("กรุณากรอกข้อมูลให้ครบ");
                    }

                //}else {
                    //tvresult.setText("กรุณากรอกข้อมูลให้ครบ");
                //}
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAge.setText("");
                edtW.setText("");
                edtH.setText("");
                //result.setEnabled(false);
                radioGroup.clearCheck();
                tvresult.setText(null);
            }
        });
        return view;
    }

    private void onWindowStart() {
        Cursor cursor = userDB.getReadData();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    edtAge.setText(cursor.getString(4));
                    edtW.setText(cursor.getString(5));
                    edtH.setText(cursor.getString(6));
                } while (cursor.moveToNext());
            }
        }
    }
}
