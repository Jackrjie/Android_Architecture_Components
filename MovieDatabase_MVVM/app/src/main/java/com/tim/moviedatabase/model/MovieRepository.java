package com.tim.moviedatabase.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.tim.moviedatabase.R;
import com.tim.moviedatabase.service.MovieDataService;
import com.tim.moviedatabase.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * create by Tim on 6/12/2019
 * email: jackrjie@gmail.com
 */
public class MovieRepository {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    // To create Live Data with Movie object, we should use MutableLiveData<List<T>>
    private MutableLiveData<List<Movie>> mLiveData = new MutableLiveData<>();
    private Application mApplication;

    public MovieRepository(Application application) {
        mApplication = application;
    }

    public MutableLiveData<List<Movie>> getLiveData(){
        MovieDataService mService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = mService.getNowPlayingMovies(mApplication.getApplicationContext().
                        getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse mResponse = response.body();

                if (mResponse!=null && response.body()!=null){
                    mMovies = (ArrayList<Movie>) mResponse.getMovies();
                    mLiveData.setValue(mMovies);
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });

        return mLiveData;
    }
}
