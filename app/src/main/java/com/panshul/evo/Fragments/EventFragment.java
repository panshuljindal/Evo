package com.panshul.evo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.panshul.evo.Activity.EventActivity;
import com.panshul.evo.R;

public class EventFragment extends Fragment {

    View view;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_event, container, false);
        context=view.getContext();
//        ImageView image  = view.findViewById(R.id.imageView);
//        image.setClipToOutline(true);
        startActivity(new Intent(context, EventActivity.class));

        return view;
    }
}