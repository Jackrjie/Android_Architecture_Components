package com.tim.getcountry.service;

import com.tim.getcountry.model.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * create by Tim on 6/10/2019
 * email: jackrjie@gmail.com
 */
public interface GetCountryDataService {
    @GET("country/get/iso2code/{alpha2_code}")
    Call<Info> getResultByAlpha2Code(@Path("alpha2_code") String alpha2Code);
}
