package com.panshul.evo.Services;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.evo.Object.Event.EventSpecificObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    public static void saveLiked(List<String> saveLike, Context context){
        Gson gson = new Gson();
        String json = gson.toJson(saveLike);
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("likes",json);
        editor.apply();
    }
    public static List<String> getLikes(Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String json = pref.getString("likes","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = new ArrayList<>();
        if (list==null){
            list = new ArrayList<>();
        }
        return list;
    }

    public static void savedEvent(List<String> saveLike, Context context){
        Gson gson = new Gson();
        String json = gson.toJson(saveLike);
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("event",json);
        editor.apply();
    }
    public static List<String> getEvent(Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String json = pref.getString("event","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = new ArrayList<>();
        if (list==null){
            list = new ArrayList<>();
        }
        return list;
    }
}
