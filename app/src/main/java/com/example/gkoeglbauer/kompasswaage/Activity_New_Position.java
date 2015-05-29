package com.example.gkoeglbauer.kompasswaage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class Activity_New_Position extends ActionBarActivity {

    public static String name;
    public static double laengengrad;
    public static double breitengrad;
    Boolean dbCreated = false;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__new__position, menu);
        return true;
    }

    public static void insertIntoDb()
    {
        ContentValues vals = new ContentValues();
        vals.put("name", name);
        vals.put("laenge", laengengrad);
        vals.put("breite", breitengrad);
        long insertedID = db.insert("Position", null, vals);
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
