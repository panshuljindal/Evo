package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.panshul.evo.R;

public class SeeAllActivity extends AppCompatActivity {
    TextView all,riviera,gravitas,speakers,workshops,hackathons,cultural,ngo;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        findViewByIds();
        type=1;
        Onclick();

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
}