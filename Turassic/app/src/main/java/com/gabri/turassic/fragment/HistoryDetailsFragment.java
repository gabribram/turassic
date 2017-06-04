package com.gabri.turassic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabri.turassic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryDetailsFragment extends Fragment {

    View view;
    public HistoryDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_history_details, container, false);
        return view;
    }

}
