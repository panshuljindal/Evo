package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.panshul.evo.Fragments.EventFragment;
import com.panshul.evo.Object.Event.EventRoot;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.hanks.library.bang.SmallBangView;

public class EventActivity extends AppCompatActivity {

    ImageView back,photo,clubLogo,save,verified;
    TextView eventName,likeTextView,clubName,eventDate,eventPrice,eventDuration,eventDescription,textViewSave;
    Button registerNow;
    ConstraintLayout savedCl,lottie1,eventChoose,eventChooseMain;
    Button yesVit,noVit;
    LottieAnimationView lottie;
    EventSpecificObject object;
    List<String> saved;
    SmallBangView likeImage;
    List<String> likes;
    boolean isDone;
    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        saved = new ArrayList<>();
        findViewByIds();
        isDone=false;
        time=Drawables.time;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDone){

                }else {
                   // Log.i("isdone",String.valueOf(isDone));
                    lottie1.setVisibility(View.VISIBLE);
                    lottie.setVisibility(View.VISIBLE);
                }
            }
        },time);
        addData();
        onclick();
    }
    private void addData(){
        Intent intent = getIntent();
        String eventId = intent.getStringExtra("eventId");
        Call<EventRoot> call = Drawables.api.getSpecificEvent(eventId);
        call.enqueue(new Callback<EventRoot>() {
            @Override
            public void onResponse(Call<EventRoot> call, Response<EventRoot> response) {
                try {
                    object=response.body().getEvent();
                    setOption();
                    isDone=true;
                    lottie.pauseAnimation();
                    lottie.setVisibility(View.GONE);
                    lottie1.setVisibility(View.GONE);
                }catch (Exception e){
                    Log.i("exception",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<EventRoot> call, Throwable t) {

            }
        });
    }
    private void onclick(){
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                eventChoose.setVisibility(View.VISIBLE);
//                eventChooseMain.setVisibility(View.VISIBLE);
//                Animation Anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
//                eventChoose.setAnimation(Anim);
//                eventChooseMain.setAnimation(Anim);

                // redirect to CDC Portal
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://vtop.vit.ac.in/vtop/open/page"));
                startActivity(i);
            }
        });
        eventChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation Anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
                eventChooseMain.setAnimation(Anim);
                eventChoose.setAnimation(Anim);
                eventChooseMain.setVisibility(View.GONE);
                eventChoose.setVisibility(View.GONE);
            }
        });
        yesVit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(object.isVtopRegistrations()){
                    intent.setData(Uri.parse("https://vtop.vit.ac.in/vtop/initialProcess"));
                }else{
                    if(object.getRegistrationLink().length()==0){
                        Toast.makeText(EventActivity.this, "Link not found!", Toast.LENGTH_SHORT).show();
                    }else {
                        intent.setData(Uri.parse(object.getRegistrationLink()));
                    }
                }
                try {
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(EventActivity.this, "Error Occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
                eventChoose.setVisibility(View.GONE);
                eventChooseMain.setVisibility(View.GONE);
            }
        });
        noVit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if(object.getRegistrationLink().length()==0){
                    Toast.makeText(EventActivity.this, "Link not found!", Toast.LENGTH_SHORT).show();
                }else {
                    intent.setData(Uri.parse(object.getRegistrationLink()));
                }
                try {
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(EventActivity.this, "Error Occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
                eventChoose.setVisibility(View.GONE);
                eventChooseMain.setVisibility(View.GONE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        clubLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i =new Intent(v.getContext(), ClubActivity.class);
//                i.putExtra("clubId",object.getClubId().get_id());
//                startActivity(i);
//            }
//        });
        eventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(v.getContext(), ClubActivity.class);
                i.putExtra("clubId",object.getClubId().get_id());
                startActivity(i);
            }
        });
        savedCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saved.contains(object.get_id())){
                    save.setImageResource(R.drawable.ic_saved);
                    textViewSave.setText("Saved For Later");
                    Drawables.noInterestedEvent(object.get_id(),EventActivity.this);
                    saved.remove(object.get_id());
                    Drawables.savedEvent(saved,EventActivity.this);
                }else {
                    saved.add(object.get_id());
                    save.setImageResource(R.drawable.ic_savednot);
                    Drawables.interestedEvent(object.get_id(),EventActivity.this);
                    textViewSave.setText("Interested");
                    Drawables.savedEvent(saved,EventActivity.this);
                }
            }
        });
        likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likes.contains(object.get_id())){
                    Drawables.unlikeEvent(object.get_id(),EventActivity.this);
                    likes.remove(object.get_id());
                    Drawables.saveLiked(likes,EventActivity.this);
                    EventFragment.adapter.notifyDataSetChanged();
                    likeTextView.setText(String.valueOf(object.getLikes()-1)+" likes");
                    object.setLikes(object.getLikes()-1);
                    likeImage.setSelected(false);
                    likeImage.likeAnimation();
                }else {
                    Drawables.likeEvent(object.get_id(),EventActivity.this);
                    likes.add(object.get_id());
                    Drawables.saveLiked(likes,EventActivity.this);
                    EventFragment.adapter.notifyDataSetChanged();
                    likeTextView.setText(String.valueOf(object.getLikes()+1)+" likes");
                    object.setLikes(object.getLikes()+1);
                    likeImage.setSelected(true);
                    likeImage.likeAnimation();
                }
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
        if (object.getClubId().isPartner()){
            verified.setVisibility(View.VISIBLE);
        }else {
            verified.setVisibility(View.INVISIBLE);
        }
        if (object.isPaid()==true){
            eventPrice.setText(object.getEventCost()+" Rs");
        }else {
            eventPrice.setText("Free");
        }
        eventDuration.setText(object.getDuration()+" hours");
        eventDescription.setText(object.getInfo());

        saved = Drawables.getSavedEvent(EventActivity.this);
        if (saved.contains(object.get_id())){
            save.setImageResource(R.drawable.ic_savednot);
            textViewSave.setText("Interested");
        }else {
            save.setImageResource(R.drawable.ic_saved);
            textViewSave.setText("Saved For Later");
        }
        likes = Drawables.getLikes(EventActivity.this);
        if (likes.contains(object.get_id())){
            likeImage.setSelected(true);
        }
        else {
            likeImage.setSelected(false);
        }

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
        likeImage=findViewById(R.id.eventImageViewAnimation);
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
        textViewSave = findViewById(R.id.textViewSave);
        savedCl = findViewById(R.id.savedConstraintLayout);
        lottie1=findViewById(R.id.eventLottieAnimation);
        lottie=findViewById(R.id.eventMainAnimationView);
        verified=findViewById(R.id.eventMainVerified);
        eventChoose=findViewById(R.id.eventChoose);
        eventChooseMain=findViewById(R.id.eventChooseMain);
        yesVit = findViewById(R.id.yesVit);
        noVit=findViewById(R.id.noVit);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (eventChooseMain.getVisibility()==View.VISIBLE){
            Animation Anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
            eventChooseMain.setAnimation(Anim);
            eventChoose.setAnimation(Anim);
            eventChooseMain.setVisibility(View.GONE);
            eventChoose.setVisibility(View.GONE);
        }else {
            finish();
        }
    }
}