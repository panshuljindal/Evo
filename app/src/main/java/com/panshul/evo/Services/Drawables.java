package com.panshul.evo.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.Object.Like.LikeBody;
import com.panshul.evo.Object.Like.LikeResponse;
import com.panshul.evo.Object.User.RegisterUser;

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

import static android.content.Context.MODE_PRIVATE;

public class Drawables {
    public static String base_url = "https://vit-events-app.herokuapp.com";
    public static int time=400;
    public static int searchTime=400;
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
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("likes",json);
        //Log.i("save_json",json);
        editor.apply();
    }
    public static List<String> getLikes(Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String json = pref.getString("likes","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list=new ArrayList<>();
        list= gson.fromJson(json,type);
        if (list==null){
            list = new ArrayList<>();
        }
        //Log.i("get_json",list.toString());
        return list;
    }
    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void savedEvent(List<String> saved, Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(saved);
        editor.putString("event",json);
        //Log.i("save_json",json);
        editor.apply();
    }
    public static List<String> getSavedEvent(Context context){
        SharedPreferences pref = context.getSharedPreferences("com.panshul.evo", MODE_PRIVATE);
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
    public static void likeEvent(String eventId,Context context){
        SharedPreferences preferences = context.getSharedPreferences("com.panshul.evo.start",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getString("token","").equals("")){
            Call<LikeResponse> call = api.likeEvent(new LikeBody(eventId,preferences.getString("token","")));
            call.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                    try {
                        LikeResponse object = response.body();
                        if (object.getMessage().equals("Likes updated!")){

                        }else {

                        }
                    }catch (Exception e){

                    }
                }
                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<RegisterUser> call = Drawables.api.registerNewDevice();
            call.enqueue(new Callback<RegisterUser>() {
                @Override
                public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                    try {
                        RegisterUser object = response.body();
                        editor.putString("token",object.getId());
                        editor.commit();
                        editor.apply();
                        likeEvent(eventId,context);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterUser> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static void unlikeEvent(String eventId,Context context){
        SharedPreferences preferences = context.getSharedPreferences("com.panshul.evo.start",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getString("token","").equals("")){
            Call<LikeResponse> call = api.dislikeEvent(new LikeBody(eventId,preferences.getString("token","")));
            call.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                    try {
                        LikeResponse object = response.body();
                        if (object.getMessage().equals("Likes updated!")){

                        }else {

                        }
                    }catch (Exception e){

                    }
                }
                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<RegisterUser> call = Drawables.api.registerNewDevice();
            call.enqueue(new Callback<RegisterUser>() {
                @Override
                public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                    try {
                        RegisterUser object = response.body();
                        editor.putString("token",object.getId());
                        editor.commit();
                        editor.apply();
                        likeEvent(eventId,context);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterUser> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static void interestedEvent(String eventId,Context context){
        SharedPreferences preferences = context.getSharedPreferences("com.panshul.evo.start",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getString("token","").equals("")){
            Call<LikeResponse> call = api.interestedEvent(new LikeBody(eventId,preferences.getString("token","")));
            call.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                    try {
                        LikeResponse object = response.body();
                        if (object.getMessage().equals("List updated!")){

                        }else {

                        }
                    }catch (Exception e){

                    }
                }
                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<RegisterUser> call = Drawables.api.registerNewDevice();
            call.enqueue(new Callback<RegisterUser>() {
                @Override
                public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                    try {
                        RegisterUser object = response.body();
                        editor.putString("token",object.getId());
                        editor.commit();
                        editor.apply();
                        likeEvent(eventId,context);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterUser> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static void noInterestedEvent(String eventId,Context context){
        SharedPreferences preferences = context.getSharedPreferences("com.panshul.evo.start",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getString("token","").equals("")){
            Call<LikeResponse> call = api.noInterestedEvent(new LikeBody(eventId,preferences.getString("token","")));
            call.enqueue(new Callback<LikeResponse>() {
                @Override
                public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                    try {
                        LikeResponse object = response.body();
                        if (object.getMessage().equals("List updated!")){

                        }else {

                        }
                    }catch (Exception e){

                    }
                }
                @Override
                public void onFailure(Call<LikeResponse> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<RegisterUser> call = Drawables.api.registerNewDevice();
            call.enqueue(new Callback<RegisterUser>() {
                @Override
                public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                    try {
                        RegisterUser object = response.body();
                        editor.putString("token",object.getId());
                        editor.commit();
                        editor.apply();
                        likeEvent(eventId,context);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<RegisterUser> call, Throwable t) {
                    Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
