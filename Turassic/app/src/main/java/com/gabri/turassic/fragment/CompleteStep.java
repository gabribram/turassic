package com.gabri.turassic.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gabri.turassic.MainActivity;
import com.gabri.turassic.R;
import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class CompleteStep extends AbstractStep {

    private int i = 1;
    private Button button;
    private final static String CLICK = "click";
    private ImageView qr_imageview;
    public final static int QRcodeWidth = 500 ;
    ACProgressFlower dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.step_completed, container, false);
        button = (Button) v.findViewById(R.id.home_button);
        qr_imageview=(ImageView)v.findViewById(R.id.qr_imageView);
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        new QRCodeTask().execute("2341");
        if (savedInstanceState != null)
            i = savedInstanceState.getInt(CLICK, 0);

//        button.setText(Html.fromHtml("Tap <b>" + i + "</b>"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((Button) view).setText(Html.fromHtml("Tap <b>" + (++i) + "</b>"));
                i++;
                mStepper.getExtras().putInt(CLICK, i);
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(CLICK, i);
    }

    @Override
    public String name() {

        return "COMPLETED!";

    }

    @Override
    public boolean isOptional() {
        return true;
    }


    @Override
    public void onStepVisible() {
    }

    @Override
    public void onNext() {
        System.out.println("onNext");
    }

    @Override
    public void onPrevious() {
        System.out.println("onPrevious");
    }

//    @Override
//    public String optional() {
//        return "You can skip";
//    }

    @Override
    public boolean nextIf() {
        return i > 1;
    }

    @Override
    public String error() {
        return "<b>You must click!</b> <small>this is the condition!</small>";
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
            qr_imageview.setImageBitmap(bitmap);
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
