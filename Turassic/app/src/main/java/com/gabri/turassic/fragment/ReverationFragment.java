package com.gabri.turassic.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gabri.turassic.MapsActivity;
import com.gabri.turassic.MapsPartnerActivity;
import com.gabri.turassic.R;
import com.gabri.turassic.activity.PartnerHotelActivity;
import com.gabri.turassic.activity.ScanHotelActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReverationFragment extends Fragment {

    View view;
    Button hotelmap_button;
    Button hotelid_button;
    Button scanqrcode_button;
    public ReverationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_reveration, container, false);
        hotelmap_button=(Button)view.findViewById(R.id.find_map_button);
        hotelid_button=(Button)view.findViewById(R.id.hotelid_button);
        scanqrcode_button=(Button)view.findViewById(R.id.qrcode_button);
        hotelmap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), MapsPartnerActivity.class);
                startActivity(intent);

            }
        });
        hotelid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PartnerHotelActivity.class);
                startActivity(intent);
            }
        });
        scanqrcode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ScanHotelActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
