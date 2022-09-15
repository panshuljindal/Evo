package edu.vit.gravitas.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vit.gravitas.Adapter.SettingsAdapter;
import edu.vit.gravitas.Object.SettingsObject;
import edu.vit.gravitas.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    RecyclerView recy2,recy3;
    List<SettingsObject> lst1,lst2,lst3;
    View view;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_settings, container, false);
        lst1 = new ArrayList<>();
        lst2 = new ArrayList<>();
        lst3 = new ArrayList<>();
        context = view.getContext();
        recy2 = view.findViewById(R.id.rcy2);
        recy3=view.findViewById(R.id.rcy3);

       // recy1.setLayoutManager(new LinearLayoutManager(context));
        recy2.setLayoutManager(new LinearLayoutManager(context));
        recy3.setLayoutManager(new LinearLayoutManager(context));

        //lst1.add(new SettingsObject(R.drawable.ic_notifications, "Notifications"));
        //recy1.setAdapter(new SettingsAdapter(context,lst1));

        lst2.add(new SettingsObject(R.drawable.ic_contact_us,"Contact Us"));
        lst2.add(new SettingsObject(R.drawable.ic_share,"Share with Peers"));
        lst2.add(new SettingsObject(R.drawable.ic_ig,"Our Instagram"));
//        lst2.add(new SettingsObject(R.drawable.ic_twit,"Our Twitter"));
        lst2.add(new SettingsObject(R.drawable.ic_fb,"Our Facebook"));
//        lst2.add(new SettingsObject(R.drawable.ic_linkedin,"Our LinkedIn"));

        recy2.setAdapter(new SettingsAdapter(context,lst2));

        lst3.add(new SettingsObject(R.drawable.ic_privacy,"Privacy Policy"));
//        lst3.add(new SettingsObject(R.drawable.ic_tnc,"Terms And Conditions"));
//        lst3.add(new SettingsObject(R.drawable.ic_faq,"FAQ"));
        lst3.add(new SettingsObject(R.drawable.ic_aboutus,"About Us"));

        recy3.setAdapter(new SettingsAdapter(context,lst3));
        return view;
    }
}