package com.tim.moviedatabase.service;

import com.tim.moviedatabase.model.Movie;
import com.tim.moviedatabase.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * create by Tim on 6/11/2019
 * email: jackrjie@gmail.com
 */
public interface MovieDataService {
    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/top_rated")
    Call<MovieDBResponse> getTopRatedMovies(@Query("api_key")String apiKey);

    @GET("movie/now_playing")
    Call<MovieDBResponse> getNowPlayingMovies(@Query("api_key")String apiKey);
}
