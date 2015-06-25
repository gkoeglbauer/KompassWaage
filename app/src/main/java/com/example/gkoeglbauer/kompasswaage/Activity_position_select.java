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
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Activity_position_select extends Activity implements Fragment_Left.OnSelectionChangedListener {

    Fragment_Right rightFragment;
    Fragment_Left leftFragment;
    SQLiteDatabase db;
    boolean showRight = false;
    public ArrayList<Class_Position> positionList;
    public ArrayList<String> positions = new ArrayList<String>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper helper = new DBHelper(this);
        db = helper.getReadableDatabase();

        setContentView(R.layout.activity_activity_position_select);
        positionList = new ArrayList<>();


        rightFragment = (Fragment_Right) getFragmentManager().findFragmentById(R.id.fragRight);
        leftFragment = (Fragment_Left) getFragmentManager().findFragmentById(R.id.fragLeft);
        showRight = rightFragment != null && rightFragment.isInLayout();
        listView = (ListView) findViewById(R.id.listPositions);
        dislpayItems();
    }

    private void dislpayItems() {
        String s;

        Cursor rows = db.query(PositionsTbl.TABLE_NAME,
                new String[]{PositionsTbl.Name, PositionsTbl.Breitengrad, PositionsTbl.Längengrad},
                null,
                null,
                null,
                null,
                PositionsTbl.Name,
                null);

        while (rows.moveToNext()) {
            String name = rows.getString(rows.getColumnIndex(PositionsTbl.Name));
            double bgrad = rows.getDouble(rows.getColumnIndex(PositionsTbl.Breitengrad));
            double lgrad = rows.getDouble(rows.getColumnIndex(PositionsTbl.Längengrad));
            positionList.add(new Class_Position(name, bgrad, lgrad));
            positions.add(name);
        }

        rightFragment.setPositionList(positionList);
        rightFragment.setPositions(positions);
        leftFragment.setPositionList(positionList);
        leftFragment.setPositions(positions);
    }


    public void onSelectionCHanged(int pos, String item) {
        if (showRight) {
            rightFragment.show(pos, item);
        } else {
            showFragmentActivity(pos, item);
        }
    }

    private void showFragmentActivity(int pos, String item) {
        Intent intent = new Intent(this, Activity_position_select_right.class);
        intent.putExtra("POS", pos);
        intent.putExtra("ITEM", item);
        startActivity(intent);
    }

    public void onButtonUsePositionClicked(View view) {
        int pos = Fragment_Right.getPosition();
        Intent intent = new Intent(this, Activity_Navigate.class);
        intent.putExtra("POS", pos);
        startActivity(intent);
    }
}

