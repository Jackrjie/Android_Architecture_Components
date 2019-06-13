package com.tim.countries.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * We need to communicate with REST many times. If we create a new Retrofit instance
 * every time we need to communicate with API, it will waste a lot of resources.
 * create by Tim on 6/10/2019
 * email: jackrjie@gmail.com
 */
public class RetrofitInstance {
    private static Retrofit sRetrofit = null;
    private static String BASE_URL = "http://services.groupkt.com/";

    public static GetCountryDataService getService(){

        if (sRetrofit == null){
            sRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // By passing interface name as an argument to the Retrofit's create method,
        // we can get a retrofit object wrapped with our interface.
        return sRetrofit.create(GetCountryDataService.class);
    }
}
