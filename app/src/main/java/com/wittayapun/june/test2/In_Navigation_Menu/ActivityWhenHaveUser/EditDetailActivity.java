package com.wittayapun.june.test2.In_Navigation_Menu.ActivityWhenHaveUser;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.In_Navigation_Menu.UserDatabaseHelper;
import com.wittayapun.june.test2.R;

import java.util.ArrayList;

import cn.refactor.lib.colordialog.PromptDialog;


public class EditDetailActivity extends AppCompatActivity {

    UserDatabaseHelper userDB;
    private Button edit;
    private EditText id,firstname,lastname,age,w,h;
    private Spinner Genderspinner;

    ArrayList<String> spinGender = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        userDB = new UserDatabaseHelper(this);
        id = findViewById(R.id.ID);
        edit = findViewById(R.id.updatedata);
        firstname = findViewById(R.id.edfirstname);
        lastname = findViewById(R.id.edlastname);

        spinGender.add("ชาย");
        spinGender.add("หญิง");
        Genderspinner = findViewById(R.id.spinGender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinGender);
        Genderspinner.setAdapter(adapter);

        age = findViewById(R.id.ageEd);
        w = findViewById(R.id.wEd);
        h = findViewById(R.id.hEd);

        firstname.addTextChangedListener(textWatcher);
        lastname.addTextChangedListener(textWatcher);
        age.addTextChangedListener(textWatcher);
        w.addTextChangedListener(textWatcher);
        h.addTextChangedListener(textWatcher);

        onShowData();
        onUpdatePress();
    }

    private void onShowData() {
        Cursor cursor = userDB.getReadData();
        if (cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    firstname.setText(cursor.getString(1));
                    lastname.setText(cursor.getString(2));
                    Genderspinner.setSelection(spinGender.indexOf(cursor.getString(3)));
                    age.setText(cursor.getString(4));
                    w.setText(cursor.getString(5));
                    h.setText(cursor.getString(6));
                } while (cursor.moveToNext());
            }
        }
    }

    private void onUpdatePress() {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                } catch (Exception e) {e.printStackTrace();
                }
                String ID = id.getText().toString().trim();
                String fname = firstname.getText().toString().trim();
                String lname = lastname.getText().toString().trim();
                String sex = Genderspinner.getSelectedItem().toString();
                String Age = age.getText().toString().trim();
                String weight = w.getText().toString().trim();
                String height = h.getText().toString().trim();

                Boolean update = userDB.updateData(ID, fname, lname, sex, Age, weight, height);

                if (update == true) {
                    PromptDialog dialog = new PromptDialog(EditDetailActivity.this);
                    dialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                    dialog.setAnimationEnable(true);
                    dialog.setTitleText(getString(R.string.สำเร็จ));
                    dialog.setContentText(getString(R.string.คุณได้อัพเดทโปรไฟล์แล้ว));
                    dialog.setPositiveListener(getString(R.string.OK), new PromptDialog.OnPositiveListener() {
                        @Override
                        public void onClick(PromptDialog dialog) {
                            dialog.dismiss();
                        }
                    }).show();
                } else {
                    Toast.makeText(EditDetailActivity.this, "Data Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String firstnameInput = firstname.getText().toString().trim();
            String lastnameInput = lastname.getText().toString().trim();
            String AgeInput = age.getText().toString().trim();
            String WInput = w.getText().toString().trim();
            String HInput = h.getText().toString().trim();

            edit.setEnabled(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !AgeInput.isEmpty() && !WInput.isEmpty() && !HInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        this.finish();
        return true;
    }
}