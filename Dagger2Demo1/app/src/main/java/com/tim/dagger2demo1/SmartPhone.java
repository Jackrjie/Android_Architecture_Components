package com.tim.dagger2demo1;

import android.util.Log;

import javax.inject.Inject;

/**
 * create by Tim on 6/14/2019
 * email: jackrjie@gmail.com
 */
public class SmartPhone {

    private Battery mBattery;
    private Screen mScreen;
    private SimCard mSimCard;

    @Inject
    public SmartPhone(Battery battery, Screen screen, SimCard simCard) {
        mBattery = battery;
        mScreen = screen;
        mSimCard = simCard;
    }

    public void makeCall(){
        Log.i("testing123", "makeCall...");
    }
}
