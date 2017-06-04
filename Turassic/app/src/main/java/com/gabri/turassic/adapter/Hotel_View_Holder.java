package com.gabri.turassic.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabri.turassic.R;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Hotel_View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    ImageView hotel_imaeview;
    TextView hotelname_textview;
    TextView hoteladdress_textview;
    TextView car_textview;

    Hotel_View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView_hotel);
        hotel_imaeview=(ImageView)itemView.findViewById(R.id.hotel_imageView);
        hotelname_textview=(TextView)itemView.findViewById(R.id.hotelname_textView);
        hoteladdress_textview=(TextView)itemView.findViewById(R.id.hoteladdress_textView);
        car_textview=(TextView)itemView.findViewById(R.id.caravailablecount_textView);
    }
}