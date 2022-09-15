package edu.vit.gravitas.Fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import edu.vit.gravitas.Adapter.SaveAdapter;
import edu.vit.gravitas.Object.Event.EventObject;
import edu.vit.gravitas.Object.Interested.InterestedPost;
import edu.vit.gravitas.R;
import edu.vit.gravitas.Services.Drawables;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class SavedFragment extends Fragment {

    View view;
    EditText editText;
    RecyclerView recyclerView;
    ImageView search;
    TextView text;
    List<String> savedId;
    List<EventObject> list, searchList;
    ConstraintLayout empty;
    LottieAnimationView lottie;
    boolean isDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saved, container, false);
        editText = view.findViewById(R.id.interestedEditText);
        recyclerView = view.findViewById(R.id.interestedRecycler);
        search = view.findViewById(R.id.interestedSearch);
        empty = view.findViewById(R.id.interestedEmpty);
        text = view.findViewById(R.id.interestedTextView);
        lottie = view.findViewById(R.id.savedAnimationView);
        int time = Drawables.time;
        isDone = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isDone) {

                } else {
                    lottie.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(GONE);
                }
            }
        }, time);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchList = new ArrayList<>();
                for (EventObject item : list) {
                    if (item.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                        searchList.add(item);
                    }
                }
                SaveAdapter adapter = new SaveAdapter(searchList, view.getContext());
                LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        });
        return view;
    }

    private void addData() {
        savedId = new ArrayList<>();
        savedId = Drawables.getSavedEvent(view.getContext());
        if (savedId.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            search.setVisibility(GONE);
            recyclerView.setVisibility(GONE);
            editText.setVisibility(GONE);
            isDone = true;
            lottie.setVisibility(GONE);
            lottie.pauseAnimation();
        } else {

            Call<List<EventObject>> call = Drawables.api.getSaved(new InterestedPost(savedId));
            call.enqueue(new Callback<List<EventObject>>() {
                @Override
                public void onResponse(Call<List<EventObject>> call, Response<List<EventObject>> response) {
                    try {
                        list = response.body();
                        if (list.size() == 0) {
                            empty.setVisibility(View.VISIBLE);
                            search.setVisibility(GONE);
                            recyclerView.setVisibility(GONE);
                            editText.setVisibility(GONE);
                            isDone = true;
                            lottie.setVisibility(GONE);
                            lottie.pauseAnimation();

                        } else {
                            adapter();
                            empty.setVisibility(GONE);
                            search.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            editText.setVisibility(View.VISIBLE);
                            isDone = true;
                            lottie.setVisibility(GONE);
                            lottie.pauseAnimation();
                        }
                    } catch (Exception e) {
                        empty.setVisibility(View.VISIBLE);
                        search.setVisibility(GONE);
                        recyclerView.setVisibility(GONE);
                        editText.setVisibility(GONE);
                        isDone = true;
                        lottie.setVisibility(GONE);
                        lottie.pauseAnimation();
                    }
                }

                @Override
                public void onFailure(Call<List<EventObject>> call, Throwable t) {
                    empty.setVisibility(View.VISIBLE);
                    search.setVisibility(GONE);
                    recyclerView.setVisibility(GONE);
                    editText.setVisibility(GONE);
                    isDone = true;
                    lottie.setVisibility(GONE);
                    lottie.pauseAnimation();
                }
            });
        }

    }

    private void adapter() {
        SaveAdapter adapter = new SaveAdapter(list, view.getContext());
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