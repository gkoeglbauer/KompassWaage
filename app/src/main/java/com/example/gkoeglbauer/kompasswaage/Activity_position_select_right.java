package com.example.gkoeglbauer.kompasswaage;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Activity_position_select_right extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_position_select_right);
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent == null) return;
        Fragment_Right rightFragment = (Fragment_Right) getFragmentManager().findFragmentById(R.id.fragRight);
        int pos = intent.getIntExtra("POS", -1);
        String item = "Unknown";
        if (intent.hasExtra("ITEM")) item = intent.getStringExtra("ITEM");
        rightFragment.show(pos, item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_position_select_right, menu);
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

    public void onButtonUsePositionClicked(View view) {
        int pos = Fragment_Right.getPosition();
        Intent intent = new Intent(this, Activity_Navigate.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }
}
