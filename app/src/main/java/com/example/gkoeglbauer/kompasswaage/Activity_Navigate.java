package com.example.gkoeglbauer.kompasswaage;

import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gkoeglbauer on 18.06.2015.
 */
public class Activity_Navigate extends ActionBarActivity implements SensorEventListener {
    private ImageView image;
    private ImageView image2;
    private float currentDegree;
    private SensorManager mSensorManager;
    TextView degrees;
    Location currentLocation;
    Location destLocation;
    private static LocationManager locationManager = null;
    GeomagneticField geoField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        image = (ImageView) findViewById(R.id.imageViewCompass);
        image2 = (ImageView) findViewById(R.id.imageViewZeiger);
        degrees = (TextView) findViewById(R.id.showDegrees);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        degrees.setText("Degrees: " + Float.toString(degree));
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(210);
        ra.setFillAfter(true);
        image.startAnimation(ra);


        if (destLocation != null) {
            RotateAnimation rotateAnimation = new RotateAnimation(
                    getDegree(),
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(210);
            rotateAnimation.setFillAfter(true);
            image.startAnimation(rotateAnimation);
        }

        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    public void setDestLocation() {
        this.destLocation = null;
    }

    public float getDegree() {
        geoField = new GeomagneticField(
                Double.valueOf(currentLocation.getLatitude()).floatValue(),
                Double.valueOf(currentLocation.getLongitude()).floatValue(),
                Double.valueOf(currentLocation.getAltitude()).floatValue(),
                System.currentTimeMillis());
        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        float degrees = currentLocation.bearingTo(destLocation);
        float heading = currentDegree;
        heading += geoField.getDeclination();
        heading = degrees - (degrees + heading);
        return heading;
    }
}
