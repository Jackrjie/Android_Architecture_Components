package com.tim.countries.service;

import com.tim.countries.model.Info;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * create by Tim on 6/10/2019
 * email: jackrjie@gmail.com
 */
public interface GetCountryDataService {
    @GET("country/get/all")
    Call<Info> getResults();
}
