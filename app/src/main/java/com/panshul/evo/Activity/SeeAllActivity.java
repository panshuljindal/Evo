package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.panshul.evo.Adapter.UpcomingEventsAdapter;
import com.panshul.evo.Object.Club.ClubAllEvents;
import com.panshul.evo.Object.Club.ClubSpecificObject;
import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Event.EventMetadataObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Api;
import com.panshul.evo.Services.Drawables;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllActivity extends AppCompatActivity {
    TextView all,riviera,gravitas,speakers,workshops,hackathons,cultural,ngo;
    int type;
    RecyclerView recyclerView;
    HorizontalScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        findViewByIds();
        type=1;
        addData();
        Onclick();

    }
    private void addData(){
        Intent i = getIntent();
        String id = i.getStringExtra("clubId");
        Call<ClubAllEvents> call;
        Api api = Drawables.api;

        call = api.getAllClubEvents(id);
        call.enqueue(new Callback<ClubAllEvents>() {
            @Override
            public void onResponse(Call<ClubAllEvents> call, Response<ClubAllEvents> response) {

                List<EventMetadataObject> metaList  = new ArrayList<>();
                List<String> typeList=new ArrayList<>();
                upcomingAdapter(response.body());
                metaList= response.body().getMetadata();
                for (EventMetadataObject metaData: metaList){
                    typeList.add(metaData.get_id());
                }
                if (typeList.contains("Gravitas")){
                    gravitas.setVisibility(View.VISIBLE);
                }
                if(typeList.contains("Riviera")){
                    riviera.setVisibility(View.VISIBLE);
                }
                if(typeList.contains("Hackathon")){
                    hackathons.setVisibility(View.VISIBLE);
                }
                if(typeList.contains("Workshops")){
                    workshops.setVisibility(View.VISIBLE);
                }
                if (typeList.contains("Speakers")){
                    speakers.setVisibility(View.VISIBLE);
                }
                if(typeList.contains("Cultural")){
                    cultural.setVisibility(View.VISIBLE);
                }
                if(typeList.contains("NGO")){
                    ngo.setVisibility(View.VISIBLE);
                }
                scrollView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ClubAllEvents> call, Throwable t) {

            }
        });
//        if (i==1){
//            call = api.getAllEvents();
//        }
//        else if(i==2){
//            call = api.getGravitasEvents();
//        }
//        else if(i==3){
//            call = api.getRivieraEvents();
//        }
//        else if(i==4){
//            call = api.getHackathonEvents();
//        }
//
//        else if(i==5){
//            call = api.getSpeakerEvents();
//        }
//
//        else if(i==6){
//            call = api.getWorkshopEvents();
//        }
//
//        else if(i==7){
//            call = api.getCulturalEvents();
//        }
//
//        else if(i==8){
//            call = api.getNGOEvents();
//        }else {
//            call=api.getAllEvents();
//        }

    }
    private void findViewByIds(){
        all=findViewById(R.id.seeAllAll);
        riviera=findViewById(R.id.seeAllRiviera);
        gravitas=findViewById(R.id.seeAllGravitas);
        speakers=findViewById(R.id.seeAllSpeakers);
        workshops=findViewById(R.id.seeAllWorkshops);
        hackathons=findViewById(R.id.seeAllHackathon);
        cultural=findViewById(R.id.seeAllCultural);
        ngo=findViewById(R.id.seeAllNGO);
        scrollView = findViewById(R.id.seeAll);
        recyclerView = findViewById(R.id.seeAllRecyclerView);
    }
    private void Onclick(){
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_selected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==1){

                }
                else {
                    type=1;
                }
            }
        });
        gravitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_selected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==2){

                }
                else {
                    type=2;
                }
            }
        });
        riviera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_selected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==3){

                }
                else {
                    type=3;
                }
            }
        });
        hackathons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_selected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==4){

                }
                else {
                    type=4;
                }
            }
        });
        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_selected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==5){

                }
                else {
                    type=5;
                }
            }
        });
        workshops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_selected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==6){

                }
                else {
                    type=6;
                }
            }
        });
        cultural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_selected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==7){

                }
                else {
                    type=7;
                }
            }
        });
        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                gravitas.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                riviera.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                cultural.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                ngo.setBackground(getResources().getDrawable(R.drawable.event_selected));
                if (type==8){

                }
                else {
                    type=8;
                }
            }
        });
    }
    private void upcomingAdapter(ClubAllEvents object){
        UpcomingEventsAdapter adapter = new UpcomingEventsAdapter(SeeAllActivity.this,object.getData());
        LinearLayoutManager manager = new LinearLayoutManager(SeeAllActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
}