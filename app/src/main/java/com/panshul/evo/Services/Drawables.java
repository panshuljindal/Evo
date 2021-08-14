package com.panshul.evo.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.Object.Like.LikeBody;
import com.panshul.evo.Object.Like.LikeResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        Log.i("save_json",json);
        editor.apply();
    }
    public static List<String> getLikes(Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String json = pref.getString("likes","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list=new ArrayList<>();
        list= gson.fromJson(json,type);
        if (list==null){
            list = new ArrayList<>();
        }
        Log.i("get_json",list.toString());
        return list;
    }

    public static void savedEvent(List<String> saved, Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saved);
        editor.putString("event",json);
        Log.i("save_json",json);
        editor.apply();
    }
    public static List<String> getSavedEvent(Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo",Context.MODE_PRIVATE);
        String json = pref.getString("event","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list=new ArrayList<>();
        list= gson.fromJson(json,type);
        if (list==null){
            list = new ArrayList<>();
        }
       // Log.i("get_json",list.toString());
        return list;
    }
    public static void likeEvent(String eventId){
//        Call<LikeResponse> call = api.likeEvent(new LikeBody(eventId));
//        call.enqueue(new Callback<LikeResponse>() {
//            @Override
//            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
//                LikeResponse object = response.body();
//                if (object.getMessage().equals("Likes updated!")){
//
//                }else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LikeResponse> call, Throwable t) {
//
//            }
//        });
    }
}
