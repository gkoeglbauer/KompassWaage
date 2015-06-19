package com.example.gkoeglbauer.kompasswaage;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Fragment_Left extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__left, container);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void show()
    {

    }
}
