package com.panshul.evo.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.panshul.evo.Object.EventObject;
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
        return null;
    }

    @Override
    public void onBindViewHolder(EventAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage,clubLogo,eventLike;
        TextView eventName,clubName;
        public MyViewHolder(View itemView) {
            super(itemView);
            eventImage=itemView.findViewById(R.id.eventItemImage);
            clubLogo=itemView.findViewById(R.id.eventItemClubLogo);
            eventLike=itemView.findViewById(R.id.eventItemLike);
            eventName=itemView.findViewById(R.id.eventItemName);
            clubName=itemView.findViewById(R.id.eventItemClubName);
        }
    }
}
