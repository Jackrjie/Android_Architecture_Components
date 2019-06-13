package com.tim.moviedatabase.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * create by Tim on 6/11/2019
 * email: jackrjie@gmail.com
 */
public class RetrofitInstance {
    private static Retrofit sRetrofit = null;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieDataService getService(){
        if (sRetrofit == null){
            sRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit.create(MovieDataService.class);
    }
}
