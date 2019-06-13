package com.tim.retrofitpostapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tim.retrofitpostapp.R;
import com.tim.retrofitpostapp.model.User;
import com.tim.retrofitpostapp.service.PostAppService;
import com.tim.retrofitpostapp.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail,etPassword;
    private Button btnSubmit;
    private TextView tvResult;
    private String email,password,result;
    private static final String TAG = "Testing123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSubmit = findViewById(R.id.btn_submit);
        tvResult = findViewById(R.id.tv_result);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
    }

    private void postData() {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            show("Email and password cannot be blank");
        } else {
            User user = new User();
            user.setUserEmail(email);
            user.setPassword(password);
            Log.i(TAG, "**************** before id: " + user.getId());

            PostAppService service = RetrofitInstance.getService();
            Call<User> call = service.getResult(user);

            etEmail.setText("");
            etPassword.setText("");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User returnedUser = response.body();
                    if (returnedUser!=null && response.body()!=null){
                        String returnedId = "Id is: " + returnedUser.getId();
                        tvResult.setText(returnedId);
                        Log.i(TAG, "**************** after id: " + returnedUser.getId());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        }
    }

    private void show(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
