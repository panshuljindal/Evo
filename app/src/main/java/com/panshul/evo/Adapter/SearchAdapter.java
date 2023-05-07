package com.panshul.evo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.panshul.evo.Activity.ClubActivity;
import com.panshul.evo.Activity.EventActivity;
import com.panshul.evo.Fragments.SearchFragment;
import com.panshul.evo.Object.Search.SearchInput;
import com.panshul.evo.Object.Search.SearchObject;
import com.panshul.evo.R;
import com.panshul.evo.Services.Drawables;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context context;
    List<SearchObject> list;
    ArrayList<Integer> isDone=new ArrayList<>();
    public SearchAdapter(Context context, List<SearchObject> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        SearchObject object = list.get(position);
        if(position==0){
            isDone.add(0);
        }
        //Log.i("isDone",String.valueOf(isDone));
        if (object.getType()==1){
            //Club
            Glide.with(context).load(object.getClubLogo()).into(holder.clubImage);
            holder.club.setVisibility(View.VISIBLE);
            holder.event.setVisibility(View.GONE);
            holder.clubName.setText(object.getClubName());
        }
        else {
            //Event
            Glide.with(context).load(object.getPoster()).into(holder.eventImage);
            holder.club.setVisibility(View.GONE);
            holder.event.setVisibility(View.VISIBLE);

            holder.eventName.setText(object.getEventName());
            holder.eventClubName.setText(object.getClubName());
            holder.eventDate.setText(getDate(object.getTimestamp()));
            if (object.getPrice()==0){
                holder.eventPrice.setText("Free");
            }
            else {
                holder.eventPrice.setText(String.valueOf(object.getPrice())+" Rs");
            }

        }
            if(holder.getAdapterPosition()==list.size()-2){
                if(!isDone.contains(list.size()/10)){
                    searchData(SearchFragment.searchInput);
                }
            }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getType()==1){
                    Intent i = new Intent(context, ClubActivity.class);
                    i.putExtra("clubId",list.get(position).getClubId());
                    context.startActivity(i);
                }
                else {
                    Intent i = new Intent(context, EventActivity.class);
                    i.putExtra("eventId",list.get(position).getEventId());
                    context.startActivity(i);
                }
            }
        });
    }

    void searchData(String input){
        List<SearchObject> list1 = SearchFragment.searchList;
        Call<List<SearchObject>> call;
        int type = SearchFragment.type;
        if(type==0){
            call= Drawables.api.getSearch( new SearchInput(input),list.size()/10);
        }
        else if(type==1){

            call=Drawables.api.getSearchEvent(new SearchInput(input),list.size()/10);

        }else if(type==2){
            call=Drawables.api.getSearchClub(new SearchInput(input),list.size()/10);
        }else {
            call= Drawables.api.getSearch( new SearchInput(input),list.size()/10);
        }
        isDone.add(list1.size()/10);
        call.enqueue(new Callback<List<SearchObject>>() {
            @Override
            public void onResponse(Call<List<SearchObject>> call, Response<List<SearchObject>> response) {
                try {
                    List<SearchObject> searchList=new ArrayList<>();
                    searchList = response.body();
                    list1.addAll(searchList);
                    SearchFragment.searchList=list1;
                    list=list1;
                    notifyDataSetChanged();
                }catch (Exception e){

                }
//                if (type==0){
//
//                }else if (type==1){
//                    List<SearchObject> searchListClub = new ArrayList<>();
//                    for (SearchObject item: list1){
//                        if (item.getType()==1){
//                            searchListClub.add(item);
//                        }
//                    }
//                    list = searchListClub;
//                    notifyDataSetChanged();
//                }
//                else if (type==2){
//                    List<SearchObject> searchListEvent = new ArrayList<>();
//                    for (SearchObject item: list1){
//                        if (item.getType()==2){
//                            searchListEvent.add(item);
//                        }
//                    }
//                    list=searchListEvent;
//                    notifyDataSetChanged();
//                }
            }

            @Override
            public void onFailure(Call<List<SearchObject>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("MMM dd yyyy, hh:mma", cal).toString();
        return date;
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
            clubName=itemView.findViewById(R.id.searchClubName);
            eventImage = itemView.findViewById(R.id.searchEventImageView);
            clubImage =itemView.findViewById(R.id.searchClubImageView);
            eventBack = itemView.findViewById(R.id.searchEventBack);
            clubBack = itemView.findViewById(R.id.searchClubBack);
        }
    }
}
