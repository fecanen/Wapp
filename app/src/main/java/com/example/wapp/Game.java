package com.example.wapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private int score;
    TextView Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int dest = intent.getIntExtra(SelectDest.EXTRA_MESSAGE,0);

        score = 10;
        Score = (TextView) findViewById(R.id.Score);
        Score.setText(String.valueOf(score));

    }

    public void nextLead(View view){
        if(score > 2) {
            score = score - 2;
            Score.setText(String.valueOf(score));

        }else{

        }
    }
}

