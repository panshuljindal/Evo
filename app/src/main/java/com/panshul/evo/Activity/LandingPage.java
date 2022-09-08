package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.panshul.evo.Object.Like.LikeBody;
import com.panshul.evo.Object.User.RegisterUser;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        SharedPreferences preferences = getSharedPreferences("com.panshul.evo.start",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        boolean start = preferences.getBoolean("startFinish",false);
        if (start){
            startActivity(new Intent(LandingPage.this, NavigationActivity.class));
            finish();
        }else {

        }
        Button letsGo=findViewById(R.id.landingLetsGo);
        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("startFinish",true);

                Call<RegisterUser> call = Drawables.api.registerNewDevice();
                call.enqueue(new Callback<RegisterUser>() {
                    @Override
                    public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                        try {
                            RegisterUser object = response.body();
                            editor.putString("token",object.getId());
                            editor.commit();
                            editor.apply();
                            startActivity(new Intent(LandingPage.this, NavigationActivity.class));

                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterUser> call, Throwable t) {

                    }
                });
//                Toast.makeText(LandingPage.this, "Coming Soon!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}