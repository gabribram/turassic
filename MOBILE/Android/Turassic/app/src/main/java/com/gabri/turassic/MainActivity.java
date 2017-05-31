package com.gabri.turassic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gabri.turassic.fragment.HistoryDetailsFragment;
import com.gabri.turassic.fragment.HistoryFragment;
import com.gabri.turassic.fragment.PartnerHotelFragment;
import com.gabri.turassic.fragment.RentFragment;
import com.gabri.turassic.fragment.ReverationFragment;
import com.gabri.turassic.fragment.SettingFragment;
import com.gabri.turassic.model.History;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    SettingFragment settingFragment;
    HistoryFragment historyFragment;
    ReverationFragment rentFragment;
    HistoryDetailsFragment historyDetailsFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,rentFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,historyFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,settingFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        rentFragment=new ReverationFragment();
        settingFragment=new SettingFragment();
        historyFragment=new HistoryFragment();
        historyDetailsFragment=new HistoryDetailsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,rentFragment).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
   public  void go_details(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,historyDetailsFragment).commit();
   }
    public  void go_partnerhotel(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new PartnerHotelFragment()).commit();
    }
}
