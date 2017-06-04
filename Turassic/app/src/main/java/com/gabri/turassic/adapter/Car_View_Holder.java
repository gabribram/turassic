package com.gabri.turassic.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabri.turassic.R;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Car_View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    ImageView car_imagevew,more_imageview,control_imageview;
    TextView type_textview;
    TextView model_textview;
    TextView year_textview;
    TextView hourly_textview;
    ConstraintLayout show_layout;


    Car_View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView_car);
        type_textview=(TextView)itemView.findViewById(R.id.type_textView);
        model_textview=(TextView)itemView.findViewById(R.id.model_textView);
        year_textview=(TextView)itemView.findViewById(R.id.year_textView);
        hourly_textview=(TextView)itemView.findViewById(R.id.hourly_textView);
        car_imagevew=(ImageView)itemView.findViewById(R.id.car_imageView);
        more_imageview=(ImageView)itemView.findViewById(R.id.more_details_imageView);
        control_imageview=(ImageView)itemView.findViewById(R.id.control_imageView);
        show_layout=(ConstraintLayout)itemView.findViewById(R.id.show_layout);

    }
}