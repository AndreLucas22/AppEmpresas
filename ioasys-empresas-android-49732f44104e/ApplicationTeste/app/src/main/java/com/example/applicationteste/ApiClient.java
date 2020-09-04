package com.example.applicationteste;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String WEBAPI_BASE_URL = "https://empresas.ioasys.com.br/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setDateFormat("dd-MM-yyyy HH:mm:ss").create();
    private static Retrofit.Builder builderLogin =
            new Retrofit.Builder()
                    .baseUrl(WEBAPI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createServiceLogin(Class<S> serviceClass) {
        httpClient.readTimeout(40, TimeUnit.SECONDS);
        httpClient.writeTimeout(40, TimeUnit.SECONDS);
        httpClient.connectTimeout(40, TimeUnit.SECONDS);
        Retrofit retrofit = builderLogin.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
