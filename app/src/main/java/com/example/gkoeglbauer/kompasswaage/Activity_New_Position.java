package com.example.gkoeglbauer.kompasswaage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class Activity_New_Position extends ActionBarActivity implements LocationListener {

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
        registerForContextMenu(findViewById(R.id.list_item));
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
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, this);
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
        long insertedID = db.insert("Positions", null, vals);
    }

    public void onStandortClicked(View view)
    {
        Location loc = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        laengengrad = loc.getLongitude();
        breitengrad = loc.getLatitude();

        EditText namen = (EditText) findViewById(R.id.TXTFname);
        name = namen.getText().toString();

        insertIntoDb();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Toast t = Toast.makeText(this,name+" gespeichert",Toast.LENGTH_SHORT);
        t.show();
    }


    public void onWerteClicked(View view)
    {
        EditText laenge = (EditText) findViewById(R.id.TXTFlängengrad);
        laengengrad = Double.parseDouble(laenge.getText().toString());

        EditText breite = (EditText) findViewById(R.id.TXTFbreitengrad);
        breitengrad = Double.parseDouble(breite.getText().toString());

        EditText namen = (EditText) findViewById(R.id.TXTFname);
        name = namen.getText().toString();

        insertIntoDb();

        Toast t = Toast.makeText(this,name+" gespeichert",Toast.LENGTH_SHORT);
        t.show();
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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
