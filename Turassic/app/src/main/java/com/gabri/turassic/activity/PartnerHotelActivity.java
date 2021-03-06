package com.gabri.turassic.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gabri.turassic.R;
import com.gabri.turassic.adapter.History_Adapter;
import com.gabri.turassic.adapter.Hotel_Adapter;
import com.gabri.turassic.model.History;
import com.gabri.turassic.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class PartnerHotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_hotel);
        getSupportActionBar().setTitle("Partner Hotels");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().hide();

        List<Hotel> history = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.partnerhotel_list);
        Hotel_Adapter adapter = new Hotel_Adapter(history, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(10000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);


    }

    public List<Hotel> fill_with_data() {

        List<Hotel> history = new ArrayList<>();
        Hotel hotel=new Hotel();
        hotel.setAddress("Stationsplein 47, 1012 AB");
        hotel.setCarcount("23");
        hotel.setHotelname("Holiday Inn Washington-Capitol");
        hotel.setImageId(R.drawable.hotel);
        history.add(hotel);


        Hotel hotel1=new Hotel();
        hotel1.setAddress("Stationsplein 47, 1015 AB");
        hotel1.setCarcount("22");
        hotel1.setHotelname("Holiday Inn Washington-Capitol");
        hotel1.setImageId(R.drawable.hotel);
        history.add(hotel1);

        Hotel hotel2=new Hotel();
        hotel2.setAddress("Stationsplein 47, 1010 AB");
        hotel2.setCarcount("23");
        hotel2.setHotelname("Holiday Inn Washington-Capitol");
        hotel2.setImageId(R.drawable.hotel);
        history.add(hotel2);

        Hotel hotel3=new Hotel();
        hotel3.setAddress("Stationsplein 47, 1011 AB");
        hotel3.setCarcount("26");
        hotel3.setHotelname("Holiday Inn Washington-Capitol");
        hotel3.setImageId(R.drawable.hotel);
        history.add(hotel3);

        Hotel hotel4=new Hotel();
        hotel4.setAddress("Stationsplein 47, 1017 AB");
        hotel4.setCarcount("73");
        hotel4.setHotelname("Holiday Inn Washington-Capitol");
        hotel4.setImageId(R.drawable.hotel);
        history.add(hotel4);

        Hotel hotel5=new Hotel();
        hotel5.setAddress("Stationsplein 47, 1013 AB");
        hotel5.setCarcount("83");
        hotel5.setHotelname("Holiday Inn Washington-Capitol");
        hotel5.setImageId(R.drawable.hotel);
        history.add(hotel5);

        return history;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search
        }
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
