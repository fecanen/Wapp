package com.example.wapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WrongAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        finish();
                    }
                },
                4000);
    }
}
