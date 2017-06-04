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

    public static MainActivity mainInstance;
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

    int index = 0;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null)
        {
            index = getIntent().getExtras().getInt("index");
        }
        mainInstance = this;
        getSupportActionBar().hide();
        rentFragment=new ReverationFragment();
        settingFragment=new SettingFragment();
        historyFragment=new HistoryFragment();
        historyDetailsFragment=new HistoryDetailsFragment();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showFragment(index);

    }
    public void showFragment(int index)
    {

        switch (index)
        {
            case 0:
            {
                navigation.setSelectedItemId(R.id.navigation_home);
                getSupportFragmentManager().beginTransaction().replace(R.id.content,rentFragment).commit();
                break;
            }
            case 1:
            {
                navigation.setSelectedItemId(R.id.navigation_dashboard);
                go_history();
                break;
            }
            case 2:
            {
                navigation.setSelectedItemId(R.id.navigation_notifications);
                go_setting();
                break;
            }
            default:
                navigation.setSelectedItemId(R.id.navigation_home);
                getSupportFragmentManager().beginTransaction().replace(R.id.content,rentFragment).commit();
        }

    }
   public  void go_details(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,historyDetailsFragment).commit();
   }
    public  void go_partnerhotel(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new PartnerHotelFragment()).commit();
    }
    public void go_history(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new HistoryFragment()).commit();
    }
    public  void go_setting(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new SettingFragment()).commit();
    }
}
