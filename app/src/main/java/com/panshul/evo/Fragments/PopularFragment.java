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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.panshul.evo.Services.Drawables.base_url;
import static com.panshul.evo.Services.Drawables.getClientInstance;

public class PopularFragment extends Fragment {

    View view;
    EditText searchEditText;
    RecyclerView popularRecyclerView;
    List<PopularMainObject> popularList;
    ImageView search;
    TextView popular;
    ConstraintLayout popularEmpty;
    LottieAnimationView popularLottie;
    boolean isDonePopular;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_popular, container, false);
        findViewByIds();
        isDonePopular=false;
        int time = Drawables.time;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDonePopular){

                }else {
                    popularLottie.setVisibility(View.VISIBLE);
                    popularRecyclerView.setVisibility(View.INVISIBLE);
                }
            }
        },time);
        addPopularData();
        onclick();
        popularAdapter();
        return view;
    }




    private void findViewByIds() {
        searchEditText=view.findViewById(R.id.popularEditText);
        popularRecyclerView=view.findViewById(R.id.popularRecycler);
        search=view.findViewById(R.id.searchSearch);
        popular=view.findViewById(R.id.popularTextview);
        popularList = new ArrayList<>();
        popularEmpty = view.findViewById(R.id.popularEmpty);
        popularLottie=view.findViewById(R.id.popularAnimationView);
    }
    private void addPopularData(){
        Call<List<PopularMainObject>> call;
        call = Drawables.api.getPopular();
        call.enqueue(new Callback<List<PopularMainObject>>() {
            @Override
            public void onResponse(Call<List<PopularMainObject>> call, Response<List<PopularMainObject>> response) {
                try {
                    popularList=new ArrayList<>();
                    popularList = response.body();
                    if (popularList.size()==0){
                        popularEmpty.setVisibility(View.VISIBLE);
                        popularRecyclerView.setVisibility(View.GONE);
                        isDonePopular=true;
                        popularLottie.setVisibility(View.GONE);
                        popularLottie.pauseAnimation();
                    }else {
                        popularEmpty.setVisibility(View.GONE);
                        popularRecyclerView.setVisibility(View.VISIBLE);
                        popularAdapter();
                        isDonePopular=true;
                        popularLottie.setVisibility(View.GONE);
                        popularLottie.pauseAnimation();
                    }
                }catch (Exception e){
                    popularEmpty.setVisibility(View.VISIBLE);
                    popularRecyclerView.setVisibility(View.GONE);
                    isDonePopular=true;
                    popularLottie.setVisibility(View.GONE);
                    popularLottie.pauseAnimation();
                }
            }

            @Override
            public void onFailure(Call<List<PopularMainObject>> call, Throwable t) {
                popularEmpty.setVisibility(View.VISIBLE);
                popularRecyclerView.setVisibility(View.GONE);
                isDonePopular=true;
                popularLottie.setVisibility(View.GONE);
                popularLottie.pauseAnimation();
            }
        });
    }
    private void popularAdapter(){
        PopularAdapter adapter = new PopularAdapter(view.getContext(),popularList);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        popularRecyclerView.setAdapter(adapter);
        popularRecyclerView.setLayoutManager(manager);
    }

    private void onclick(){
        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment momDiagFrag = new SearchFragment();
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,momDiagFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        searchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Fragment momDiagFrag = new SearchFragment();
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,momDiagFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoftKeyboard(searchEditText);
                Fragment momDiagFrag = new SearchFragment();
                FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,momDiagFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
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