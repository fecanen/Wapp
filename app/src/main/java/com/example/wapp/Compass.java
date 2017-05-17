package com.example.wapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class Compass extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;
    private float[] mLastAccelerometer = new float[3];
    private float[] mLastMagnetometer = new float[3];
    private boolean mLastAccelerometerSet = false;
    private boolean mLastMagnetometerSet = false;
    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];
    private float mCurrentDegree = 0f;
    static final float ALPHA = 0.25f; // if ALPHA = 1 OR 0, no filter applies.
    private float bearing;
    public Context context;

    int count;
    int counter;

    MediaPlayer m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        context = this;
        Intent intent = getIntent();

        double lat1 = intent.getDoubleExtra("lat1", 0);
        double long1 = intent.getDoubleExtra("long1", 0);
        double lat2 = intent.getDoubleExtra("lat2", 0);
        double long2 = intent.getDoubleExtra("long2", 0);

        Location l1 = new Location("");
        l1.setLatitude(lat1);
        l1.setLongitude(long1);

        Location l2 = new Location("");
        l2.setLatitude(lat2);
        l2.setLongitude(long2);

        bearing = l1.bearingTo(l2);
        bearing = (bearing +180);


        count = 230;
        counter = 0;

        m = MediaPlayer.create(context, R.raw.beep);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, 2000000000);
        mSensorManager.registerListener(this, mMagnetometer, 2000000000);
    }


    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerometer);
        mSensorManager.unregisterListener(this, mMagnetometer);
    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor == mAccelerometer) {
            System.arraycopy(event.values, 0, mLastAccelerometer, 0, event.values.length);
            mLastAccelerometerSet = true;
        } else if (event.sensor == mMagnetometer) {
            System.arraycopy(event.values, 0, mLastMagnetometer, 0, event.values.length);
            mLastMagnetometerSet = true;
        }

        if (mLastAccelerometerSet && mLastMagnetometerSet) {
            SensorManager.getRotationMatrix(mR, null, mLastAccelerometer, mLastMagnetometer);
            SensorManager.getOrientation(mR, mOrientation);
            float azimuthInRadians = mOrientation[0];
            float azimuthInDegress = (float) (Math.toDegrees(azimuthInRadians) + 360) % 360;

            mCurrentDegree = azimuthInDegress;

            if(mCurrentDegree > (bearing-30) && mCurrentDegree < (bearing +30)){
                    count=60;
            }else if(mCurrentDegree > (bearing-90) && mCurrentDegree < (bearing +90)){
                count=110;
            }else{
                count = 220;
            }

            if(count <= counter) {
                counter = 0;
                m.start();
            }
            counter++;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }


}


