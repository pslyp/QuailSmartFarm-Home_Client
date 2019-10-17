package com.pslyp.quailsmartfarm_home_client.services.api;

import com.pslyp.quailsmartfarm_home_client.models.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    @FormUrlEncoded
    @POST("signup.php")
    Call<Response> signUp(

    );

    @FormUrlEncoded
    @POST("signin.php")
    Call<Response> signIn(
            @Field("email") String email,
            @Field("password") String password
    );

}