package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.panshul.evo.Adapter.UpcomingEventsAdapter;
import com.panshul.evo.Object.Club.ClubSpecificObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubActivity extends AppCompatActivity {
    ImageView back,poster,logo,insta,facebook,twitter,linkedin,medium;
    TextView name,tagline,description,knowMore,upcoming,seeAll;
    RecyclerView recyclerView;

    String clubId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        findViewByIds();
        onclick();
        clubId=getIntent().getStringExtra("clubId");
        Log.i("clubId",clubId);
        Call<ClubSpecificObject> call = Drawables.api.getSpecificClub(clubId);
        call.enqueue(new Callback<ClubSpecificObject>() {
            @Override
            public void onResponse(Call<ClubSpecificObject> call, Response<ClubSpecificObject> response) {
                ClubSpecificObject object=response.body();
                setOption(object);
            }

            @Override
            public void onFailure(Call<ClubSpecificObject> call, Throwable t) {
            }
        });

    }
    private void setOption(ClubSpecificObject object){
        Glide.with(ClubActivity.this).load(object.getBackdrop()).into(poster);
        Glide.with(ClubActivity.this).load(object.getLogo()).into(logo);
        name.setText(object.getName());
        tagline.setText(object.getTagline());
        description.setText(object.getDescription());
        if (object.getInstagram().equals("")){
            insta.setVisibility(View.VISIBLE);
        }
        if (object.getFacebook().equals("")){
            facebook.setVisibility(View.VISIBLE);
        }
        if (object.getTwitter().equals("")){
            twitter.setVisibility(View.VISIBLE);
        }
        if (object.getLinkedIn().equals("")){
            linkedin.setVisibility(View.VISIBLE);
        }
        if (object.getMedium().equals("")){
            medium.setVisibility(View.VISIBLE);
        }
        upcoming.setText("Upcoming Events ("+String.valueOf(object.getEvents().size())+")");

        upcomingAdapter(object);
    }
    private void upcomingAdapter(ClubSpecificObject object){
        UpcomingEventsAdapter adapter = new UpcomingEventsAdapter(ClubActivity.this,object.getEvents());
        LinearLayoutManager manager = new LinearLayoutManager(ClubActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
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
                startActivity(new Intent(ClubActivity.this,SeeAllActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}