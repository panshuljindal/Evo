package com.panshul.evo.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.panshul.evo.Adapter.EventAdapter;
import com.panshul.evo.Adapter.PopularAdapter;
import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Popular.PopularMainObject;
import com.panshul.evo.Object.Search.SearchObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Api;
import com.panshul.evo.Services.Drawables;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    View view;
    Context context;
    EditText searchEditText;
    HorizontalScrollView tabLayout;
    RecyclerView searchRecyclerView,popularRecyclerView;
    List<PopularMainObject> popularList;
    ImageView cancel,search;
    TextView popular,go;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Drawables.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=inflater.inflate(R.layout.fragment_search, container, false);
         findViewByIds();
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
        go=view.findViewById(R.id.searchText);
        popularList = new ArrayList<>();
    }
    private void addPopularData(){
        Call<List<PopularMainObject>> call;
        call = api.getPopular();
        call.enqueue(new Callback<List<PopularMainObject>>() {
            @Override
            public void onResponse(Call<List<PopularMainObject>> call, Response<List<PopularMainObject>> response) {
                popularList = response.body();
                popularAdapter();
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
    private void onclick(){
        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.INVISIBLE);
                searchEditText.setPadding(40,40,40,40);
                popular.setVisibility(View.INVISIBLE);
                popularRecyclerView.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                go.setVisibility(View.VISIBLE);
                searchRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               // Log.i("focus",String.valueOf(hasFocus));
                if (hasFocus){
                    showSoftKeyboard(searchEditText);
                    search.setVisibility(View.INVISIBLE);
                    searchEditText.setPadding(40,40,40,40);
                    popular.setVisibility(View.INVISIBLE);
                    popularRecyclerView.setVisibility(View.INVISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);
                    go.setVisibility(View.VISIBLE);
                    searchRecyclerView.setVisibility(View.VISIBLE);
                }
                else {
                    search.setVisibility(View.VISIBLE);
                    searchEditText.setPadding(120,40,40,40);
                    popular.setVisibility(View.VISIBLE);
                    popularRecyclerView.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.INVISIBLE);
                    go.setVisibility(View.INVISIBLE);
                    searchRecyclerView.setVisibility(View.INVISIBLE);
                }
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
                go.setVisibility(View.VISIBLE);
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
                popularRecyclerView.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.INVISIBLE);
                go.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                searchRecyclerView.setVisibility(View.INVISIBLE);
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
                if (s.toString().length()==0){
                    go.setVisibility(View.INVISIBLE);
                    cancel.setVisibility(View.VISIBLE);
                }
                else {
                    go.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.INVISIBLE);
                }
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Call<List<SearchObject>> call=api.getSearch();
//                call.enqueue(new Callback<List<SearchObject>>() {
//                    @Override
//                    public void onResponse(Call<List<SearchObject>> call, Response<List<SearchObject>> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<SearchObject>> call, Throwable t) {
//
//                    }
//                });
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
        searchEditText.setFocusable(false);


    }
}