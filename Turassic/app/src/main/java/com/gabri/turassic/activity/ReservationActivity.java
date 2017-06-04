package com.gabri.turassic.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabri.turassic.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class ReservationActivity extends AppCompatActivity {
    ImageView qrcode_imageview;
    //    FloatingActionButton track_floatactionbutton;
    TextView textView;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    ACProgressFlower dialog;
    TextView reservatoinnumber_textview;
    Button next_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        getSupportActionBar().setTitle("Reservation completed!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
//        dialog.show();
        qrcode_imageview=(ImageView)findViewById(R.id.oneqr_imageView);
        reservatoinnumber_textview=(TextView)findViewById(R.id.reservationnumber_textView);
        reservatoinnumber_textview.setText("2343");
        next_button=(Button)findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReservationActivity.this,InspectionDetailsActivity.class);
                startActivity(intent);
            }
        });
        new QRCodeTask().execute("2343");
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
    public class QRCodeTask extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String value = params[0];
            try {
                Bitmap bitmap = TextToImageEncode(value);
                return bitmap;
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            dialog.dismiss();
            qrcode_imageview.setImageBitmap(bitmap);
        }
    }
    Bitmap TextToImageEncode(String Value) throws WriterException {

        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);

        return bitmap;
    }
}
