package com.panshul.evo.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.evo.Adapter.EventAdapter;
import com.panshul.evo.Object.Event.EventClubObject;
import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.Object.Interested.InterestedPost;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedFragment extends Fragment {

    View view;
    EditText editText;
    RecyclerView recyclerView;
    ImageView search;
    List<String> savedId;
    List<EventObject> list,searchList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_saved, container, false);
        editText=view.findViewById(R.id.interestedEditText);
        recyclerView=view.findViewById(R.id.interestedRecycler);
        search = view.findViewById(R.id.interestedSearch);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchList=new ArrayList<>();
                for (EventObject item:list){
                    if (item.getName().toLowerCase().contains(s.toString().toLowerCase())){
                        searchList.add(item);
                    }
                }
                EventAdapter adapter = new EventAdapter(searchList,view.getContext());
                LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        });
        return view;
    }
    private void addData(){
        savedId = new ArrayList<>();
        savedId = Drawables.getSavedEvent(view.getContext());
        if (savedId.size()==0){

        }else {
            Call<List<EventObject>> call = Drawables.api.getSaved(new InterestedPost(savedId));
            call.enqueue(new Callback<List<EventObject>>() {
                @Override
                public void onResponse(Call<List<EventObject>> call, Response<List<EventObject>> response) {
                    list=response.body();
                    adapter();
                }

                @Override
                public void onFailure(Call<List<EventObject>> call, Throwable t) {

                }
            });
        }

    }
    private void adapter(){
        EventAdapter adapter = new EventAdapter(list,view.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onStart() {
        super.onStart();
        addData();
    }
}