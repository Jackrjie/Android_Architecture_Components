package com.tim.dagger2demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SmartPhone mSmartPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.create();

        mSmartPhone = smartPhoneComponent.getSmartPhone();
        mSmartPhone.makeCall();
    }
}
