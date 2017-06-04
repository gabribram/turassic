package com.gabri.turassic.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabri.turassic.MapsActivity;
import com.gabri.turassic.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;


/**
 * A simple {@link Fragment} subclass.
 */
public class RentFragment extends Fragment {

    View view;
    ImageView qrcode_imageview;
//    FloatingActionButton track_floatactionbutton;
    TextView textView;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    ACProgressFlower dialog;
    public RentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_rent, container, false);
//        track_floatactionbutton=(FloatingActionButton)view.findViewById(R.id.track_floatingActionButton);
//        track_floatactionbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                result_alert();
//            }
//        });
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
//        dialog.show();
        qrcode_imageview=(ImageView)view.findViewById(R.id.qrcode_imageView);
        new QRCodeTask().execute("2341");

        return view;
    }
    public void result_alert(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.successalert);



        Button dialogButton = (Button) dialog.findViewById(R.id.start_button);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent=new Intent(getActivity(),MapsActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }
    public class QRCodeTask extends AsyncTask <String, Void, Bitmap>
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
