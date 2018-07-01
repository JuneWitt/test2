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
import android.widget.Toast;

import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class femaleCalorieFragment extends Fragment {
    UserDatabaseHelper userDB;
    private EditText edtAge,edtW,edtH;
    private Button reset,result;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4,rb5;
    private TextView tvresult;


    public femaleCalorieFragment() {
        // Required empty public constructor
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            /*
            final String ageInput = edtAge.getText().toString().trim();
            final String weightInput = edtW.getText().toString().trim();
            final String heightInput = edtH.getText().toString().trim();

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
        View view = inflater.inflate(R.layout.fragment_female_calorie, container, false);

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

                double age =Integer.parseInt(edtAge.getText().toString().trim());
                double w =Integer.parseInt(edtW.getText().toString().trim());
                double h =Integer.parseInt(edtH.getText().toString().trim());

                //665 + (9.6 x น้ำหนักตัว (กิโลกรัม))+(1.8 x ส่วนสูง (เซนติเมตร))-(4.7 x อายุ)
                double BMR = 665+(9.6*w)+(1.8*h)-(4.7*age);

                double activity1 = BMR*1.2;
                double activity2 = BMR*1.375;
                double activity3 = BMR*1.55;
                double activity4 = BMR*1.725;
                double activity5 = BMR*1.9;
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

                if (rb1.isChecked() && result.isEnabled()) {tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ "+decimalFormat.format(activity1)+" กิโลแคลอรี่");
                } else if (rb2.isChecked() && result.isEnabled()) {tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ "+decimalFormat.format(activity2)+" กิโลแคลอรี่");
                } else if (rb3.isChecked() && result.isEnabled()) {tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ "+decimalFormat.format(activity3)+" กิโลแคลอรี่");
                } else if (rb4.isChecked() && result.isEnabled()) {tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ "+decimalFormat.format(activity4)+" กิโลแคลอรี่");
                } else if (rb5.isChecked() && result.isEnabled()) { tvresult.setText("ค่าพลังงานที่จำเป็นต่อวันของคุณ คือ " + decimalFormat.format(activity5) + " กิโลแคลอรี่");
                } else {tvresult.setText("กรุณากรอกข้อมูลให้ครบ");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAge.getText().clear();
                edtW.getText().clear();
                edtH.getText().clear();
                radioGroup.clearCheck();
                result.setEnabled(false);
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
