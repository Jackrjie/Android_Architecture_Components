package com.tim.moviedatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.tim.moviedatabase.model.Movie;
import com.tim.moviedatabase.model.MovieDataSource;
import com.tim.moviedatabase.model.MovieDataSourceFactory;
import com.tim.moviedatabase.model.MovieRepository;
import com.tim.moviedatabase.service.MovieDataService;
import com.tim.moviedatabase.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * create by Tim on 6/12/2019
 * email: jackrjie@gmail.com
 */
public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository mRepository;

    public LiveData<MovieDataSource> mMovieDataSourceLiveData;
    private Executor mExecutor;
    private LiveData<PagedList<Movie>> mMoviePagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MovieRepository(application);
        MovieDataService movieDataService = RetrofitInstance.getService();
        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieDataService,application);
        mMovieDataSourceLiveData = factory.getMovieDataSourceMutableLiveData();

        /**
         * PagedList is a special wrapper list we create when we are using paging library
         * to hold our data items. In this scenario to hold movies data, PagedList act
         * between the data source and the PagedListAdapter.
         *
         * For example, let's say we have some data that we add to the DataSource in the
         * background thread, then the DataSource invalidates the PagedList and updates its value.
         * PagedList will notify PagedListAdapter about the new updates.
         */

        // Before create the PagedList, we should define the configurations for the PagedList
        PagedList.Config config = (new PagedList.Config.Builder())
                                    .setEnablePlaceholders(true)
                                    .setInitialLoadSizeHint(10)
                                    .setPageSize(20)
                                    .setPrefetchDistance(4)
                                    .build();
        mExecutor = Executors.newFixedThreadPool(5);

        mMoviePagedList = (new LivePagedListBuilder<Long,Movie>(factory,config))
                .setFetchExecutor(mExecutor)
                .build();
    }

    public LiveData<PagedList<Movie>> getMoviePagedList() {
        return mMoviePagedList;
    }

    public LiveData<List<Movie>> getAllMovies(){
        return mRepository.getLiveData();
    }

}
