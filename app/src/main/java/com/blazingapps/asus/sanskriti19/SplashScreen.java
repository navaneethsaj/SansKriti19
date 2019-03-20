package com.blazingapps.asus.sanskriti19;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    private FirebaseAuth mAuth;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.logo);
        Glide.with(this)
                .load(R.drawable.giphy)
                .into(imageView);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(final FirebaseUser currentUser) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (currentUser == null){
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
