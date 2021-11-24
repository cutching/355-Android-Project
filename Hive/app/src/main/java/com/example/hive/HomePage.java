package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // hooks
        fullName = findViewById(R.id.largeName);

        displayName();

    }

    private void displayName() {

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        fullName.setText("WELCOME, " + name.toUpperCase() + "!");
    }



}