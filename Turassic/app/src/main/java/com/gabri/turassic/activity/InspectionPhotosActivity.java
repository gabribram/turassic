package com.gabri.turassic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.gabri.turassic.R;
import com.gabri.turassic.adapter.Danger_Adapter;
import com.gabri.turassic.model.Danger;

import java.util.ArrayList;
import java.util.List;

public class InspectionPhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_photos);
        getSupportActionBar().setTitle("Details Photos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Danger> history = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.dangerphoto_list);
        Danger_Adapter adapter = new Danger_Adapter(history, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(10000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }


    public List<Danger> fill_with_data() {

        List<Danger> history = new ArrayList<>();
        Danger car=new Danger();
        car.setImageid(R.drawable.dangerfirst);
        history.add(car);


        Danger hotel1=new Danger();
        hotel1.setImageid(R.drawable.dangersecond);
        history.add(hotel1);


        Danger hotel2=new Danger();
        hotel2.setImageid(R.drawable.dangerthird);
        history.add(hotel2);




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
