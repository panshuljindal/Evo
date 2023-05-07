package com.panshul.evo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.panshul.evo.Activity.EventActivity;
import com.panshul.evo.Object.Popular.PopularEventObject;
import com.panshul.evo.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PopularPopularAdapter extends RecyclerView.Adapter<PopularPopularAdapter.MyViewHolder> {

    Context context;
    List<PopularEventObject> list;

    public PopularPopularAdapter(Context context, List<PopularEventObject> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.popularitem_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        PopularEventObject object = list.get(position);
        holder.eventName.setText(object.getName());
        holder.eventName.bringToFront();
        Glide.with(context).load(object.getPoster()).into(holder.eventImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventActivity.class);
                i.putExtra("eventId",list.get(position).getEventId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;
        ImageView eventImage;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.popularPopularImageView);
            eventName = itemView.findViewById(R.id.popularPopularTextView);
        }
    }
}
