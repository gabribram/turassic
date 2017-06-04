package com.gabri.turassic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabri.turassic.R;
import com.gabri.turassic.activity.SelectCarActivity;
import com.gabri.turassic.model.Car;
import com.gabri.turassic.model.Danger;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.TransitionManager;

import java.util.Collections;
import java.util.List;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Danger_Adapter extends RecyclerView.Adapter<Danger_View_Holder> {

    List<Danger> list = Collections.emptyList();
    Context context;
    public Danger_Adapter(List<Danger> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public Danger_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        Danger_View_Holder holder = new Danger_View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final Danger_View_Holder holder, int position) {

//Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView

        holder.danger_imageview.setImageResource(list.get(position).imageid);


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
    public void insert(int position, Danger history) {
        list.add(position, history);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified History object
    public void remove(Danger history) {
        int position = list.indexOf(history);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
