package com.panshul.evo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.panshul.evo.Activity.EventActivity;
import com.panshul.evo.Activity.ClubActivity;
import com.panshul.evo.Fragments.EventFragment;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import java.util.List;

import xyz.hanks.library.bang.SmallBangView;

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
        holder.like.setText(String.valueOf(object.getLikes())+" likes");
        holder.eventName.setText(object.getName());
        holder.clubName.setText(object.getClubName());
        holder.clubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(v.getContext(), ClubActivity.class);
                i.putExtra("clubId",list.get(position).getClubId().get_id());
                context.startActivity(i);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,EventActivity.class);
                i.putExtra("eventId",list.get(position).get_id());
                context.startActivity(i);
            }
        });
        List<String> likes = Drawables.getLikes(context);
        if (likes.contains(object.get_id())){
            holder.imageView.setSelected(true);
        }
        else {
            holder.imageView.setSelected(false);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> like = Drawables.getLikes(context);
                if (like.contains(list.get(position).get_id())){
                    holder.imageView.setSelected(true);
                }else {
                    //Log.i("Saved","No");
                    Drawables.likeEvent(list.get(position).get_id());
                    like.add(list.get(position).get_id());
                    Drawables.saveLiked(like,context);
                    holder.like.setText(String.valueOf(list.get(position).getLikes()+1)+" likes");
                    holder.imageView.setSelected(true);
                    holder.imageView.likeAnimation();
                }
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
        SmallBangView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            eventImage=itemView.findViewById(R.id.eventItemImage);
            clubLogo=itemView.findViewById(R.id.eventItemClubLogo);
            eventLike=itemView.findViewById(R.id.eventItemLike);
            eventName=itemView.findViewById(R.id.eventItemName);
            clubName=itemView.findViewById(R.id.eventItemClubName);
            like = itemView.findViewById(R.id.eventItemLikeTextView);
            imageView = itemView.findViewById(R.id.imageViewAnimation);
        }
    }

}
