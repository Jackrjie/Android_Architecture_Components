package com.tim.moviedatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tim.moviedatabase.model.Movie;
import com.tim.moviedatabase.model.MovieRepository;

import java.util.List;

/**
 * create by Tim on 6/12/2019
 * email: jackrjie@gmail.com
 */
public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository mRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies(){
        return mRepository.getLiveData();
    }

}
