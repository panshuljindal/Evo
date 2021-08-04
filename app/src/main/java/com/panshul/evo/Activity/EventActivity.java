package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Api;
import com.panshul.evo.Services.Drawables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventActivity extends AppCompatActivity {

    ImageView back,photo,like,clubLogo,save;
    TextView eventName,likeTextView,clubName,eventDate,eventPrice,eventDuration,eventDescription;
    Button registerNow;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Drawables.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);
    EventSpecificObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        findViewByIds();

        Intent intent = getIntent();
        String eventId = intent.getStringExtra("eventId");
        String poster = intent.getStringExtra("eventPoster");
        Glide.with(EventActivity.this).load(poster).into(photo);
        Call<EventSpecificObject> call = api.getSpecificEvent(eventId);
        call.enqueue(new Callback<EventSpecificObject>() {
            @Override
            public void onResponse(Call<EventSpecificObject> call, Response<EventSpecificObject> response) {
                object=response.body();
                setOption();
            }

            @Override
            public void onFailure(Call<EventSpecificObject> call, Throwable t) {

            }
        });
        onclick();

    }
    private void onclick(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
    private void setOption(){
        //Glide.with(EventActivity.this).load(object.get)
        eventName.setText(object.getName());
        likeTextView.setText(object.getLikes()+" likes");
        clubName.setText(object.getClubName());
    }
    private void findViewByIds(){
        back=findViewById(R.id.eventBack);
        photo=findViewById(R.id.eventImage);
        like=findViewById(R.id.eventLike);
        clubLogo=findViewById(R.id.eventClubLogo);
        save=findViewById(R.id.eventSave);
        eventName=findViewById(R.id.eventName);
        clubName=findViewById(R.id.eventClubName);
        eventDate=findViewById(R.id.eventDate);
        eventPrice=findViewById(R.id.eventPrice);
        eventDuration=findViewById(R.id.eventDuration);
        eventDescription=findViewById(R.id.eventDescription);
        registerNow = findViewById(R.id.eventRegisterNow);
        likeTextView = findViewById(R.id.eventLikes);
    }
}