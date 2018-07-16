package com.wittayapun.june.test2.In_Navigation_Menu.WeightForHeight;


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
import android.widget.TextView;

import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class femaleWeightFragment extends Fragment {
    private UserDatabaseHelper userDB;
    private EditText edtH;
    private Button reset, result;

    public femaleWeightFragment() {
        // Required empty public constructor
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String heightInput = edtH.getText().toString().trim();
            result.setEnabled(!heightInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_female_weight, container, false);

        edtH = view.findViewById(R.id.edt2);
        reset = view.findViewById(R.id.btnreset);
        result = view.findViewById(R.id.btnresult);
        final TextView showresult = view.findViewById(R.id.tvresult);

        userDB = new UserDatabaseHelper(getActivity());

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

                double h = Integer.parseInt(edtH.getText().toString());
                double bws = (h-100)*0.8;
                DecimalFormat decimalFormat = new DecimalFormat("##.#");

                if (bws >= 300 || bws <= 0) { showresult.setText("กรุณากรอกข้อมูลที่เป็นความจริง");
                }else { showresult.setText("น้ำหนักที่เหมาะสมของคุณ คือ " + decimalFormat.format(bws) + " กิโลกรัม"); }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtH.setText("");
                result.setEnabled(false);
                showresult.setText(null);
            }
        });

        onWindowStart();
        return view;
    }

    private void onWindowStart() {
        Cursor cursor = userDB.getReadData();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    edtH.setText(cursor.getString(6));
                } while (cursor.moveToNext());
            }
        }
    }
}
