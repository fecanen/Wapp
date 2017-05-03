package com.example.wapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import slide.SlideButton;
import slide.SlideButtonListener;


public class Game extends AppCompatActivity {
    private int score;
    TextView Score;
    LinkedList<Destination> destinations;
    private DestinationParser parser;
    Destination destination;
    Context context;
    GPS gps;
    String currentLead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        int nbrdest = intent.getIntExtra(SelectDest.EXTRA_MESSAGE,0);
        InputStream in = this.getResources().openRawResource(R.raw.lund);
        parser = new DestinationParser();
        context = this;

        try {
            destinations = loadGame(in,nbrdest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        //destination is the destination currently being played.
        destination = destinations.poll();
        score = 10;
        Score = (TextView) findViewById(R.id.Score);
        Score.setText(String.valueOf(score));

        LocationManager lm = (LocationManager)getSystemService(context.LOCATION_SERVICE);
        gps = new GPS(context,lm);

        //sensor to detect shakes, code from http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it (following 5 lines)
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        currentLead = destination.getLead();

        ((SlideButton) findViewById(R.id.unlockButton)).setSlideButtonListener(new SlideButtonListener() {
            @Override
            public void handleSlide() {
                //lås in GPS
            }
        });

        //play the first lead directly when the game is started
        try {
            playLead(currentLead);
        }catch(IOException e){

        }
    }

    public void nextLead(View view) {
        if (score > 3) {
            score = score - 2;
            Score.setText(String.valueOf(score));
            currentLead = destination.getLead();
            try {
                playLead(currentLead);
                //double dist = gps.distFrom(destination.getLat(),destination.getLong());
                //Score.setText(String.valueOf(dist));
            }catch(IOException e){

            }
        }
    }

    //repeats the current lead
    public void repeatLead() {
        try {
            playLead(currentLead);
        }catch(IOException e){

        }
    }

    //Parses an XML file with the leads to each destination. The Leads are adressess to sounds that should be played
    public LinkedList<Destination> loadGame(InputStream in, int nbr) throws IOException, XmlPullParserException {
        return parser.parse(in,nbr);
    }

    //Adress is the adress of the sound that should be played.
    private void playLead(String adress) throws IOException{
        MediaPlayer mp;
        mp=MediaPlayer.create(context, getResources().getIdentifier(adress,"raw",getPackageName()));
        mp.start();
    }

    public void checkGPS(){

    }

    //SensorListener that listens for shakes, code from http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 3000; //timeout between shakes
    private long mLastShakeTime;

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {
                float x = se.values[0];
                float y = se.values[1];
                float z = se.values[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta; // perform low-cut filter

                if (mAccel > 20) {
                    mLastShakeTime = curTime;
                    repeatLead();
                }
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}