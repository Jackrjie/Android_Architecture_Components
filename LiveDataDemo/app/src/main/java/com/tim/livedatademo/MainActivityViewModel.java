package com.tim.livedatademo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * create by Tim on 6/2/2019
 * email: jackrjie@gmail.com
 */
public class MainActivityViewModel extends ViewModel {
    private int count = 0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

//    public int initCount(){
//        return count;
//    }

//    public int getCurrentCount(){
//        return ++count;
//    }

    public MutableLiveData<Integer> getInitCount(){
        countLiveData.setValue(count);
        return  countLiveData;
    }

    public void getCurrentCount(){
        count += 1;
        countLiveData.setValue(count);
    }
}
