package com.example.gkoeglbauer.kompasswaage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Activity_New_Position extends ActionBarActivity {

    public static String name;
    public static double laengengrad;
    public static double breitengrad;
    Boolean dbCreated = false;

    private static LocationManager locMan = null;

    public static DBHelper dbhelper;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__new__position);

        if(dbCreated==false) {
            dbhelper = new DBHelper(this);
            db = dbhelper.getReadableDatabase();
            dbCreated = true;
        }

        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__new__position, menu);
        return true;
    }

    protected void onResume()
    {
        super.onResume();
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, (LocationListener) this);
    }

    protected void onPause()
    {
        super.onPause();
        locMan.removeUpdates((LocationListener) this);
    }



    public static void insertIntoDb()
    {
        ContentValues vals = new ContentValues();
        vals.put("name", name);
        vals.put("laenge", laengengrad);
        vals.put("breite", breitengrad);
        long insertedID = db.insert("Position", null, vals);
    }

    public void onStandortClicked(Location loc)
    {
        laengengrad = loc.getLongitude();
        breitengrad = loc.getLatitude();
    }

    public void onWerteClicked()
    {
        EditText laenge = (EditText) findViewById(R.id.TXTFlängengrad);
        laengengrad = Double.parseDouble(laenge.getText().toString());

        EditText breite = (EditText) findViewById(R.id.TXTFbreitengrad);
        breitengrad = Double.parseDouble(breite.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
