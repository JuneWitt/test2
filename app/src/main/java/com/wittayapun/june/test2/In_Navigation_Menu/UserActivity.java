package com.wittayapun.june.test2.In_Navigation_Menu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wittayapun.june.test2.DatabaseHelper;
import com.wittayapun.june.test2.In_Navigation_Menu.ActivityWhenHaveUser.EditDetailActivity;
import com.wittayapun.june.test2.MainActivity;
import com.wittayapun.june.test2.R;

import cn.refactor.lib.colordialog.ColorDialog;
import cn.refactor.lib.colordialog.PromptDialog;

public class UserActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "UserProfile_db";

    UserDatabaseHelper dbhelper;
    //SQLiteDatabase myDatabase;

    EditText firstname,lastname,Age,weight,height;
    Spinner spinnerGender;
    Button insert,btnviewAll;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String firstnameInput = firstname.getText().toString().trim();
            String lastnameInput = lastname.getText().toString().trim();
            String AgeInput = Age.getText().toString().trim();
            String WInput = weight.getText().toString().trim();
            String HInput = height.getText().toString().trim();

            insert.setEnabled(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !AgeInput.isEmpty() && !WInput.isEmpty() && !HInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("สร้างโปรไฟล์");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

       //myDatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        dbhelper = new UserDatabaseHelper(this);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        spinnerGender = findViewById(R.id.spinnerGender);
        Age = findViewById(R.id.age);
        weight = findViewById(R.id.w);
        height = findViewById(R.id.h);
        insert = findViewById(R.id.createUser);
        btnviewAll = findViewById(R.id.btnviewAll);

        firstname.addTextChangedListener(textWatcher);
        lastname.addTextChangedListener(textWatcher);
        Age.addTextChangedListener(textWatcher);
        weight.addTextChangedListener(textWatcher);
        height.addTextChangedListener(textWatcher);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(insert.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                addUser();
            }
        });

        viewAll();
}

    private void addUser() {

        final String fname = firstname.getText().toString().trim();
        final String lname = lastname.getText().toString().trim();
        final String gender = spinnerGender.getSelectedItem().toString();
        final String age = Age.getText().toString().trim();
        final String w = weight.getText().toString().trim();
        final String h = height.getText().toString().trim();
        Boolean result = dbhelper.insertData(fname,lname,gender,age,w,h);


        //Log.d("keyValue","key= "+fname+ ","+ lname +","+ sex +","+ age +","+ w +","+ h)

            //String insertSQL = "INSERT INTO  User(Firstname,Lastname,Gender,Age,Weight,Height) VALUES(?,?,?,?,?,?);";
            //myDatabase.execSQL(insertSQL, new String[]{fname,lname,sex,age,w,h});

        if (result == true) {
                PromptDialog dialog = new PromptDialog(UserActivity.this);
                dialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                dialog.setAnimationEnable(true);
                dialog.setTitleText(getString(R.string.สำเร็จ));
                dialog.setContentText(getString(R.string.คุณได้สร้างโปรไฟล์แล้ว));
                dialog.setPositiveListener(getString(R.string.OK), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                        Intent MainIntent = new Intent(UserActivity.this, MainActivity.class);
                        startActivity(MainIntent);
                        finish();

                    }
                }).show();
            } else {
               Toast.makeText(this, "Data Insert Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = dbhelper.getReadData();
                        //Cursor res = dbhelper.rawQuery("select * from User ",null);
                        if (res.getCount() == 0) {
                                // show message
                                showMessage("Error", "Nothing found");
                                return;
                        }
                        StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id :" + res.getString(0) + "\n");
                                buffer.append("Name :" + res.getString(1) + "\n");
                                buffer.append("Surname :" + res.getString(2) + "\n");
                                buffer.append("Gender :" + res.getString(3) + "\n");
                                buffer.append("Age :" + res.getString(4) + "\n");
                                buffer.append("Weight :" + res.getString(5) + "\n");
                                buffer.append("Height :" + res.getString(6) + "\n\n");
                            }
                            // Show all data
                            showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
