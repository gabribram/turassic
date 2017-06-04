package com.gabri.turassic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gabri.turassic.MapsActivity;
import com.gabri.turassic.R;

public class InspectionDetailsActivity extends AppCompatActivity {
    ImageView details_imageview;
    ImageView rent_imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_details);
        getSupportActionBar().setTitle("Inspection-check in");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        details_imageview=(ImageView)findViewById(R.id.details_imageView);
        details_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InspectionDetailsActivity.this,InspectionPhotosActivity.class);
                startActivity(intent);
            }
        });
        rent_imageview=(ImageView)findViewById(R.id.rent_imageView);
        rent_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InspectionDetailsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
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
