package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Calendar extends AppCompatActivity {

    Button callSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        callSettings = findViewById(R.id.settingsButton);
        callSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this,Settings.class);
                startActivity(intent);
            }
        });

    }
}