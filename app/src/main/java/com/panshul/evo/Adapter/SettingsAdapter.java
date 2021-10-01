package com.panshul.evo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.panshul.evo.Activity.Notifications;
import com.panshul.evo.Object.SettingsObject;
import com.panshul.evo.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {
    Context context;
    List<SettingsObject> list;

    public SettingsAdapter(Context context, List<SettingsObject> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.settings_item, parent, false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SettingsAdapter.SettingsViewHolder holder, int position) {
        holder.img1.setImageResource(list.get(position).getImg());
        //Log.i("int", String.valueOf(list.get(position).getImg()));
        holder.settingItem.setText(list.get(position).getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (list.get(position).getText().equals("Appearance")){
//                    //context.startActivity(new Intent(context, Appearance.class));
//                }
                if (list.get(position).getText().equals("Notifications")){
                    Intent i = new Intent(context, Notifications.class);
                    context.startActivity(i);
                }

                if (list.get(position).getText().equals("Contact Us")){
//                    Intent i = new Intent(context, Notifications.class);
//                    context.startActivity(i);
                }

                if (list.get(position).getText().equals("Share with Peers")){
//                    Intent i = new Intent(context, Notifications.class);
//                    context.startActivity(i);
                    //throw new RuntimeException();
                }


                if (list.get(position).getText().equals("Our Instagram")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://www.instagram.com/evo.app.in/"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){

                    }
                }

                if (list.get(position).getText().equals("Our Twitter")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://twitter.com/EvoAppIn"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){

                    }
                }

                if (list.get(position).getText().equals("Our Facebook")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://www.facebook.com/Evo-App-261498562420388"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){

                    }
                }

                if (list.get(position).getText().equals("Our Linkedin")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://www.linkedin.com/in/evoappin/"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){

                    }
                }

                if (list.get(position).getText().equals("About Us")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(""));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){

                    }
                }

                if (list.get(position).getText().equals("Privacy Policy")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(""));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){

                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SettingsViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView settingItem;

        public SettingsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img_settings_card);
            settingItem=itemView.findViewById(R.id.cardview_settings_text);
            
        }
    }
}
