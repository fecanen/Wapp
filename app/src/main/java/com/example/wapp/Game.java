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

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.Gametext);
        textView.setText(String.valueOf(dest));
    }
}
