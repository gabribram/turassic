package com.gabri.turassic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabri.turassic.R;
import com.gabri.turassic.adapter.History_Adapter;
import com.gabri.turassic.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_history, container, false);
        List<History> history = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        History_Adapter adapter = new History_Adapter(history, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(10000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        return view;
    }
    public List<History> fill_with_data() {

        List<History> history = new ArrayList<>();

        history.add(new History("Skoda Citigo or similar – Manual", "Stationsplein 47, 1012 AB Amsterdam","$50","2017-05-14:05:34~5h", R.drawable.car));
        history.add(new History("VW Up or similar – Manual", "Stationsplein 47, 1012 AB Amsterdam","$50","2017-05-14:05:34~5h", R.drawable.carse));
        history.add(new History("Hyundai i20 or similar – Manual",  "Stationsplein 47, 1012 AB Amsterdam","$50","2017-05-14:05:34~5h", R.drawable.carth));
        history.add(new History("Skoda Fabia or similar", "Stationsplein 47, 1012 AB Amsterdam","$50","2017-05-14:05:34~5h", R.drawable.carfu));
        history.add(new History("Hyundai i20 or similar", "Stationsplein 47, 1012 AB Amsterdam","$50","2017-05-14:05:34~5h", R.drawable.carfif));
        history.add(new History("Skoda Citigo or similar", "Stationsplein 47, 1012 AB Amsterdam","$50","2017-05-14:05:34~5h", R.drawable.carsix));

        return history;
    }

}
