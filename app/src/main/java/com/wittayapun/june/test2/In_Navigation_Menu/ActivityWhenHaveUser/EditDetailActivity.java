package com.wittayapun.june.test2.In_Navigation_Menu.ActivityWhenHaveUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

import cn.refactor.lib.colordialog.PromptDialog;


public class EditDetailActivity extends AppCompatActivity {

    UserDatabaseHelper userDB;
    private Button edit;
    private EditText id,firstname,lastname,age,w,h;
    private Spinner Genderspinner;

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
        Genderspinner = findViewById(R.id.spinGender);
        age = findViewById(R.id.ageEd);
        w = findViewById(R.id.wEd);
        h = findViewById(R.id.hEd);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}