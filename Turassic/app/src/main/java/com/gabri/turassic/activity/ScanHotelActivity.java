package com.gabri.turassic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gabri.turassic.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanHotelActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler{
    private static final String TAG = "TAG";
    private ZXingScannerView mScannerView;
    ViewGroup contentFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_hotel);
        getSupportActionBar().hide();
        contentFrame = (ViewGroup) findViewById(R.id.contente_layout);
        start_scanner();
    }
    public void start_scanner() {
        mScannerView = new ZXingScannerView(ScanHotelActivity.this);
        contentFrame.addView(mScannerView);
        mScannerView.setResultHandler(ScanHotelActivity.this);
        mScannerView.startCamera();
    }
    @Override
    public void handleResult(Result result) {
        Toast.makeText(this,result.getText(),Toast.LENGTH_LONG).show();
        Intent intent=new Intent(ScanHotelActivity.this,PartnerHotelActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("state", "resume");

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScannerView != null) mScannerView.stopCamera();
    }

}
