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
import android.widget.Toast;

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
                if (list.get(position).getText().equals("Notifications")){
                    Intent i = new Intent(context, Notifications.class);
                    context.startActivity(i);
                }

                if (list.get(position).getText().equals("Contact Us")){
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"events.sw@vit.ac.in"});
                    intent.putExtra(Intent.EXTRA_SUBJECT,"");
                    intent.putExtra(Intent.EXTRA_TEXT,"");
                    intent.setType("message/rfc822");
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Select Gmail", Toast.LENGTH_SHORT).show();
                    }
                }

                if (list.get(position).getText().equals("Share with Peers")){

                    String app_url = " https://play.google.com/store/apps/details?id=com.panshul.evo";
                    Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String text = "Hey. I came across this brilliant app that could help you efficiently search through the clutter of events and find the best one that suits your need. Try it out. It is so easy to use.\n"
                            +"App Link: "+app_url;
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,"");
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,text);
                    try {
                        v.getContext().startActivity(Intent.createChooser(shareIntent,"Share via"));
                    }catch (Exception e){
                        Toast.makeText(context, "Select Whatsapp", Toast.LENGTH_SHORT).show();
                    }
                }


                if (list.get(position).getText().equals("Our Instagram")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://www.instagram.com/swcc_online/?utm_medium=copy_link"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (list.get(position).getText().equals("Our Twitter")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://twitter.com/EvoAppIn"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                if (list.get(position).getText().equals("Our Facebook")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://www.facebook.com/VITstudentswelfare"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                if (list.get(position).getText().equals("Our LinkedIn")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://www.linkedin.com/company/evoappin/"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                if (list.get(position).getText().equals("About Us")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://vit.ac.in/campuslife/studentswelfare"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }

                if (list.get(position).getText().equals("Privacy Policy")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://fakeyudi.notion.site/fakeyudi/VIT-Nav-87ef1ccf0fcc4dde9d8ed365c616a9f9"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }if (list.get(position).getText().equals("Terms And Conditions")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://privacy.evoevents.club/tnc"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();

                    }
                }
                if (list.get(position).getText().equals("FAQ")){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://privacy.evoevents.club/faq"));
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Please try again!", Toast.LENGTH_SHORT).show();
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
