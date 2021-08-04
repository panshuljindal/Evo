package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panshul.evo.R;

public class MusicActivity extends AppCompatActivity {
    ImageView back,poster,logo,insta,facebook,twitter,linkedin,medium;
    TextView name,tagline,description,knowMore,upcoming,seeAll;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        findViewByIds();
        onclick();

    }
    void findViewByIds(){
        back = findViewById(R.id.clubBack);
        poster = findViewById(R.id.clubImage);
        logo = findViewById(R.id.clubLogo);
        insta = findViewById(R.id.clubInsta);
        facebook = findViewById(R.id.clubFacebook);
        twitter = findViewById(R.id.clubTwitter);
        linkedin = findViewById(R.id.clubLinkedin);
        medium = findViewById(R.id.clubMedium);
        name = findViewById(R.id.clubTextView);
        tagline = findViewById(R.id.clubTagline);
        description = findViewById(R.id.clubDescription);
        knowMore = findViewById(R.id.clubKnowMore);
        upcoming = findViewById(R.id.clubUpcoming);
        seeAll = findViewById(R.id.clubSeeAll);
        recyclerView = findViewById(R.id.clubRecyclerView);
    }
    void onclick(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MusicActivity.this,SeeAllActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}