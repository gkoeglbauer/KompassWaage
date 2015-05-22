package com.example.gkoeglbauer.kompasswaage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class Kompass extends Activity implements SensorEventListener {

    //private SQLiteDatabase db;
    //private boolean positionSelected=false;
    private ImageView image;
    private float currentDegree = 0f;
    private SensorManager SensorManager;
    private int pos= 0;
    TextView tvHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageViewCompass);

        tvHeading = (TextView) findViewById(R.id.tvHeading);

        SensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        SensorManager.registerListener(this, SensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        SensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);

        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        ra.setDuration(210);

        ra.setFillAfter(true);

        image.startAnimation(ra);
        currentDegree = -degree;

        /*
        if(positionSelected==true)
        {
            Cursor cursor = db.query(
                    "Positionen",
                    new String[]{"id","name","longitude","altitude"},
                    "id="+pos,
                    new String[] {"1"},
                    null,
                    null,
                    "name",
                    "1");

            //RotateAnimation rotateAnimation = new RotateAnimation();
        }
        */

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    /*
    public void setPosition(boolean positionSelected, int pos) {
        this.positionSelected = positionSelected;
        this.pos = pos;
    }
    */
}
