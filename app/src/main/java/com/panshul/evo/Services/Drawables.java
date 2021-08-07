package com.panshul.evo.Services;

import com.panshul.evo.Object.Event.EventSpecificObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Drawables {
    public static String base_url = "https://vit-events-app.herokuapp.com";
    public static OkHttpClient getClientInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(base_url)
            .client(getClientInstance())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static Api api = retrofit.create(Api.class);

}
