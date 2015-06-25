package com.example.gkoeglbauer.kompasswaage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment_Left extends Fragment {

    ListView listView;
    public static ArrayList<Class_Position> positionList = new ArrayList<Class_Position>();
    public static ArrayList<String> positions = new ArrayList<String>();
    private OnSelectionChangedListener listener;

    public static void setPositionList(ArrayList<Class_Position> positionListe) {
        positionList = positionListe;
    }

    public static void setPositions(ArrayList<String> positionslist) {
        positions = positionslist;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__left, container);
        listView = (ListView) view.findViewById(R.id.listPositions);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                entrySelected(position);
            }
        });

        return view;

    }


    public interface OnSelectionChangedListener {
        public void onSelectionCHanged(int pos, String item);
    }

    @Override
    public void onStart() {
        super.onStart();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, positions);
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnSelectionChangedListener) {
            listener = (OnSelectionChangedListener) activity;
        }
    }

    public void entrySelected(int pos) {
        String position = positions.get(pos);
        if (listener != null) {
            listener.onSelectionCHanged(pos, position);
        }
    }
}
