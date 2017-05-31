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

public class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    TextView price;
    TextView time;
    ImageView imageView;
    ImageView details_history;

    View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        price=(TextView)itemView.findViewById(R.id.price_textView);
        time=(TextView)itemView.findViewById(R.id.time_textView);
        details_history=(ImageView)itemView.findViewById(R.id.more_imageView);
    }
}