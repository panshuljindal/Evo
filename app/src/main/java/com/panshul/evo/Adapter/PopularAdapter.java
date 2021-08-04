package com.panshul.evo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panshul.evo.Object.Popular.PopularMainObject;
import com.panshul.evo.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {
    Context context;
    RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    List<PopularMainObject> list;

    public PopularAdapter(Context context, List<PopularMainObject> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.popular_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PopularMainObject object = list.get(position);
        holder.name.setText(object.get_id()+" Events");
        PopularPopularAdapter adapter = new PopularPopularAdapter(holder.recyclerView.getContext(),list.get(position).getEvents());
        LinearLayoutManager manager = new LinearLayoutManager(holder.recyclerView.getContext(),RecyclerView.HORIZONTAL,false);
        manager.setInitialPrefetchItemCount(list.get(position).getEvents().size());
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RecyclerView recyclerView;
        public MyViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.popularEventTypeTextView);
            recyclerView = itemView.findViewById(R.id.itemRecyclerView);
        }
    }
}
