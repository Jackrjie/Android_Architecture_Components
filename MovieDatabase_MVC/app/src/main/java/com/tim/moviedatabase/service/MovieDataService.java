package com.tim.moviedatabase.service;

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
}
