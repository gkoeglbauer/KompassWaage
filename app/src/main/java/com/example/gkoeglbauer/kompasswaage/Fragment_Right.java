package com.example.gkoeglbauer.kompasswaage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Fragment_Right extends Fragment {

    TextView name;
    TextView longitude;
    TextView latitude;
    String sname;
    String slongitude;
    String slatitude;
    static int position;

    public static ArrayList<Class_Position> positionList = new ArrayList<Class_Position>();
    public static ArrayList<String> positions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__right, container);
        name = (TextView) view.findViewById(R.id.textViewName);
        longitude = (TextView) view.findViewById(R.id.textViewLongitude);
        latitude = (TextView) view.findViewById(R.id.textViewLatitude);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public static void setPositionList(ArrayList<Class_Position> positionList1) {
        positionList = positionList1;
    }

    public static void setPositions(ArrayList<String> positions1) {
        positions = positions1;
    }

    public void show(int pos, String item) {
        position = pos;

        Class_Position positions = (Class_Position)positionList.get(position);
        sname = positions.getName();
        slongitude = ""+ positions.getLgrad();
        slatitude = ""+ positions.getBgrad();

        name.setText(sname);
        longitude.setText(slongitude);
        latitude.setText(slatitude);
    }

    public static int getPosition() {
        return position;
    }
}
