package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        logo.post(() -> rotacionarView(logo,1800));
        logo.postDelayed(this::irParaTelaDeLogin, 4000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void rotacionarView(View view, int duracao){
        RotateAnimation animacao = new RotateAnimation(0.0f,360.0f,view.getWidth()/2,view.getHeight());
        animacao.setInterpolator(new AccelerateDecelerateInterpolator());
        animacao.setDuration(duracao);
        animacao.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animacao);
    }

    private void irParaTelaDeLogin(){
        logo.clearAnimation();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
