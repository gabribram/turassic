package com.gabri.turassic.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gabri.turassic.R;
import com.gabri.turassic.activity.SelectCarActivity;
import com.gabri.turassic.model.Car;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.TransitionManager;

import java.util.Collections;
import java.util.List;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Car_Adapter extends RecyclerView.Adapter<Car_View_Holder> {

    List<Car> list = Collections.emptyList();
    Context context;
    public Car_Adapter(List<Car> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public Car_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        Car_View_Holder holder = new Car_View_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final Car_View_Holder holder, int position) {

//Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.type_textview.setText(list.get(position).getType());
        holder.model_textview.setText(list.get(position).getModel());
        holder.car_imagevew.setImageResource(list.get(position).imageId);
        holder.year_textview.setText(list.get(position).getYear());
        holder.hourly_textview.setText(list.get(position).getHourly());
//        holder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, AvailableCarActivity.class);
//                context.startActivity(intent);
//            }
//        });
        holder.more_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SelectCarActivity.class);
                context.startActivity(intent);
            }
        });
        holder.control_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                        holder.show_layout.setVisibility(View.GONE);
                        boolean shouldExpand = holder.show_layout.getVisibility() == View.GONE;
                        ChangeBounds transition = new ChangeBounds();
                        transition.setDuration(125);
                        if (shouldExpand) {
                            holder.show_layout.setVisibility(View.VISIBLE);
                            holder.control_imageview.setImageResource(R.drawable.ic_up);
                        } else {
                            holder.show_layout.setVisibility(View.GONE);
                            holder.control_imageview.setImageResource(R.drawable.ic_more_list);
                        }
                        TransitionManager.beginDelayedTransition(holder.cv, transition);
                        holder.control_imageview.setActivated(shouldExpand);
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
    public void insert(int position, Car history) {
        list.add(position, history);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified History object
    public void remove(Car history) {
        int position = list.indexOf(history);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
