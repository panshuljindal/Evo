package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
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
    ClubSpecificObject object;
    ConstraintLayout cl,clubLogoCl;
    String clubId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        findViewByIds();
        onclick();
        clubId=getIntent().getStringExtra("clubId");
        Call<ClubSpecificObject> call = Drawables.api.getSpecificClub(clubId);
        call.enqueue(new Callback<ClubSpecificObject>() {
            @Override
            public void onResponse(Call<ClubSpecificObject> call, Response<ClubSpecificObject> response) {
                object=response.body();
                setOption();
            }

            @Override
            public void onFailure(Call<ClubSpecificObject> call, Throwable t) {
            }
        });

    }
    private void setOption(){
        Glide.with(ClubActivity.this).load(object.getBackdrop()).into(poster);
        Glide.with(ClubActivity.this).load(object.getLogo()).into(logo);
        //logo.setClipToOutline(true);
        name.setText(object.getName());
        tagline.setText(object.getTagline());
        if (object.getDescription().length()<=120){
            description.setText(object.getDescription().substring(0,object.getDescription().length()/2));
        }else {
            description.setText(object.getDescription().substring(0,120));
        }
        if (!object.getInstagram().equals("")){
            insta.setVisibility(View.VISIBLE);
        }
        if (!object.getFacebook().equals("")){
            facebook.setVisibility(View.VISIBLE);
        }
        if (!object.getTwitter().equals("")){
            twitter.setVisibility(View.VISIBLE);
        }
        if (!object.getLinkedIn().equals("")){
            linkedin.setVisibility(View.VISIBLE);
        }
        if (!object.getMedium().equals("")){
            medium.setVisibility(View.VISIBLE);
        }
        if (object.getEvents().size()>0){
            upcoming.setText("Upcoming Events ("+String.valueOf(object.getEvents().size())+")");
            knowMore.setVisibility(View.VISIBLE);
        }else {
            upcoming.setText("");
            seeAll.setVisibility(View.GONE);
        }
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
        cl=findViewById(R.id.eventConstraintLayout);
        //clubLogoCl=findViewById(R.id.clubLogoCl);
    }
    void onclick(){

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(object.getInstagram()));
                startActivity(intent);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(object.getFacebook()));
                startActivity(intent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(object.getTwitter()));
                startActivity(intent);
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(object.getLinkedIn()));
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(object.getMedium()));
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(ClubActivity.this,SeeAllActivity.class);
                i.putExtra("clubId",object.get_id());
                startActivity(i);
            }
        });
        knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (knowMore.getText().toString().equals("Know more")){
                   description.setText(object.getDescription());
                   knowMore.setText("See Less");
                   cl.setVisibility(View.GONE);
               }
               else {
                   if (object.getDescription().length()<=120){
                       description.setText(object.getDescription().substring(0,object.getDescription().length()/2));
                   }else {
                       description.setText(object.getDescription().substring(0,120));
                   }
                   knowMore.setText("Know more");
                   cl.setVisibility(View.VISIBLE);
               }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}