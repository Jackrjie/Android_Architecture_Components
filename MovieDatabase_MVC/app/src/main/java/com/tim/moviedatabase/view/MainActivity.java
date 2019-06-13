package com.tim.moviedatabase.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.tim.moviedatabase.R;
import com.tim.moviedatabase.adapter.MovieAdapter;
import com.tim.moviedatabase.model.Movie;
import com.tim.moviedatabase.model.MovieDBResponse;
import com.tim.moviedatabase.service.MovieDataService;
import com.tim.moviedatabase.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Testing123";
    private ArrayList<Movie> mMovies;
    private RecyclerView mRvApp;
    private MovieAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");
        mRefreshLayout = findViewById(R.id.rf_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout != null){
                            if (mRefreshLayout.isRefreshing()){
                                mRefreshLayout.setRefreshing(false);
                                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },500);
            }
        });
        getPopularMovies();
    }

    private void getPopularMovies() {

        Log.i(TAG, "getPopularMovies: ");
        MovieDataService mService = RetrofitInstance.getService();
        Call<MovieDBResponse> call = mService.getPopularMovies(this.getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {

            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                Log.i(TAG, "onResponse: ");
                MovieDBResponse mResponse = response.body();
                if (mResponse!=null && response.body()!=null){
                    Log.i(TAG, "onResponse: " + mResponse.toString());
                    mMovies = (ArrayList<Movie>) mResponse.getMovies();
                    showOnRecyclerView();
                } else {
                    Log.i(TAG, "nothing ");
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void showOnRecyclerView() {
        mRvApp = findViewById(R.id.rv_app);
        mAdapter = new MovieAdapter(this,mMovies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mRvApp.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            mRvApp.setLayoutManager(new GridLayoutManager(this,4));
        }
        mRvApp.setItemAnimator(new DefaultItemAnimator());
        mRvApp.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
