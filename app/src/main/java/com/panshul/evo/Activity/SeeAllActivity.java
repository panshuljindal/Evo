package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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
    RecyclerView recyclerView;
    HorizontalScrollView scrollView;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        findViewByIds();
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
            }

            @Override
            public void onFailure(Call<ClubAllEvents> call, Throwable t) {

            }
        });


    }
    private void findViewByIds(){
        scrollView = findViewById(R.id.seeAll);
        recyclerView = findViewById(R.id.seeAllRecyclerView);
        back=findViewById(R.id.seeAllBack);
    }
    private void Onclick(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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