package com.gabri.turassic.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.gabri.turassic.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class InspectionActivity extends AppCompatActivity {
    ImageView background_imageview;
    ImageView takephoto_imageview;
    Bitmap bitmapMaster;
    Canvas canvasMaster;
    Bitmap bitmapDrawingPane;
    Canvas canvasDrawingPane;
    projectPt startPt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        getSupportActionBar().setTitle("Inspection-check out");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        takephoto_imageview=(ImageView)findViewById(R.id.capture_imageView);

        background_imageview=(ImageView)findViewById(R.id.inspectoin_imageView);
        load_image();
        background_imageview.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch(action){
                    case MotionEvent.ACTION_DOWN:

                        startPt = projectXY((ImageView)v, bitmapMaster, x, y);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        drawOnRectProjectedBitMap((ImageView)v, bitmapMaster, x, y);
//                        finalizeDrawing();
                        break;
                    case MotionEvent.ACTION_UP:

                        drawOnRectProjectedBitMap((ImageView)v, bitmapMaster, x, y);
                        finalizeDrawing();
                        break;
                }
    /*
     * Return 'true' to indicate that the event have been consumed.
     * If auto-generated 'false', your code can detect ACTION_DOWN only,
     * cannot detect ACTION_MOVE and ACTION_UP.
     */
                return true;
            }});
        takephoto_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image=((BitmapDrawable)background_imageview.getDrawable()).getBitmap();
                Intent intent=new Intent(InspectionActivity.this,DangerDetailsActivity.class);
                intent.putExtra("file",saveImageFile(image));
                startActivity(intent);
            }
        });
    }
    public String saveImageFile(Bitmap bitmap) {
        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filename;
    }

    private String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), "turassic");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/"
                + "capture" + ".jpg");
        return uriSting;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_danger, menu);
        // Associate searchable configuration with the SearchView


        return true;
    }


    public void  load_image(){

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.dangercar);
        background_imageview.setImageBitmap(largeIcon);

        Bitmap tempBitmap;


        //tempBitmap is Immutable bitmap,
        //cannot be passed to Canvas constructor
        tempBitmap =largeIcon;

        Bitmap.Config config;
        if(tempBitmap.getConfig() != null){
            config = tempBitmap.getConfig();
        }else{
            config = Bitmap.Config.ARGB_8888;
        }

        //bitmapMaster is Mutable bitmap
        bitmapMaster = Bitmap.createBitmap(
                tempBitmap.getWidth(),
                tempBitmap.getHeight(),
                config);

        canvasMaster = new Canvas(bitmapMaster);
        canvasMaster.drawBitmap(tempBitmap, 0, 0, null);


        //Create bitmap of same size for drawing
        bitmapDrawingPane = Bitmap.createBitmap(
                tempBitmap.getWidth(),
                tempBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        canvasDrawingPane = new Canvas(bitmapDrawingPane);

//        canvasDrawingPane.drawBitmap();
        background_imageview.setImageBitmap(bitmapMaster);

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
            case R.id.clear:{
                load_image();
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    class projectPt{
        int x;
        int y;

        projectPt(int tx, int ty){
            x = tx;
            y = ty;
        }
    }

    private projectPt projectXY(ImageView iv, Bitmap bm, int x, int y){
        if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
            //outside ImageView
            return null;
        }else{
            int projectedX = (int)((double)x * ((double)bm.getWidth()/(double)iv.getWidth()));
            int projectedY = (int)((double)y * ((double)bm.getHeight()/(double)iv.getHeight()));

            return new projectPt(projectedX, projectedY);
        }
    }

    private void drawOnRectProjectedBitMap(ImageView iv, Bitmap bm, int x, int y){
        if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
            //outside ImageView
            return;
        }else{
            int projectedX = (int)((double)x * ((double)bm.getWidth()/(double)iv.getWidth()));
            int projectedY = (int)((double)y * ((double)bm.getHeight()/(double)iv.getHeight()));

            //clear canvasDrawingPane
            canvasDrawingPane.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#F57C00"));
            paint.setStrokeWidth(8);
            canvasDrawingPane.drawRect(startPt.x, startPt.y, projectedX, projectedY, paint);
            background_imageview.invalidate();

        }
    }

    Canvas tempCanvas = null;
    private void finalizeDrawing(){
        canvasMaster.drawBitmap(bitmapDrawingPane, 0, 0, null);
    }
}
