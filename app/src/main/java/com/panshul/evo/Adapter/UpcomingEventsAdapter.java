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

import com.bumptech.glide.Glide;
import com.panshul.evo.Object.Club.ClubEventObject;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.Object.Search.SearchObject;
import com.panshul.evo.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.panshul.evo.Adapter.SearchAdapter.getDate;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.MyViewHolder> {
    Context context;
    List<ClubEventObject> list;

    public UpcomingEventsAdapter(Context context, List<ClubEventObject> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.club.setVisibility(View.GONE);
        holder.event.setVisibility(View.VISIBLE);
        ClubEventObject object = list.get(position);
        Glide.with(context).load(object.getPoster()).into(holder.eventImage);
        holder.eventName.setText(object.getName());
        holder.eventClubName.setText(object.getClubName());
        holder.eventDate.setText(getDate(object.getTimestamp()));
        if (object.getEventCost()==0){
            holder.eventPrice.setText("Free");
        }
        else {
            holder.eventPrice.setText(String.valueOf(object.getEventCost())+" Rs");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
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
