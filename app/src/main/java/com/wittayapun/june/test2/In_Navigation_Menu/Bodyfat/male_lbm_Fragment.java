package com.wittayapun.june.test2.In_Navigation_Menu.Bodyfat;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class male_lbm_Fragment extends Fragment {
    UserDatabaseHelper userDB;
    private EditText edtw, edtwaist;
    private Button result, reset;
    private TextView showresult;
    private ImageView imvfat;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public male_lbm_Fragment() {
        // Required empty public constructor
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            /*
            String weightInput = edtw.getText().toString().trim();
            String heightInput = edtwaist.getText().toString().trim();

            result.setEnabled(!weightInput.isEmpty() && !heightInput.isEmpty());
            */
        }

        @Override
        public void afterTextChanged(Editable editable) {
            editor.putString("pref_m_waist",edtwaist.getText().toString()).commit();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_male_lbm_, container, false);

        edtw = view.findViewById(R.id.edt1);
        edtwaist = view.findViewById(R.id.edt2);
        reset = view.findViewById(R.id.btnreset);
        result = view.findViewById(R.id.btnresult);
        showresult = view.findViewById(R.id.tvresult);
        imvfat = view.findViewById(R.id.imvfat);

        prefs = this.getActivity().getSharedPreferences("Prefs_male_lbm", Context.MODE_PRIVATE);
        editor = prefs.edit();
        //  getpreference
        edtwaist.setText(prefs.getString("pref_m_waist", ""));

        userDB = new UserDatabaseHelper(getActivity());

        onStartWindow();

        edtw.addTextChangedListener(textWatcher);
        edtwaist.addTextChangedListener(textWatcher);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(result.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//  CalFat
                double w = Integer.parseInt(edtw.getText().toString().trim());
                double waist = Integer.parseInt(edtwaist.getText().toString().trim());

                double weightlbs = w * 2.2;
                double f1 = (weightlbs * 1.082) + 94.42;
                double f2 = waist * 4.15;

                double leanbodymass = f1 - f2;
                double fatweight = weightlbs - leanbodymass;
                double fatpercentage = (fatweight * 100) / weightlbs;
                DecimalFormat decimalFormat = new DecimalFormat("##.#");

                if (fatpercentage >= 35.00) {
                    showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat35);
                } else if (fatpercentage >= 30.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat30);
                } else if (fatpercentage >= 25.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat25);
                } else if (fatpercentage >= 20.00) {showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat20);
                } else if (fatpercentage >= 15.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat15);
                } else if (fatpercentage >= 12.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat12);
                } else if (fatpercentage >= 8.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fat8);
                } else if (fatpercentage < 8.00 && fatpercentage >=0) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที่: " + decimalFormat.format(fatpercentage) +" % ของน้ำหนักตัวทั้งหมด"+ "\n" + "คำเตือน!: ห้ามไขมันต่ำกว่า 2%-5%\n" + "เพราะจะเป็นไขมันที่จำเป็นต่อร่างกาย");
                } else if (fatpercentage < 0) { showresult.setText("กรุณาใส่ข้อมูลที่เป็นความจริง");
                } else {
                    showresult.setText("กรุณากรอกข้อมูลให้ครบ");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtw.getText().clear();
                edtwaist.getText().clear();
                showresult.setText(null);
                imvfat.setImageDrawable(null);
            }
        });


        return view;
    }

    private void onStartWindow() {
        Cursor cursor = userDB.getReadData();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    edtw.setText(cursor.getString(5));
                } while (cursor.moveToNext());
            }
        }
    }
}
