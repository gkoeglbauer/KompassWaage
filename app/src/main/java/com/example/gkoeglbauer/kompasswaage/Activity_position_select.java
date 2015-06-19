package com.example.gkoeglbauer.kompasswaage;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Activity_position_select extends ListActivity{

    SQLiteDatabase db;
    public static ArrayList <String> positionList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView lv1 = (ListView) findViewById(R.id.textView);
        registerForContextMenu(lv1);
        lv1.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        DBHelper helper = new DBHelper(this);
        db = helper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_position_select);
        dislpayItems();

    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
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

        String s;

        Cursor rows= db.query(PositionsTbl.TABLE_NAME,
                new String[]{PositionsTbl.Name},
                null,
                null,
                null,
                null,
                null,
                null);

        while(rows.moveToNext())
        {

              positionList.add(rows.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, positionList);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String POSid = positionList.get(position);
        Intent i = new Intent(Activity_position_select.this,Activity_Navigate.class);
        i.putExtra("Positionid",POSid);
        startActivity(i);


    }
}
