package com.example.applicationteste;

import com.google.gson.JsonObject;
import com.example.applicationteste.domain.LoginCredenciado;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface Login {
//    @Headers({
//            "Accept: application/json",
//    })
//    @POST("api/v1/users/auth/sign_in")
//    Call<JsonObject> Login(@Body LoginCredenciado loginApi);

    @Headers("Content-Type: application/json")
    @POST("api/v1/users/auth/sign_in")
    Call<JsonObject> Login(@Body LoginCredenciado loginApi);
}
