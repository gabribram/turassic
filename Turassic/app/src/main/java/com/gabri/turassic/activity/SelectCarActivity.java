package com.gabri.turassic.activity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.gabri.turassic.R;

import java.util.ArrayList;

public class SelectCarActivity extends AppCompatActivity implements WheelPicker.OnItemSelectedListener {
    private WheelPicker wheelCenter;
    private WheelPicker wheelMin;
    private TextView hours_textview;
    private TextView min_textview;
    private Button submit_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        getSupportActionBar().setTitle("Making the reservation this car");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hours_textview=(TextView)findViewById(R.id.hour_textView);
        min_textview=(TextView)findViewById(R.id.min_textView);
        submit_button=(Button)findViewById(R.id.submit_button);
        wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_hours);
        wheelCenter.setOnItemSelectedListener(this);
        wheelMin=(WheelPicker)findViewById(R.id.main_wheel_min);
        wheelMin.setOnItemSelectedListener(this);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(SelectCarActivity.this,ReservationActivity.class);
                startActivity(intent);
            }
        });
        sethours();
        setmin();
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        switch (picker.getId()) {
            case R.id.main_wheel_hours:
                hours_textview.setText(String.valueOf(data));
                break;
            case R.id.main_wheel_min:
                min_textview.setText(String.valueOf(data));
                break;

        }
        //Toast.makeText(this, String.valueOf(data), Toast.LENGTH_SHORT).show();
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
    public void sethours(){

        ArrayList<String> list = new ArrayList<String>();
        list.add("00");
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("06");
        list.add("07");
        list.add("08");
        wheelCenter.setData(list);

    }
    public void setmin(){

        ArrayList<String> list = new ArrayList<String>();
        list.add("00");
        list.add("15");
        list.add("30");
        list.add("45");
        wheelMin.setData(list);

    }
}
