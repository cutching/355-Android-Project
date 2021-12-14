package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    Button callGroceries, callReminders, callNotes, callCalendar;

    TextView fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // hooks
        fullName = findViewById(R.id.largeName);
        displayName();

        callGroceries = findViewById(R.id.groceriesButton);
        callGroceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Groceries.class);
                startActivity(intent);
            }
        });

        callReminders = findViewById(R.id.remindersButton);
        callReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Reminders.class);
                startActivity(intent);
            }
        });

        callNotes = findViewById(R.id.notesButton);
        callNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Notes.class);
                startActivity(intent);
            }
        });

        callCalendar = findViewById(R.id.calendarButton);
        callCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Calendar.class);
                startActivity(intent);
            }
        });

    }

    private void displayName() {

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        fullName.setText("WELCOME, " + name.toUpperCase() + "!");
    }



}