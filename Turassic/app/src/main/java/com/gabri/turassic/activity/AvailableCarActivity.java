package com.gabri.turassic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.gabri.turassic.R;
import com.gabri.turassic.adapter.Car_Adapter;
import com.gabri.turassic.adapter.Hotel_Adapter;
import com.gabri.turassic.model.Car;
import com.gabri.turassic.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class AvailableCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_car);
        getSupportActionBar().setTitle("Stationsplein 47, 1012 AB");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Car> history = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.car_list);
        Car_Adapter adapter = new Car_Adapter(history, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(10000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }

    public List<Car> fill_with_data() {

        List<Car> history = new ArrayList<>();
        Car car=new Car();
        car.setModel("AUD");
        car.setHourly("$34/h");
        car.setType("Auto");
        car.setYear("2015");
        car.setImageId(R.drawable.car);
        history.add(car);


        Car hotel1=new Car();
        hotel1.setModel("AUD");
        hotel1.setHourly("$54/h");
        hotel1.setType("Auto");
        hotel1.setYear("2013");
        hotel1.setImageId(R.drawable.carse);
        history.add(hotel1);


        Car hotel2=new Car();
        hotel2.setModel("AUD");
        hotel2.setHourly("$60/h");
        hotel2.setType("Auto");
        hotel2.setYear("2011");
        hotel2.setImageId(R.drawable.carfu);
        history.add(hotel2);


        Car hotel3=new Car();
        hotel3.setModel("AUD");
        hotel3.setHourly("$34/h");
        hotel3.setType("Manual");
        hotel3.setYear("2015");
        hotel3.setImageId(R.drawable.carsix);
        history.add(hotel3);


        Car hotel4=new Car();
        hotel4.setModel("AUD");
        hotel4.setHourly("$34/h");
        hotel4.setType("Auto");
        hotel4.setYear("2015");
        hotel4.setImageId(R.drawable.carfif);
        history.add(hotel4);


        Car hotel5=new Car();
        hotel5.setModel("AUD");
        hotel5.setHourly("$34/h");
        hotel5.setType("Auto");
        hotel5.setYear("2015");
        hotel5.setImageId(R.drawable.car);
        history.add(hotel5);


        Car hotel6=new Car();
        hotel6.setModel("AUD");
        hotel6.setHourly("$34/h");
        hotel6.setType("Auto");
        hotel6.setYear("2015");
        hotel6.setImageId(R.drawable.car);
        history.add(hotel6);

        return history;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                onBackPressed();
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
