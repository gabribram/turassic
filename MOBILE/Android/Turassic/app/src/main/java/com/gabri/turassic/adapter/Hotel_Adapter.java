package com.gabri.turassic.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabri.turassic.MainActivity;
import com.gabri.turassic.R;
import com.gabri.turassic.model.History;
import com.gabri.turassic.model.Hotel;

import java.util.Collections;
import java.util.List;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Hotel_Adapter extends RecyclerView.Adapter<Hotel_View_Holder> {

    List<Hotel> list = Collections.emptyList();
    Context context;
    public Hotel_Adapter(List<Hotel> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public Hotel_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        Hotel_View_Holder holder = new Hotel_View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(Hotel_View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.hoteladdress_textview.setText(list.get(position).address);
        holder.hotelname_textview.setText(list.get(position).hotelname);
        holder.hotel_imaeview.setImageResource(list.get(position).imageId);
        holder.car_textview.setText(list.get(position).carcount);
        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Hotel history) {
        list.add(position, history);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified History object
    public void remove(History history) {
        int position = list.indexOf(history);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
