package com.panshul.evo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.panshul.evo.Activity.EventActivity;
import com.panshul.evo.Activity.MusicActivity;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    List<EventObject> list;
    Context context;

    public EventAdapter(List<EventObject> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.MyViewHolder holder, int position) {
        EventObject object = list.get(position);
        Glide.with(context).load(object.getPoster()).into(holder.eventImage);
        Glide.with(context).load(object.getClubId().getLogo()).into(holder.clubLogo);
        holder.eventImage.setClipToOutline(true);
        holder.like.setText(String.valueOf(object.getLikes()));
        holder.eventName.setText(object.getName());
        holder.clubName.setText(object.getClubName());
        holder.clubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(v.getContext(), MusicActivity.class) );
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,EventActivity.class);
                i.putExtra("eventId",list.get(position).get_id());
                i.putExtra("eventPoster",list.get(position).getPoster());
                context.startActivity(i);
            }
        });

        int i = holder.getAdapterPosition();
//        if (i==list.size()-2){
//            notifyDataSetChanged();
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage,clubLogo,eventLike;
        TextView eventName,clubName,like;
        public MyViewHolder(View itemView) {
            super(itemView);
            eventImage=itemView.findViewById(R.id.eventItemImage);
            clubLogo=itemView.findViewById(R.id.eventItemClubLogo);
            eventLike=itemView.findViewById(R.id.eventItemLike);
            eventName=itemView.findViewById(R.id.eventItemName);
            clubName=itemView.findViewById(R.id.eventItemClubName);
            like = itemView.findViewById(R.id.eventItemLikeTextView);
        }
    }
}
