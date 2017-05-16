package com.example.wapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Distance extends AppCompatActivity {
    Context context;
    double distance;
    Intent intent;
    Vibrator vibrator;
    TextView lead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        context = this;
        intent = getIntent();
        distance = intent.getDoubleExtra(Game.DISTANCE,0);
        TextView lead = (TextView) findViewById(R.id.distanceText);
        vibrator = (Vibrator) getSystemService(context.VIBRATOR_SERVICE);
        checkDistTarget();
    }

    public void checkDistTarget() {
        setContentView(R.layout.activity_distance);
        final TextView lead = (TextView) findViewById(R.id.distanceText);
        final int hundredsOfMeters = (int) Math.round(distance*1000)/100;
        if(hundredsOfMeters < 0) {
            lead.setText("Du är under 100 m från målet");
        } else {
            vibrateNbrOfTimes(hundredsOfMeters);
            waitForVib(hundredsOfMeters, lead);
        }
    }

    /**Updates TextView when the vibrator is finished*/
    private void waitForVib(final int delay,final TextView view){
        int time = delay * 900; // in milliseconds
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setText("Du är över " + "\n" + delay * 100 + " m från målet");
            }
        },time);
        view.setText("Varje vibration motsvarar 100 meter");
    }

    //http://stackoverflow.com/questions/5880250/how-to-vibrate-device-n-number-of-times-through-programming-in-android
    private void vibrateNbrOfTimes(final int nbrTimes){
        final long[] pattern = {500, 300, 100}; //delay befor starting pattern of 300, vibrate 200, delay 0
        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < nbrTimes; i++){
                    vibrator.vibrate(pattern, -1); //run vibrator pattern once (-1)
                    try {
                        Thread.sleep(900); //time the complete pattern needs
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
