package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.panshul.evo.R;

public class EventActivity extends AppCompatActivity {

    ImageView back,photo,like,clubLogo,save;
    TextView eventName,clubName,eventDate,eventPrice,eventDuration,eventDescription;
    Button registerNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        findViewByIds();
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
    }

    }