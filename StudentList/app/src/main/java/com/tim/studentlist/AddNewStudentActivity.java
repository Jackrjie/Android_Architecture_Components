package com.tim.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewStudentActivity extends AppCompatActivity {

    private Button mBtnSubmit;
    private EditText mEtName,mEtEmail,mEtCountry;
    private String action;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        mEtName = findViewById(R.id.et_name);
        mEtEmail = findViewById(R.id.et_email);
        mEtCountry = findViewById(R.id.et_country);
        mBtnSubmit = findViewById(R.id.btn_submit);
        action = getIntent().getStringExtra("ACTION");

        if (action.equalsIgnoreCase("update")){
            mBtnSubmit.setText("UPDATE");
            Intent intent = getIntent();
            mEtName.setText(intent.getStringExtra("_NAME"));
            mEtEmail.setText(intent.getStringExtra("_EMAIL"));
            mEtCountry.setText(intent.getStringExtra("_COUNTRY"));
            position = intent.getIntExtra("POSITION",0);
        }

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action.equalsIgnoreCase("create")) {
                    if (TextUtils.isEmpty(mEtName.getText())) {
                        show("Name field cannot be empty");
                    } else {
                        String name = mEtName.getText().toString();
                        String email = mEtEmail.getText().toString();
                        String country = mEtCountry.getText().toString();

                        Intent intent = new Intent();
                        intent.putExtra("NAME", name);
                        intent.putExtra("EMAIL", email);
                        intent.putExtra("COUNTRY", country);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    if (TextUtils.isEmpty(mEtName.getText())) {
                        show("Name field cannot be empty lah");
                    } else {
                        String name = mEtName.getText().toString();
                        String email = mEtEmail.getText().toString();
                        String country = mEtCountry.getText().toString();

                        Intent intent = new Intent();
                        intent.putExtra("NAME", name);
                        intent.putExtra("EMAIL", email);
                        intent.putExtra("COUNTRY", country);
                        intent.putExtra("POSITION",position);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });
    }

    private void show(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
