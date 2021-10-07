package com.panshul.evo.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.panshul.evo.Adapter.EventAdapter;
import com.panshul.evo.Adapter.PopularAdapter;
import com.panshul.evo.Adapter.SearchAdapter;
import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Popular.PopularMainObject;
import com.panshul.evo.Object.Search.SearchInput;
import com.panshul.evo.Object.Search.SearchObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Api;
import com.panshul.evo.Services.Drawables;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.panshul.evo.Services.Drawables.base_url;
import static com.panshul.evo.Services.Drawables.getClientInstance;

public class SearchFragment extends Fragment {

    View view;
    EditText searchEditText;
    HorizontalScrollView tabLayout;
    RecyclerView searchRecyclerView;
    public static List<SearchObject> searchList;
    ImageView cancel;
    TextView all,club,event;
    ConstraintLayout searchEmpty;
    LottieAnimationView searchLottie;
    public static int type;
    public static String searchInput;
    boolean isDoneSearch;
    Call<List<SearchObject>> callPopular;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_search, container, false);
        context=view.getContext();
        findViewByIds();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftKeyboard(searchEditText);
            }
        },100);
        type=0;
        onclick();
        return view;
    }
    void searchData(String input){
        searchList=new ArrayList<>();
        searchInput=input;
        if(type==0){
            callPopular= Drawables.api.getSearch( new SearchInput(input),0);
        }
        else if(type==1){
            callPopular=Drawables.api.getSearchClub(new SearchInput(input),0);

        }else if(type==2){
            callPopular=Drawables.api.getSearchEvent(new SearchInput(input),0);
        }else {
            callPopular= Drawables.api.getSearch( new SearchInput(input),0);
        }
        callPopular.enqueue(new Callback<List<SearchObject>>() {
            @Override
            public void onResponse(Call<List<SearchObject>> call, Response<List<SearchObject>> response) {
                try {
                    searchList = response.body();
                    searchAdapter(searchList);
                }catch (Exception e){
                    searchEmpty.setVisibility(View.VISIBLE);
                    searchRecyclerView.setVisibility(View.GONE);
                    isDoneSearch=true;
                    searchLottie.setVisibility(View.GONE);
                    searchLottie.pauseAnimation();
                }
            }

            @Override
            public void onFailure(Call<List<SearchObject>> call, Throwable t) {
                searchEmpty.setVisibility(View.VISIBLE);
                searchRecyclerView.setVisibility(View.GONE);
                isDoneSearch=true;
                searchLottie.setVisibility(View.GONE);
                searchLottie.pauseAnimation();
            }
        });
    }
    private void searchAdapter(List<SearchObject> searchList1){
        if (searchList1.size()==0){
            searchEmpty.setVisibility(View.VISIBLE);
            searchRecyclerView.setVisibility(View.GONE);
            isDoneSearch=true;
            searchLottie.setVisibility(View.GONE);
            searchLottie.pauseAnimation();
        }else {
            searchEmpty.setVisibility(View.GONE);
            searchRecyclerView.setVisibility(View.VISIBLE);
            isDoneSearch=true;
            searchLottie.setVisibility(View.GONE);
            searchLottie.pauseAnimation();
        }
        SearchAdapter adapter = new SearchAdapter(view.getContext(),searchList1);
        LinearLayoutManager manager= new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        searchRecyclerView.setAdapter(adapter);
        searchRecyclerView.setLayoutManager(manager);
    }



    private void findViewByIds() {
        searchEditText=view.findViewById(R.id.searchEditText);
        tabLayout = view.findViewById(R.id.horizontalViewSearch);
        searchRecyclerView=view.findViewById(R.id.searchRecyclerView);
        cancel=view.findViewById(R.id.searchCancel);
        all = view.findViewById(R.id.searchAll);
        club = view.findViewById(R.id.searchClubs);
        event=view.findViewById(R.id.searchEvents);
        searchEmpty = view.findViewById(R.id.searchEmpty);
        searchLottie=view.findViewById(R.id.searchAnimationView);
    }


    private void onclick(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                Fragment momDiagFrag = new PopularFragment();
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,momDiagFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchString=searchEditText.getText().toString();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchEmpty.setVisibility(View.GONE);
                        int time=Drawables.searchTime;
                        if (searchEditText.getText().toString().equals(searchString)){
                            isDoneSearch=false;
                            if (searchEditText.getText().toString().length()==0){
                                searchEmpty.setVisibility(View.VISIBLE);
                                searchRecyclerView.setVisibility(View.GONE);
                                isDoneSearch=true;
                                searchLottie.setVisibility(View.GONE);
                                searchLottie.pauseAnimation();
                            }
                            else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isDoneSearch){

                                        }else {
                                            searchLottie.playAnimation();
                                            searchLottie.setVisibility(View.VISIBLE);
                                        }
                                    }
                                },time);
                                try {
                                    callPopular.cancel();
                                    searchData(searchEditText.getText().toString());
                                }catch (Exception e){
                                    searchData(searchEditText.getText().toString());

                                }
                            }
                        }else {

                        }
                    }
                },400);

            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_selected));
                club.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                event.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==0){

                }else {
                    type=0;
                    if (searchEditText.getText().toString().length()!=0){                        int time=Drawables.searchTime;
                        int time1=Drawables.searchTime;
                        isDoneSearch=false;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isDoneSearch){

                                }else {
                                    searchLottie.playAnimation();
                                    searchLottie.setVisibility(View.VISIBLE);
                                }
                            }
                        },time1);
                        searchData(searchEditText.getText().toString());
                    }
                }

            }
        });

        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                club.setBackground(getResources().getDrawable(R.drawable.event_selected));
                event.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                if (type==1){

                }else {
                    type=1;
                    if (searchEditText.getText().toString().length()!=0){                        int time=Drawables.searchTime;
                        int time1=Drawables.searchTime;
                        isDoneSearch=false;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isDoneSearch){

                                }else {
                                    searchLottie.playAnimation();
                                    searchLottie.setVisibility(View.VISIBLE);
                                }
                            }
                        },time1);
                        searchData(searchEditText.getText().toString());
                    }
                }
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                club.setBackground(getResources().getDrawable(R.drawable.event_unselected));
                event.setBackground(getResources().getDrawable(R.drawable.event_selected));
                if (type==2){

                }else {
                    type=2;
                    if (searchEditText.getText().toString().length()!=0){                        int time=Drawables.searchTime;
                        int time1=Drawables.searchTime;
                        isDoneSearch=false;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isDoneSearch){

                                }else {
                                    searchLottie.playAnimation();
                                    searchLottie.setVisibility(View.VISIBLE);
                                }
                            }
                        },time1);
                        searchData(searchEditText.getText().toString());
                    }
                }
            }
        });


    }
    public void hideSoftKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view!=null){
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //searchEditText.setFocusable(false);

    }
}