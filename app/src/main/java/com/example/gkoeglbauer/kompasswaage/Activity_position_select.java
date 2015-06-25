package com.example.gkoeglbauer.kompasswaage;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Activity_position_select extends Activity implements Fragment_Left.OnSelectionChangedListener{

    Fragment_Right rightFragment;
    SQLiteDatabase db;
    SQLiteDatabase db2;
    boolean showRight = false;
    public ArrayList <Class_Position> positionList;
    public ArrayList <String> positions;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper helper = new DBHelper(this);
        db=helper.getReadableDatabase();
        db= helper.getWritableDatabase();
        setContentView(R.layout.activity_activity_position_select);
        positionList  = new ArrayList<>();
        dislpayItems();
        Fragment_Right.setPositionList(positionList);
        Fragment_Right.setPositions(positions);
        Fragment_Left.setPositionList(positionList);
        Fragment_Left.setPositions(positions);
        rightFragment = (Fragment_Right) getFragmentManager().findFragmentById(R.id.fragRight);
        showRight = rightFragment != null && rightFragment.isInLayout();
        listView = (ListView)findViewById(R.id.listPositions);
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
                new String[]{PositionsTbl.Name,PositionsTbl.Breitengrad,PositionsTbl.Längengrad},
                null,
                null,
                null,
                null,
                PositionsTbl.Name,
                null);

        while(rows.moveToNext())
        {
            String name = rows.getString(rows.getColumnIndex(PositionsTbl.Name));
            double bgrad = rows.getDouble(rows.getColumnIndex(PositionsTbl.Breitengrad));
            double lgrad = rows.getDouble(rows.getColumnIndex(PositionsTbl.Längengrad));
            positionList.add(new Class_Position(name,bgrad,lgrad));
            positions.add(name);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, positions);
        listView.setAdapter(adapter);
    }



    @Override
    public void onSelectionCHanged(int pos, String item) {
        if(showRight)
        {
            rightFragment.show(pos, item);
        }
        else
        {
            showFragmentActivity(pos, item);
        }
    }

    private void showFragmentActivity(int pos, String item) {
        Intent intent = new Intent(this, Activity_position_select_right.class);
        intent.putExtra("POS", pos);
        intent.putExtra("ITEM", item);
        startActivity(intent);
    }
}

