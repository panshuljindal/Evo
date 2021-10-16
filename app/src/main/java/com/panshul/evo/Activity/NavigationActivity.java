package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.panshul.evo.Fragments.EventFragment;
import com.panshul.evo.Fragments.PopularFragment;
import com.panshul.evo.Fragments.SavedFragment;
import com.panshul.evo.Fragments.SearchFragment;
import com.panshul.evo.Fragments.SettingsFragment;
import com.panshul.evo.Object.User.RegisterUser;
import com.panshul.evo.Object.User.UserMetaData;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationActivity extends AppCompatActivity {
    AnimatedBottomBar animatedBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_acitivity);

        animatedBottomBar = findViewById(R.id.bottom_bar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                Fragment fragment = null;
                switch (tab1.getId()){
                    case R.id.tab_event:
                        fragment=new EventFragment();
                        break;
                    case R.id.tab_search:
                        fragment= new PopularFragment();
                        break;
                    case R.id.tab_saved:
                        fragment = new SavedFragment();
                        break;
                    case R.id.tab_settings:
                        fragment = new SettingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }


            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EventFragment()).commit();
        getUserData();
    }
    private void getUserData(){
        SharedPreferences pref = getSharedPreferences("com.panshul.evo.start",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String token=pref.getString("token","");
        if (token.equals("")){
            Call<RegisterUser> call = Drawables.api.registerNewDevice();
            call.enqueue(new Callback<RegisterUser>() {
                @Override
                public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                    try {
                        RegisterUser object = response.body();
                        editor.putString("token",object.getId());
                        editor.commit();
                        editor.apply();
                        finish();
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(Call<RegisterUser> call, Throwable t) {

                }
            });

        }else {
            Call<UserMetaData> call = Drawables.api.getUserData(token);
            call.enqueue(new Callback<UserMetaData>() {
                @Override
                public void onResponse(Call<UserMetaData> call, Response<UserMetaData> response) {

                    try {
                        UserMetaData data = response.body();
                        Drawables.savedEvent(data.getInterestedEvents(),NavigationActivity.this);
                        Drawables.saveLiked(data.getLikedEvents(),NavigationActivity.this);

                    }catch (Exception e){

                    }
                }
                @Override
                public void onFailure(Call<UserMetaData> call, Throwable t) {

                }
            });
        }
    }
}