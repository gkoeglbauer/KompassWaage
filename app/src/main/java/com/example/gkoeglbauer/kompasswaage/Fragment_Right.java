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
    int slongitude;
    int slatitude;
    int pos;

    public static ArrayList<Class_Position> positionList = new ArrayList<Class_Position>();
    public static ArrayList <String> positions;

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

    public static void setPositionList(ArrayList<Class_Position> positionList) {
        Fragment_Right.positionList = positionList;
    }

    public static void setPositions(ArrayList<String> positions) {
        Fragment_Right.positions = positions;
    }

    public void show(int pos, String item)
    {
        pos=pos;
        Class_Position class_position= positionList.get(pos);
        sname = class_position.getName();
        slongitude = class_position.getLgrad();
        slatitude= class_position.getBgrad();

        name.setText(sname);
        longitude.setText(longitude.toString());
        latitude.setText(latitude.toString());
    }

    public void onButtonUsePositionClicked()
    {
        Intent intent = new Intent(getActivity(), Activity_Navigate.class);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }
}
