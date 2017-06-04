package com.gabri.turassic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gabri.turassic.utill.Constants;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DangerActivity extends AppCompatActivity {
    ImageView report_imageview;
    Button report_button;
    Button skip_button;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SELECT_PICTURE = 2;
    UCrop uCrop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);
        getSupportActionBar().hide();
        report_button=(Button)findViewById(R.id.report_button);
        skip_button=(Button)findViewById(R.id.skype_button);
        report_imageview=(ImageView)findViewById(R.id.report_imageview);
        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DangerActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        report_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_takephoto_popup();
            }
        });
        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            show_alert();
            }
        });


    }
    public void show_alert(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.report_alert);



        Button agree_button = (Button) dialog.findViewById(R.id.agree_button);
        Button skip_button=(Button)dialog.findViewById(R.id.skip_button);
        agree_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                show_takephoto_popup();

            }
        });
        skip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
    public void show_takephoto_popup(){


        PopupMenu popupMenu = new PopupMenu(this,report_imageview);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("popup",""+item.getItemId());
                switch (item.getTitle().toString()){
                    case "TakePhoto":
                        dispatchTakePictureIntent();
                        return true;
                    case "Select Image From Gallery":
                        selectimage_fromgallery();
                        return true;
                    default:
                        return true;
                }

            }
        });
        popupMenu.getMenu().add("TakePhoto");
        popupMenu.getMenu().add("Select Image From Gallery");
        popupMenu.show();


    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    private void selectimage_fromgallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.label_select_picture)), REQUEST_SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d("Camera","Ok");
            String uploadFolder = Constants.KEY_SAVEPHOTO_URL;
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            String PATH = Environment.getExternalStorageDirectory() + "/" + uploadFolder + "/";
            File folder = new File(PATH);
            if (!folder.exists()) {
                folder.mkdir();//If there is no folder it will be created.
            }
            File destination = new File(PATH,
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            uCrop = UCrop.of(data.getData(), Uri.fromFile(destination));
            uCrop.withAspectRatio(3,2);
            uCrop.start(this);
        }
        else if (requestCode==REQUEST_SELECT_PICTURE  && resultCode == RESULT_OK){
            String uploadFolder = Constants.KEY_SAVEPHOTO_URL;
            String PATH = Environment.getExternalStorageDirectory() + "/" + uploadFolder + "/";
            File folder = new File(PATH);
            if (!folder.exists()) {
                folder.mkdir();//If there is no folder it will be created.
            }
            File destination = new File(PATH,
                    System.currentTimeMillis() + ".jpg");
            uCrop = UCrop.of(data.getData(), Uri.fromFile(destination));
            uCrop.withAspectRatio(3,2);
            uCrop.start(this);
        }
        else if (requestCode== uCrop.REQUEST_CROP && resultCode == RESULT_OK){
            final Uri resultUri = UCrop.getOutput(data);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                report_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
