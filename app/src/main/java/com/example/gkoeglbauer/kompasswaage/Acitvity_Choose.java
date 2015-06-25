package com.example.gkoeglbauer.kompasswaage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Acitvity_Choose extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitvity__choose);
    }

    public void onClickChoose(View view) {
        Intent intent = new Intent(this, Activity_position_select.class);
        startActivity(intent);
    }

    public void onClickNew(View view) {
        Intent intent = new Intent(this, Activity_New_Position.class);
        startActivity(intent);
    }
}
