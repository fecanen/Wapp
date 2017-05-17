package com.example.wapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RightAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_answer);
        Intent intent = getIntent();
        int score = intent.getIntExtra("nbrpoint",0);
        TextView Score = (TextView) findViewById(R.id.textView6);
        Score.setText(Integer.toString(score) + " poäng");

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                       finish();
                    }
                },
                4000);
    }

}
