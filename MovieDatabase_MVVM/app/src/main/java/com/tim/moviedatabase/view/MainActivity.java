package com.tim.moviedatabase.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.tim.moviedatabase.R;
import com.tim.moviedatabase.adapter.MovieAdapter;
import com.tim.moviedatabase.databinding.ActivityMainBinding;
import com.tim.moviedatabase.viewmodel.MainActivityViewModel;
import com.tim.moviedatabase.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Testing123";
    private ArrayList<Movie> mMovies;
    private RecyclerView mRvApp;
    private MovieAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private MainActivityViewModel mViewModel;
    private ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TMDB Popular Movies Today");

        mMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mRefreshLayout = mMainBinding.rfRefresh;
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
        mViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                mMovies = (ArrayList<Movie>) moviesFromLiveData;
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {
        mRvApp = mMainBinding.rvApp;
        mAdapter = new MovieAdapter(this,mMovies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mRvApp.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            mRvApp.setLayoutManager(new GridLayoutManager(this,3));
        }
        mRvApp.setItemAnimator(new DefaultItemAnimator());
        mRvApp.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
