package com.gabri.turassic.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabri.turassic.R;
import com.gabri.turassic.TakePhotoActivity;
import com.gabri.turassic.utill.Constants;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    View view;
    TextView totalpayment_textview;
    ImageView profile_imageview;
    Button save_button;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    UCrop uCrop;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_setting, container, false);
        totalpayment_textview=(TextView)view.findViewById(R.id.total_paymenttextView);
        totalpayment_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content,new HistoryFragment()).commit();
            }
        });
        profile_imageview=(ImageView)view.findViewById(R.id.show_image);
        profile_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TakePhotoActivity.class);
                startActivity(intent);
            }
        });
        save_button=(Button)view.findViewById(R.id.submint_button);
        return view;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destinationFileName)));
//        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
//            final Uri resultUri = UCrop.getOutput(data);
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            final Throwable cropError = UCrop.getError(data);
//        }


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
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
            uCrop.start(getActivity());
//            uCrop.ge
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uCrop.getOutput);
//            path = destination.getAbsolutePath();
//
////            userBitmap = thumbnail;
//            profile_imageview.setImageBitmap(temp_bitmap);
        Log.d("Camera","Yes");
        }
        else if (requestCode== uCrop.REQUEST_CROP && resultCode == RESULT_OK){

            Log.d("Image","No");
            Bundle extras = uCrop.getIntent(getActivity()).getExtras();
            if (extras==null){
                Log.d("Image","No");
            }
            else {
                Log.d("Image","Yes");
            }
            Bitmap thumbnail = (Bitmap) extras.get("data");
            profile_imageview.setImageBitmap(thumbnail);

        }
    }

}
