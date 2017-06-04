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

import java.util.Collections;
import java.util.List;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class History_Adapter extends RecyclerView.Adapter<View_Holder> {

    List<History> list = Collections.emptyList();
    MainActivity mainActivity;
    public History_Adapter(List<History> list, Activity activity) {
        this.list = list;
        this.mainActivity=(MainActivity)activity;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.price.setText(list.get(position).price);
        holder.time.setText(list.get(position).time);
        holder.details_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.go_details();
            }
        });

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
    public void insert(int position, History history) {
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
