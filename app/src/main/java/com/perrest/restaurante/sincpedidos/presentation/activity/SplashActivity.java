package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.perrest.restaurante.sincpedidos.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.splash_logo);

        logo.post(() -> rotateView(logo,2000));
        logo.postDelayed(this::goToAuthenticationActivity, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void rotateView(View view, int duracao){
        RotateAnimation animation = new RotateAnimation(0.0f,360.0f,view.getWidth()/2,view.getHeight());
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(duracao);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }

    private void goToAuthenticationActivity(){
        logo.clearAnimation();
        Intent intent = new Intent(this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }
}
