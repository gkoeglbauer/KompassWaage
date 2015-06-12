package com.example.gkoeglbauer.kompasswaage;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Activity_position_select extends ListActivity {

    SQLiteDatabase db;
    DBHelper dbhelper = new DBHelper(this);
    ArrayList <String> positionList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_position_select);
        db = dbhelper.getReadableDatabase();

        dislpayItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_position_select, menu);
        return true;
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

    private void dislpayItems()
    {

        Cursor rows = db.query(PositionsTbl.TABLE_NAME,
                new String[]{PositionsTbl.Name},
                null,
                null,
                null,
                null,
                PositionsTbl.Name,
                null);

        while(rows.moveToNext())
        {

              positionList.add(rows.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , positionList);
        setListAdapter(adapter);
    }
}
