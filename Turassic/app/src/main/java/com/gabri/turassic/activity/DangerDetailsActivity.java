package com.gabri.turassic.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gabri.turassic.MainActivity;
import com.gabri.turassic.R;
import com.gabri.turassic.adapter.Danger_Adapter;
import com.gabri.turassic.model.Danger;
import com.gabri.turassic.utill.Constants;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DangerDetailsActivity extends AppCompatActivity {
    ImageView capture_imageview;
    ImageView takephoto_imageview;
    String filename="";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SELECT_PICTURE = 2;
    Uri outputFileUri;
    UCrop uCrop;
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton rent_floatactionbutton,history_floatactionbutton,setting_floatactionbutton;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_details);
        getSupportActionBar().setTitle("Inspection-check out Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        filename=getIntent().getExtras().getString("file");
        floatingActionMenu=(FloatingActionMenu)findViewById(R.id.float_action_menu);
        rent_floatactionbutton=(FloatingActionButton)findViewById(R.id.menu_rentcar);
        history_floatactionbutton=(FloatingActionButton)findViewById(R.id.menu_history);
        setting_floatactionbutton=(FloatingActionButton)findViewById(R.id.menu_setting);
        mainActivity=new MainActivity();
        rent_floatactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangerDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        history_floatactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(DangerDetailsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("index", 1);
                startActivity(intent);
            }
        });
        setting_floatactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(DangerDetailsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("index", 2);
                startActivity(intent);
            }
        });
        capture_imageview=(ImageView)findViewById(R.id.capturedanger_imageView);
        capture_imageview.setImageURI(Uri.fromFile(new File(filename)));
        takephoto_imageview=(ImageView)findViewById(R.id.takephoto_imageView);
        takephoto_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               show_takephoto_popup();
            }
        });
        List<Danger> history = fill_with_data();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.dangerphotodetails_list);
        Danger_Adapter adapter = new Danger_Adapter(history, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(10000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }
    public void show_takephoto_popup(){


        PopupMenu popupMenu = new PopupMenu(this,takephoto_imageview);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("popup",""+item.getItemId());
                switch (item.getTitle().toString()){
                    case "TakePhoto":
                        imageFromCamera();
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
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        takePictureIntent.putExtra()
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }


    public void imageFromCamera() {

        try {

            File root = new File(Environment.getExternalStorageDirectory(), Constants.KEY_APPTEMP_FOLDER);

            if (!root.exists()) {

                root.mkdirs();
            }

            File sdImageMainDirectory = new File(root, "photo.jpg");
            outputFileUri = Uri.fromFile(sdImageMainDirectory);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);

        } catch (Exception e) {

            Log.d("error_String", e.getLocalizedMessage());
            Toast.makeText(this, "Error occured. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
     private void selectimage_fromgallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.label_select_picture)), REQUEST_SELECT_PICTURE);
    }
    public Bitmap save(String outFile, String inFile) {

        try {

//            Bitmap bmp = resize(outFile);
            Bitmap bmp = BitmapFactory.decodeFile(outFile);
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + Constants.KEY_APPTEMP_FOLDER, inFile);
            FileOutputStream fOut = new FileOutputStream(file);

            bmp.compress(Bitmap.CompressFormat.JPEG, 90, fOut);

            fOut.flush();
            fOut.close();
            fixPicture(new File(outFile), bmp);
//            testingImage.setImageBitmap(bmp);
            return bmp;

        } catch (Exception ex) {

            Log.e("Error", ex.getMessage());
        }
        return null;
    }
    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    public void fixPicture(File file, Bitmap bitmap) {
        Log.d("rotation", "start");
        ExifInterface ei = null;
        try {
            ei = new ExifInterface(file.getPath());
        } catch (IOException e) {
            Log.d("rotation", e.getLocalizedMessage());
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
//		bitmap = rotateImage(bitmap, 90);

        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                Log.d("rotation", "90");
                bitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                Log.d("rotation", "180");
                bitmap =rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                Log.d("rotation", "270");
                bitmap =rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            {
                bitmap = rotateImage(bitmap, 90);
                Log.d("rotation", "0");
                break;
            }

            default:
                bitmap = rotateImage(bitmap, 90);
                Log.d("rotation", "end");
                break;
        }


        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("rotation", e.getLocalizedMessage());

        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            String selectedPostImg = Environment.getExternalStorageDirectory() + File.separator + Constants.KEY_APPTEMP_FOLDER + File.separator + "photo.jpg";
            String dest = Environment.getExternalStorageDirectory() + File.separator + Constants.KEY_APPTEMP_FOLDER + File.separator + "dest.jpg";

            Bitmap bt =  save(selectedPostImg, "photo.jpg");

            File destination = new File(dest);
            uCrop = UCrop.of(Uri.fromFile(new File(selectedPostImg)), Uri.fromFile(destination));
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
//                capture_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    public List<Danger> fill_with_data() {

        List<Danger> history = new ArrayList<>();
        Danger car=new Danger();
        car.setImageid(R.drawable.dangerfirst);
        history.add(car);


        Danger hotel1=new Danger();
        hotel1.setImageid(R.drawable.dangersecond);
        history.add(hotel1);


        Danger hotel2=new Danger();
        hotel2.setImageid(R.drawable.dangerthird);
        history.add(hotel2);




        return history;
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
