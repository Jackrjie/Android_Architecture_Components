package com.tim.moviedatabase.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.tim.moviedatabase.service.MovieDataService;

/**
 * create by Tim on 6/13/2019
 * email: jackrjie@gmail.com
 */
public class MovieDataSourceFactory extends DataSource.Factory {

    /**
     * To construct a MovieDataSource instance we need an instance of Application class
     * and an instance of MovieDataService interface.
     */
    private MovieDataSource mSource;
    private MovieDataService mService;
    private Application mApplication;

    /**
     * We are going to send MovieDataSource instance to the view model as mutable live data.
     */
    private MutableLiveData<MovieDataSource> mMovieDataSourceMutableLiveData;

    public MovieDataSourceFactory(MovieDataService service, Application application) {
        mService = service;
        mApplication = application;
        mMovieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        mSource = new MovieDataSource(mService,mApplication);
        mMovieDataSourceMutableLiveData.postValue(mSource);
        return mSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return mMovieDataSourceMutableLiveData;
    }
}
