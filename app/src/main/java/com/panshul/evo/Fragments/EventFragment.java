package com.panshul.evo.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.panshul.evo.Adapter.EventAdapter;
import com.panshul.evo.Object.Event.EventClubObject;
import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Event.EventMetadataObject;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Api;
import com.panshul.evo.Services.Drawables;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventFragment extends Fragment {

    View view;
    Context context;
    public static List<EventObject> list;
    HorizontalScrollView scrollView;
    TextView all,riviera,gravitas,speakers,workshops,hackathons,cultural,ngo;
    ConstraintLayout empty;
    public static RecyclerView recyclerView;
    public static int type;
    public static EventAdapter adapter;
    boolean isData;
    int time;
    public static LottieAnimationView lottie;
    Call<EventMainObject> call;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_event, container, false);
        findViewByIds();
        //Log.i("uuid", view.getContext().getSharedPreferences("com.panshul.evo.start",Context.MODE_PRIVATE).getString("token",""));
        isData=false;
        time=Drawables.time;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isData){

                }
                else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    lottie.setVisibility(View.VISIBLE);
                }
            }
        },time);
        context=view.getContext();
        type=1;
        list=new ArrayList<>();
        addData(1);
        adapter();
        Onclick();
        return view;
    }
    private void findViewByIds(){
        all=view.findViewById(R.id.eventAll);
        riviera=view.findViewById(R.id.eventRiviera);
        gravitas=view.findViewById(R.id.eventGravitas);
        speakers=view.findViewById(R.id.eventSpeakers);
        workshops=view.findViewById(R.id.eventWorkshops);
        hackathons=view.findViewById(R.id.eventHackathon);
        cultural=view.findViewById(R.id.eventCultural);
        ngo=view.findViewById(R.id.eventNGO);
        recyclerView=view.findViewById(R.id.eventsRecycler);
        scrollView = view.findViewById(R.id.horizontalScrollView);
        empty = view.findViewById(R.id.eventsEmpty);
        lottie = view.findViewById(R.id.eventAnimationView);
    }
    private void addData(int i){
        Api api = Drawables.api;
        if (i==1){
            call = api.getAllEvents(0);
        }
        else if(i==2){
            call = api.getGravitasEvents(0);
        }
        else if(i==3){
            call = api.getRivieraEvents(0);
        }
        else if(i==4){
            call = api.getHackathonEvents(0);
        }

        else if(i==5){
            call = api.getSpeakerEvents(0);
        }

        else if(i==6){
            call = api.getWorkshopEvents(0);
        }

        else if(i==7){
            call = api.getCulturalEvents(0);
        }

        else if(i==8){
            call = api.getNGOEvents(0);
        }else {
            call=api.getAllEvents(0);
        }
        call.enqueue(new Callback<EventMainObject>() {
            @Override
            public void onResponse(Call<EventMainObject> call, Response<EventMainObject> response) {
                try {
                    EventMainObject object = response.body();
                    list = object.getData();
                    //list.addAll(object.getData());
                    //Log.i("list",String.valueOf(list.size()));
                    if (list.size()==0){
                        empty.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        scrollView.setVisibility(View.GONE);
                        isData=true;
                        lottie.setVisibility(View.GONE);
                        lottie.pauseAnimation();
                    }
                    else {
                        if (list.size()<10 && list.size()>1){
                            List<String>list2 = new ArrayList<>();
                            list2.add("Hello");
                            list.add(new EventObject(list2,0,"isDone","","",new EventClubObject("","",true),""));
                        }
                        adapter();
                        isData=true;

                        lottie.setVisibility(View.GONE);
                        lottie.pauseAnimation();
                        empty.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.VISIBLE);
                        List<EventMetadataObject> metaList = new ArrayList<>();
                        metaList = object.getMetadata();
                        List<String> typeList = new ArrayList<>();

                        for (EventMetadataObject metaData : metaList) {
                            typeList.add(metaData.get_id());
                        }
                        if (typeList.contains("Gravitas")) {
                            gravitas.setVisibility(View.VISIBLE);
                        }
                        if (typeList.contains("Riviera")) {
                            riviera.setVisibility(View.VISIBLE);
                        }
                        if (typeList.contains("Hackathon")) {
                            hackathons.setVisibility(View.VISIBLE);
                        }
                        if (typeList.contains("Workshops")) {
                            workshops.setVisibility(View.VISIBLE);
                        }
                        if (typeList.contains("Speakers")) {
                            speakers.setVisibility(View.VISIBLE);
                        }
                        if (typeList.contains("Cultural")) {
                            cultural.setVisibility(View.VISIBLE);
                        }
                        if (typeList.contains("NGO")) {
                            ngo.setVisibility(View.VISIBLE);
                        }
                    }

                }
                catch (Exception e){
                    empty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    isData=true;
                    lottie.setVisibility(View.GONE);
                    lottie.pauseAnimation();
                }
            }

            @Override
            public void onFailure(Call<EventMainObject> call, Throwable t) {
                empty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                scrollView.setVisibility(View.GONE);
                isData=true;
                lottie.setVisibility(View.GONE);
                lottie.pauseAnimation();
            }
        });
    }
    private void adapter(){
        adapter = new EventAdapter(list,view.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=1;
                    addData(1);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=2;
                    addData(2);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=3;
                    addData(3);
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
                    isData=false;

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=4;
                    addData(4);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=5;
                    addData(5);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=6;
                    addData(6);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=7;
                    addData(7);
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
                    isData=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(isData){

                            }
                            else {
                                recyclerView.setVisibility(View.INVISIBLE);
                                lottie.playAnimation();
                                lottie.setVisibility(View.VISIBLE);
                            }
                        }
                    },time);
                    type=8;
                    addData(8);
                }
            }
        });
    }
}