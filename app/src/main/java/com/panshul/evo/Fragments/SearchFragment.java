package com.panshul.evo.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
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

public class SearchFragment extends Fragment {

    View view;
    EditText searchEditText;
    HorizontalScrollView tabLayout;
    RecyclerView searchRecyclerView,popularRecyclerView;
    List<PopularMainObject> popularList;
    public static List<SearchObject> searchList;
    ImageView cancel,search;
    TextView popular,all,club,event;
    ConstraintLayout popularEmpty,searchEmpty;

    public static int type;
    public static String searchInput;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    void searchData(String input){
        searchList=new ArrayList<>();
        searchInput=input;
        Call<List<SearchObject>> call;
        if(type==0){
            call= Drawables.api.getSearch( new SearchInput(input),0);
        }
        else if(type==1){
            call=Drawables.api.getSearchClub(new SearchInput(input),0);


        }else if(type==2){
            call=Drawables.api.getSearchEvent(new SearchInput(input),0);
        }else {
            call= Drawables.api.getSearch( new SearchInput(input),0);
        }
        call.enqueue(new Callback<List<SearchObject>>() {
            @Override
            public void onResponse(Call<List<SearchObject>> call, Response<List<SearchObject>> response) {
                try {
                    searchList = response.body();
                    searchAdapter(searchList);
                }catch (Exception e){

                }
//                if (type==0){
//
//                }else if (type==1){
//                    List<SearchObject> searchListClub = new ArrayList<>();
//                    for (SearchObject item: searchList){
//                        if (item.getType()==1){
//                            searchListClub.add(item);
//                        }
//                    }
//                    searchAdapter(searchListClub);
//                }
//                else if (type==2){
//                    List<SearchObject> searchListEvent = new ArrayList<>();
//                    for (SearchObject item: searchList){
//                        if (item.getType()==2){
//                            searchListEvent.add(item);
//                        }
//                    }
//                    searchAdapter(searchListEvent);
//                }
            }

            @Override
            public void onFailure(Call<List<SearchObject>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=inflater.inflate(R.layout.fragment_search, container, false);
         findViewByIds();
         type=0;
         addPopularData();
         onclick();
         popularAdapter();
         return view;
    }

    private void findViewByIds() {
        searchEditText=view.findViewById(R.id.searchEditText);
        tabLayout = view.findViewById(R.id.horizontalViewSearch);
        searchRecyclerView=view.findViewById(R.id.searchRecyclerView);
        popularRecyclerView=view.findViewById(R.id.popularRecycler);
        cancel=view.findViewById(R.id.searchCancel);
        search=view.findViewById(R.id.searchSearch);
        popular=view.findViewById(R.id.popularTextview);
        all = view.findViewById(R.id.searchAll);
        club = view.findViewById(R.id.searchClubs);
        event=view.findViewById(R.id.searchEvents);
        popularList = new ArrayList<>();
        popularEmpty = view.findViewById(R.id.popularEmpty);
        searchEmpty = view.findViewById(R.id.searchEmpty);
    }
    private void addPopularData(){
        Call<List<PopularMainObject>> call;
        call = Drawables.api.getPopular();
        call.enqueue(new Callback<List<PopularMainObject>>() {
            @Override
            public void onResponse(Call<List<PopularMainObject>> call, Response<List<PopularMainObject>> response) {
                popularList=new ArrayList<>();
                popularList = response.body();
                if (popularList.size()==0){
                    //Log.i("popular","Empty");
                    popularEmpty.setVisibility(View.VISIBLE);
                    popularRecyclerView.setVisibility(View.GONE);
                }else {
                    popularEmpty.setVisibility(View.GONE);
                    popularRecyclerView.setVisibility(View.VISIBLE);
                    popularAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<PopularMainObject>> call, Throwable t) {

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
    private void searchAdapter(List<SearchObject> searchList1){
        if (searchList1.size()==0){
            popularEmpty.setVisibility(View.GONE);
            searchEmpty.setVisibility(View.VISIBLE);
            searchRecyclerView.setVisibility(View.GONE);
        }else {
            searchEmpty.setVisibility(View.GONE);
            searchRecyclerView.setVisibility(View.VISIBLE);
        }
        SearchAdapter adapter = new SearchAdapter(view.getContext(),searchList1);
        LinearLayoutManager manager= new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        searchRecyclerView.setAdapter(adapter);
        searchRecyclerView.setLayoutManager(manager);
    }
    private void onclick(){
        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.INVISIBLE);
                searchEditText.setPadding(40,40,40,40);
                popular.setVisibility(View.INVISIBLE);
                popularRecyclerView.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                searchRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        searchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showSoftKeyboard(searchEditText);
                search.setVisibility(View.INVISIBLE);
                searchEditText.setPadding(40,40,40,40);
                popular.setVisibility(View.INVISIBLE);
                popularRecyclerView.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                popularEmpty.setVisibility(View.GONE);
                searchRecyclerView.setVisibility(View.VISIBLE);
                return false;
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.INVISIBLE);
                searchEditText.setPadding(40,40,40,40);
                popular.setVisibility(View.INVISIBLE);
                popularRecyclerView.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                searchRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(searchEditText);
                search.setVisibility(View.VISIBLE);
                searchEditText.setPadding(120,40,40,40);
                popular.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.INVISIBLE);

                cancel.setVisibility(View.INVISIBLE);
                searchRecyclerView.setVisibility(View.INVISIBLE);
                searchList=new ArrayList<>();

                searchAdapter(searchList);
                searchEditText.setText("");
                searchEmpty.setVisibility(View.GONE);
                if (popularList.size()==0){
                    //Log.i("popular","Empty");
                    popularEmpty.setVisibility(View.VISIBLE);
                    popularRecyclerView.setVisibility(View.GONE);
                }else {
                    popularEmpty.setVisibility(View.GONE);
                    popularRecyclerView.setVisibility(View.VISIBLE);
                    popularAdapter();
                }
                hideSoftKeyboard(searchEditText);
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
                        //Log.i("Substring",searchString);
                        if (searchEditText.getText().toString().equals("")){

                        }else {
                            if (searchEditText.getText().toString().equals(searchString)){
                                searchData(searchEditText.getText().toString());
                            }else {

                            }
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
                    searchData(searchEditText.getText().toString());
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
//                    List<SearchObject> searchListClub = new ArrayList<>();
//                    try {
//                        for (SearchObject item: searchList){
//                            if (item.getType()==1){
//                                searchListClub.add(item);
//                            }
//                        }
//                        searchAdapter(searchListClub);
//                    }catch (Exception e){
//
//                    }
                    type=1;
                    searchData(searchEditText.getText().toString());
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
//                    try {
//                        List<SearchObject> searchListEvent = new ArrayList<>();
//                        for (SearchObject item: searchList){
//                            if (item.getType()==2){
//                                searchListEvent.add(item);
//                            }
//                        }
//                        searchAdapter(searchListEvent);
//                    }
//                    catch (Exception e){
//
//                    }
                    type=2;
                    searchData(searchEditText.getText().toString());
                }
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