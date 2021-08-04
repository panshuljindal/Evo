package com.panshul.evo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.panshul.evo.R;

public class SavedFragment extends Fragment {

    View view;
    EditText editText;
    RecyclerView recyclerView;
    ImageView search;
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

        return view;
    }
}