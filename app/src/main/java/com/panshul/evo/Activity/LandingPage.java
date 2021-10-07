package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.panshul.evo.R;

import java.util.UUID;

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
                startActivity(new Intent(LandingPage.this, NavigationActivity.class));
                editor.putBoolean("startFinish",true);

                String uuid = UUID.randomUUID().toString();
                editor.putString("token",uuid);
                editor.commit();
                editor.apply();
                finish();
            }
        });

    }
}