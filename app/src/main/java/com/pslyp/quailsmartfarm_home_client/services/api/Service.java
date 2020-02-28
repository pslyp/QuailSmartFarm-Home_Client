package com.pslyp.quailsmartfarm_home_client.services.api;

import com.pslyp.quailsmartfarm_home_client.models.BaseResponse;
import com.pslyp.quailsmartfarm_home_client.models.SignInResponse;
import com.pslyp.quailsmartfarm_home_client.models.User;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    @POST("user")
    Call<BaseResponse> signUp(@Body User user);

    @FormUrlEncoded
    @POST("user/login")
    Call<SignInResponse> signIn(
            @Field("email") String email,
            @Field("password") String password
    );

}
