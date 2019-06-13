package com.tim.moviedatabase.model;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.tim.moviedatabase.R;
import com.tim.moviedatabase.service.MovieDataService;
import com.tim.moviedatabase.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * create by Tim on 6/13/2019
 * email: jackrjie@gmail.com
 */
public class MovieDataSource extends PageKeyedDataSource<Long,Movie> {

    private MovieDataService mService;
    private Application mApplication;
    private static final String TAG = "testing123";

    public MovieDataSource(MovieDataService service, Application application) {
        mService = service;
        mApplication = application;
    }

    // fetch first page
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {

        mService = RetrofitInstance.getService();
        String apiKey = mApplication.getApplicationContext().getString(R.string.api_key);
        Call<MovieDBResponse> call = mService.getPopularMoviesWithPaging(apiKey,1);

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse mResponse = response.body();
                ArrayList<Movie> movies; // store data responded

                if (mResponse!=null && mResponse.getMovies()!=null){
                    movies = (ArrayList<Movie>)mResponse.getMovies();
                    callback.onResult(movies,null,(long)2);
                } else {
                    show("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                show("Something went wrong...");
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    // fetch other page
    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        mService = RetrofitInstance.getService();
        String apiKey = mApplication.getApplicationContext().getString(R.string.api_key);
        Call<MovieDBResponse> call = mService.getPopularMoviesWithPaging(apiKey,params.key);

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse mResponse = response.body();
                ArrayList<Movie> movies; // store data responded

                if (mResponse!=null && mResponse.getMovies()!=null){
                    movies = (ArrayList<Movie>)mResponse.getMovies();
                    callback.onResult(movies,params.key+1);
                } else {
                    show("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                show("Something went wrong...");
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void show(String msg){
        Toast.makeText(mApplication,msg,Toast.LENGTH_SHORT).show();
    }
}
