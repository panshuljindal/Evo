package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.panshul.evo.Object.Event.EventRoot;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Api;
import com.panshul.evo.Services.Drawables;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventActivity extends AppCompatActivity {

    ImageView back,photo,like,clubLogo,save;
    TextView eventName,likeTextView,clubName,eventDate,eventPrice,eventDuration,eventDescription;
    Button registerNow;
    EventSpecificObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        findViewByIds();

        Intent intent = getIntent();
        String eventId = intent.getStringExtra("eventId");
        Call<EventRoot> call = Drawables.api.getSpecificEvent(eventId);
        call.enqueue(new Callback<EventRoot>() {
            @Override
            public void onResponse(Call<EventRoot> call, Response<EventRoot> response) {
                object=response.body().getEvent();
                setOption();
            }

            @Override
            public void onFailure(Call<EventRoot> call, Throwable t) {

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
        Glide.with(EventActivity.this).load(object.getClubId().getLogo()).into(clubLogo);
        Glide.with(EventActivity.this).load(object.getPoster()).into(photo);
        eventName.setText(object.getName());
        likeTextView.setText(object.getLikes()+" likes");
        clubName.setText(object.getClubName());
        eventDate.setText(getDate(object.getTimestamp()));
        if (object.isPaid()==true){
            eventPrice.setText(object.getEventCost()+" Rs");
        }else {
            eventPrice.setText("Free");
        }
        eventDuration.setText(object.getDuration()+" hours");
        //eventDescription.setText(object.get);
    }
    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("MMM dd, yyyy", cal).toString();
        return date;
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