package com.example.wapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int dest = intent.getIntExtra(SelectDest.EXTRA_MESSAGE,0);

        if(dest > 0) {
            Intent destination = new Intent(this, destination.class);
            startActivity(destination);
            dest = dest -1;
        }
    }
}

