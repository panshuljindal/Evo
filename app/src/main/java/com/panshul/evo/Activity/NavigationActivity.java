package com.panshul.evo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.panshul.evo.Fragments.EventFragment;
import com.panshul.evo.Fragments.PopularFragment;
import com.panshul.evo.Fragments.SavedFragment;
import com.panshul.evo.Fragments.SearchFragment;
import com.panshul.evo.Fragments.SettingsFragment;
import com.panshul.evo.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class NavigationActivity extends AppCompatActivity {
    AnimatedBottomBar animatedBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_acitivity);

        animatedBottomBar = findViewById(R.id.bottom_bar);
        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NotNull AnimatedBottomBar.Tab tab1) {
                Fragment fragment = null;
                switch (tab1.getId()){
                    case R.id.tab_event:
                        fragment=new EventFragment();
                        break;
                    case R.id.tab_search:
                        fragment= new PopularFragment();
                        break;
                    case R.id.tab_saved:
                        fragment = new SavedFragment();
                        break;
                    case R.id.tab_settings:
                        fragment = new SettingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }


            @Override
            public void onTabReselected(int i, @NotNull AnimatedBottomBar.Tab tab) {

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EventFragment()).commit();

    }
}