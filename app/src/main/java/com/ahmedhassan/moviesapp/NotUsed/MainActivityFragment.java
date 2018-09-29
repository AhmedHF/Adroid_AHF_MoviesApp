package com.ahmedhassan.moviesapp.NotUsed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedhassan.moviesapp.NotUsed.NameListener;
import com.ahmedhassan.moviesapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private NameListener mListener;

    void setNameListener(NameListener nameListener) {
        this.mListener = nameListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        return rootView;

    }
}
