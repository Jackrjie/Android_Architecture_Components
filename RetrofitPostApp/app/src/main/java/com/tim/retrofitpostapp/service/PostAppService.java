package com.tim.retrofitpostapp.service;

import com.tim.retrofitpostapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * create by Tim on 6/10/2019
 * email: jackrjie@gmail.com
 */
public interface PostAppService {
    @POST("posts")
    Call<User> getResult(@Body User user);
}
