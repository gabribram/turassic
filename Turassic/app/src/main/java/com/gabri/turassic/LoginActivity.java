package com.gabri.turassic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button login_button;
    TextView singup_textview;
    TextView terms_textview;
    public static final int REQUEST_CODE = 1336;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        login_button=(Button)findViewById(R.id.login_button);
        singup_textview=(TextView)findViewById(R.id.signup_textView);
        terms_textview=(TextView)findViewById(R.id.terms_textView);
        terms_textview.setPaintFlags(terms_textview.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        singup_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(LoginActivity.this,Register.class);
            startActivity(intent);
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
