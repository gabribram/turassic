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

public class Danger_View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    ImageView danger_imageview;

    Danger_View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        danger_imageview=(ImageView)itemView.findViewById(R.id.danger_imageView);
    }
}