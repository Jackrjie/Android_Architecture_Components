package com.tim.getcountry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tim.getcountry.model.Info;
import com.tim.getcountry.model.Result;
import com.tim.getcountry.service.GetCountryDataService;
import com.tim.getcountry.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTvResult;
    private EditText mEtInput;
    private Button mBtnSubmit,mBtnClear;
    private String code,countryCode;
    private Result mResult;
    private static final String TAG = "Testing123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = findViewById(R.id.tv_reuslt);
        mEtInput = findViewById(R.id.et_input);
        mBtnSubmit = findViewById(R.id.btn_submit);
        mBtnClear = findViewById(R.id.btn_clear);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtInput.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Please insert country code",Toast.LENGTH_SHORT).show();
                } else {
                    code = mEtInput.getText().toString();
                    String countryCode = code.toUpperCase();
                    GetCountryDataService service = RetrofitInstance.getService();
                    Call<Info> call = service.getResultByAlpha2Code(countryCode);

                    call.enqueue(new Callback<Info>() {
                        @Override
                        public void onResponse(Call<Info> call, Response<Info> response) {
                            Info info = response.body();
                            if (info != null && info.getRestResponse() != null){
                                mResult = info.getRestResponse().getResult();
                                if (mResult != null){
                                    mTvResult.setText(mResult.getName());
                                } else {
                                    Toast.makeText(getApplicationContext(),"Wrong country code",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Info> call, Throwable t) {
                            Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                        }
                    });
                }
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvResult.setText("");
                mEtInput.setText("");
            }
        });
    }
}
