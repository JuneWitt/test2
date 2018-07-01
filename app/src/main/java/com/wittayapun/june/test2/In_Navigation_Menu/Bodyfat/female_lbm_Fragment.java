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
public class female_lbm_Fragment extends Fragment {
    UserDatabaseHelper userDB;
    private EditText edtw;
    //private EditText edth;
    private EditText edtwrist;
    private EditText edtwaist;
    private EditText edthip;
    private EditText edtarm;
    private Button result;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            /*
            String weightInput = edtw.getText().toString().trim();
            String wristInput = edtwrist.getText().toString().trim();
            String waistInput = edtwaist.getText().toString().trim();
            String hipInput = edthip.getText().toString().trim();
            String armInput = edtarm.getText().toString().trim();

            result.setEnabled(!weightInput.isEmpty() && !wristInput.isEmpty() && !waistInput.isEmpty()
            && !hipInput.isEmpty() && !armInput.isEmpty());
            */
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // put getPreference
           editor.putString("pref_wrist",edtwrist.getText().toString());
           editor.putString("pref_waist",edtwaist.getText().toString());
           editor.putString("pref_hip",edthip.getText().toString());
           editor.putString("pref_arm",edtarm.getText().toString());
           editor.commit();
        }
    };

    public female_lbm_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_female_lbm_, container, false);

        /*
        String wrist = prefs.getString("wrist", "");
        String waist = prefs.getString("waist", "");
        String hip = prefs.getString("hip", "");
        String arm = prefs.getString("arm", "");
        */


        edtw = view.findViewById(R.id.edt1);
        //edth = view.findViewById(R.id.edt2);
        edtwrist = view.findViewById(R.id.edt3);
        edtwaist = view.findViewById(R.id.edt4);
        edthip = view.findViewById(R.id.edt5);
        edtarm = view.findViewById(R.id.edt6);
        Button reset = view.findViewById(R.id.btnreset);
        result = view.findViewById(R.id.btnresult);
        final TextView showresult = view.findViewById(R.id.tvresult);
        final ImageView imvfat = view.findViewById(R.id.imvfat);

        prefs = getActivity().getSharedPreferences("pref_female_lbm",Context.MODE_PRIVATE);
        editor = prefs.edit();
        //  getpreference
        edtwrist.setText(prefs.getString("pref_wrist", ""));
        edtwaist.setText(prefs.getString("pref_waist", ""));
        edthip.setText(prefs.getString("pref_hip", ""));
        edtarm.setText(prefs.getString("pref_arm", ""));

        userDB = new UserDatabaseHelper(getActivity());

        onWindowStart();

        edtw.addTextChangedListener(textWatcher);
        //edth.addTextChangedListener(textWatcher);
        edtwrist.addTextChangedListener(textWatcher);
        edtwaist.addTextChangedListener(textWatcher);
        edthip.addTextChangedListener(textWatcher);
        edtarm.addTextChangedListener(textWatcher);

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
                //double h = Integer.parseInt(edth.getText().toString().trim());
                double wrist = Integer.parseInt(edtwrist.getText().toString().trim());
                double waist = Integer.parseInt(edtwaist.getText().toString().trim());
                double hip = Integer.parseInt(edthip.getText().toString().trim());
                double arm = Integer.parseInt(edtarm.getText().toString().trim());

                double weightlbs =  w * 2.2;
                double f1 = (weightlbs * 0.732)+8.987;
                double f2 = wrist/3.140;
                double f3 = waist*0.157;
                double f4 = hip*0.249;
                double f5 = arm*0.434;

                double leanbodymass = (((f1 + f2) - f3) - f4) + f5;
                double fatweight = weightlbs - leanbodymass;
                double fatpercentage = (fatweight * 100)/weightlbs;

                DecimalFormat decimalFormat = new DecimalFormat("##.#");

                if (fatpercentage >= 45.00) {
                    showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: "+decimalFormat.format(fatpercentage)+" %");imvfat.setImageResource(R.drawable.fefat45);
                } else if (fatpercentage >= 40.00) {showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: "+decimalFormat.format(fatpercentage)+" % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat40);
                } else if (fatpercentage >= 35.00) {showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: "+decimalFormat.format(fatpercentage)+" % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat35);
                } else if (fatpercentage >= 30.00) {showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: "+decimalFormat.format(fatpercentage)+" % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat30);
                } else if (fatpercentage >= 25.00) {showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: " + decimalFormat.format(fatpercentage)+" % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat25);
                } else if (fatpercentage >= 20.00) {showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: "+ decimalFormat.format(fatpercentage)+" % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat20);
                } else if (fatpercentage >= 15.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat15);
                } else if (fatpercentage < 15.00) { showresult.setText("คุณมีเปอร์เซ็นไขมันอยู่ที: " + decimalFormat.format(fatpercentage) + " % ของน้ำหนักตัวทั้งหมด");imvfat.setImageResource(R.drawable.fefat15);
                }else if (fatpercentage <12 && fatpercentage >=0){showresult.setText("เปอร์เซ็นไขมันของคุณ: "+decimalFormat.format(fatpercentage)+" %"+"\n"+"คำเตือน!: ห้ามไขมันต่ำกว่า 8%-12%\n"+"เพราะจะเป็นไขมันที่จำเป็นต่อร่างกาย");
                }else if (fatpercentage <0){showresult.setText("กรุณาใส่ข้อมูลที่เป็นความจริง");
                } else {
                    showresult.setText("กรุณากรอกข้อมูลให้ครบ");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtw.getText().clear();
                //edth.getText().clear();
                edtwrist.getText().clear();
                edtwaist.getText().clear();
                edthip.getText().clear();
                edtarm.getText().clear();
                showresult.setText(null);
                imvfat.setImageDrawable(null);
            }
        });
        return view;
    }

    private void onWindowStart() {
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
