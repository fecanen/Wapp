package com.example.wapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class destination extends AppCompatActivity {
    private int score;
    TextView Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
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

    @Override
    public void onBackPressed() {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();
    }
}
