package com.example.gkoeglbauer.kompasswaage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonKompassClicked(View view) {
        Intent intent = new Intent(this, Activity_Compass.class);
        startActivity(intent);
    }

    public void onButtonNavigateClick(View view) {
        Intent intent = new Intent(this, Acitvity_Choose.class);
        startActivity(intent);
    }
}
