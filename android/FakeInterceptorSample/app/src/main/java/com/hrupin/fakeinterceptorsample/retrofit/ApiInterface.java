package com.hrupin.fakeinterceptorsample.retrofit;


import com.hrupin.fakeinterceptorsample.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * ServerInterface
 */
public interface ApiInterface {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("login")
    Call<User> login(@Body HashMap<String, String> body);

}
