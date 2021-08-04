package com.panshul.evo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.panshul.evo.R;

import org.jetbrains.annotations.NotNull;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context context;

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout event,club;
        TextView eventName,eventClubName,eventDate,eventPrice,clubName;
        ImageView eventImage,clubImage,eventBack,clubBack;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.searchEventCl);
            club = itemView.findViewById(R.id.searchClubCl);
            eventName=itemView.findViewById(R.id.searchEventName);
            eventClubName=itemView.findViewById(R.id.searchEventClubName);
            eventDate = itemView.findViewById(R.id.searchEventDate);
            eventPrice=itemView.findViewById(R.id.searchEventPrice);
            clubName=itemView.findViewById(R.id.searchEventClubName);
            eventImage = itemView.findViewById(R.id.searchEventImageView);
            clubImage =itemView.findViewById(R.id.searchClubImageView);
            eventBack = itemView.findViewById(R.id.searchEventBack);
            clubBack = itemView.findViewById(R.id.searchClubBack);
        }
    }
}
