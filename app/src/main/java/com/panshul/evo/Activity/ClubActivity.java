package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.panshul.evo.Adapter.UpcomingEventsAdapter;
import com.panshul.evo.Object.Club.ClubEventObject;
import com.panshul.evo.Object.Club.ClubSpecificObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubActivity extends AppCompatActivity {
    ImageView back,poster,logo,insta,facebook,twitter,linkedin,medium;
    TextView name,tagline,description,knowMore,upcoming,seeAll;
    RecyclerView recyclerView;
    ClubSpecificObject object;
    ConstraintLayout cl,lottie1,serverError;
    LottieAnimationView lottie;
    String clubId;
    ImageView isPartner;
    boolean isDone;
    int time;
    Button tryAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        findViewByIds();
        onclick();
        isDone=false;
        time=Drawables.time;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDone){

                }else {
                    lottie1.setVisibility(View.VISIBLE);
                    lottie.setVisibility(View.VISIBLE);
                }
            }
        },time);
        addData();

    }
    private void addData(){
        clubId=getIntent().getStringExtra("clubId");
        Call<ClubSpecificObject> call = Drawables.api.getSpecificClub(clubId);
        call.enqueue(new Callback<ClubSpecificObject>() {
            @Override
            public void onResponse(Call<ClubSpecificObject> call, Response<ClubSpecificObject> response) {
                try {
                    object=response.body();
                    setOption();
                }catch (Exception e){
                    Log.i("exception", e.toString());
                    isDone=true;
                    lottie1.setVisibility(View.GONE);
                    lottie.setVisibility(View.GONE);
                    lottie.pauseAnimation();
                    serverError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ClubSpecificObject> call, Throwable t) {
                isDone=true;
                lottie1.setVisibility(View.GONE);
                lottie.setVisibility(View.GONE);
                lottie.pauseAnimation();
                serverError.setVisibility(View.VISIBLE);
            }
        });
    }
    private void setOption(){
        Glide.with(ClubActivity.this).load(object.getBackdrop()).into(poster);
        Glide.with(ClubActivity.this).load(object.getLogo()).into(logo);
        logo.setClipToOutline(true);
        if(object.isPartner()){
            isPartner.setVisibility(View.VISIBLE);
        }
        logo.setClipToOutline(true);

        tagline.setText(object.getTagline());
        if (object.getEvents().size()>2){
            seeAll.setVisibility(View.VISIBLE);
        }else {
            seeAll.setVisibility(View.INVISIBLE);
        }
        if (object.getDescription().length()<=120 && object.getDescription().length()!=0){
            description.setText(object.getDescription().substring(0,object.getDescription().length()/2-2)+"..");
        }else if(object.getDescription().length()!=0){
            description.setText(object.getDescription().substring(0,118)+"..");
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
        if (object.getName().length()>25){
            name.setText(object.getName().substring(0,23)+"..");
        }else {
            name.setText(object.getName());
        }
        if (object.getEvents().size()>0){
            if(object.getEvents().size()>2){
                upcoming.setText("Upcoming Events(2)");
            }else {
                upcoming.setText("Upcoming Events ("+String.valueOf(object.getEvents().size())+")");
            }
        }else {
            upcoming.setText("");
            seeAll.setVisibility(View.GONE);
        }
        upcomingAdapter(object);
        isDone=true;
        lottie1.setVisibility(View.GONE);
        lottie.setVisibility(View.GONE);
        lottie.pauseAnimation();

    }
    private void upcomingAdapter(ClubSpecificObject object1){
        List<ClubEventObject> finalList = new ArrayList<>();
        for (int i=0;i<object1.getEvents().size();i++){
            finalList.add(object1.getEvents().get(i));
            if (i==1){
                break;
            }
        }
        UpcomingEventsAdapter adapter = new UpcomingEventsAdapter(ClubActivity.this,finalList);
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
        lottie=findViewById(R.id.clubAnimationView);
        lottie1=findViewById(R.id.clubLottieAnimation);
        isPartner = findViewById(R.id.clubVerified);
        serverError=findViewById(R.id.clubEmptyCl);
        tryAgain = findViewById(R.id.clubTryAgain);
    }
    void onclick(){
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Drawables.isNetworkAvailable(ClubActivity.this)){
                    serverError.setVisibility(View.GONE);
                    isDone=false;
                    lottie1.setVisibility(View.VISIBLE);
                    lottie.setVisibility(View.VISIBLE);
                    lottie.playAnimation();
                    addData();
                }else {
                    Toast.makeText(ClubActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                       description.setText(object.getDescription().substring(0,object.getDescription().length()/2-2)+"..");
                   }else {
                       description.setText(object.getDescription().substring(0,118)+"..");
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