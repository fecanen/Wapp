package com.example.wapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RightAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_answer);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                       finish();
                    }
                },
                4000);
    }

}
