package edu.vit.gravitas.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.vit.gravitas.R;

public class Notifications extends AppCompatActivity {
    ImageView back;
    private RadioGroup radioGroup;
    private RadioButton on, off;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        radioGroup = findViewById(R.id.notificationsRadioGroup);
        on = findViewById(R.id.notificationsOn);
        off=findViewById(R.id.notificationOff);
        back = findViewById(R.id.notificationsBack);

        SharedPreferences sharedPreferences = getSharedPreferences("com.panshul.evo.notifications",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String notifications = sharedPreferences.getString("Notification","On");
        //Log.i("notifications",notifications);
        on.setChecked(true);
        if(notifications.equals("Off")){
            off.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.notificationsOn){
                    editor.putString("Notification","On");
                    on.setChecked(true);
                    editor.apply();
                }
                else if(i==R.id.notificationOff){
                    editor.putString("Notification","Off");
                    off.setChecked(true);
                    editor.apply();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}