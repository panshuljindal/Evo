package com.panshul.evo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panshul.evo.Activity.EventActivity;
import com.panshul.evo.Adapter.EventAdapter;
import com.panshul.evo.Object.EventObject;
import com.panshul.evo.R;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {

    View view;
    Context context;
    List<EventObject> list;
    TextView all,speakers,workshops,hackathons;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_event, container, false);
        context=view.getContext();
        findViewByIds();
        list=new ArrayList<>();
        //startActivity(new Intent(context, EventActivity.class));
        addData(1);
        adapter();
        Onclick();
        return view;
    }
    private void findViewByIds(){
        all=view.findViewById(R.id.eventAll);
        speakers=view.findViewById(R.id.eventSpeakers);
        workshops=view.findViewById(R.id.eventWorkshops);
        hackathons=view.findViewById(R.id.eventHackathon);
        recyclerView=view.findViewById(R.id.eventsRecycler);

    }
    private void addData(int i){

    }
    private void adapter(){
        EventAdapter adapter = new EventAdapter(list,context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
    private void Onclick(){
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_selected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                addData(1);
            }
        });
        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                speakers.setBackground(getResources().getDrawable(R.drawable.event_selected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                addData(2);
            }
        });
        workshops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_selected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                addData(3);
            }
        });
        hackathons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakers.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                workshops.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                hackathons.setBackground(getResources().getDrawable(R.drawable.event_selected));
                addData(4);
            }
        });
    }
}