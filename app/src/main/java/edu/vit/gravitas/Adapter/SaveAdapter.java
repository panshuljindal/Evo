package edu.vit.gravitas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import edu.vit.gravitas.Activity.EventActivity;
import edu.vit.gravitas.Activity.ClubActivity;
import edu.vit.gravitas.Object.Event.EventObject;
import edu.vit.gravitas.R;

import edu.vit.gravitas.Services.Drawables;

import java.util.List;

import xyz.hanks.library.bang.SmallBangView;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.MyViewHolder> {

    List<EventObject> list;
    Context context;
    public SaveAdapter(List<EventObject> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
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
        holder.clubName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(v.getContext(), ClubActivity.class);
                i.putExtra("clubId",list.get(position).getClubId().get_id());
                context.startActivity(i);
            }
        });

        if (list.get(position).getClubId().isPartner()){
            holder.verify.setVisibility(View.VISIBLE);
        }
        else {
            holder.verify.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventActivity.class);
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
                    Drawables.unlikeEvent(list.get(position).get_id(),context);
                    like.remove(list.get(position).get_id());
                    Drawables.saveLiked(like,context);
                    holder.like.setText(String.valueOf(list.get(position).getLikes()-1)+" likes");
                    holder.imageView.setSelected(false);
                    holder.imageView.likeAnimation();
                }else {
                    //Log.i("Saved","No");
                    Drawables.likeEvent(list.get(position).get_id(),context);
                    like.add(list.get(position).get_id());
                    Drawables.saveLiked(like,context);
                    holder.like.setText(String.valueOf(list.get(position).getLikes()+1)+" likes");
                    holder.imageView.setSelected(true);
                    holder.imageView.likeAnimation();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage,clubLogo,eventLike;
        TextView eventName,clubName,like;
        SmallBangView imageView;
        ImageView verify;
        public MyViewHolder(View itemView) {
            super(itemView);
            verify=itemView.findViewById(R.id.eventItemVerified);
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
