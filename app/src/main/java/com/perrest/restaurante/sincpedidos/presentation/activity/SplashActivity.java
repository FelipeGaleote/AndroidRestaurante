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
import com.perrest.restaurante.sincpedidos.domain.entity.User;
import com.perrest.restaurante.sincpedidos.repository.UserRepository;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

public class SplashActivity extends AppCompatActivity implements UserRepository.UserAuthListener {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.splash_logo);

        User user = SharedPrefsUtil.getLoginInfo(this);
        UserRepository repository = new UserRepository(this);
        repository.login(user);

        logo.post(() -> rotateView(logo, 3000));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void rotateView(View view, int duracao) {
        RotateAnimation animation = new RotateAnimation(0.0f, 360.0f, view.getWidth() / 2, view.getHeight());
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(duracao);
        animation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(animation);
    }

    @Override
    public void onUserAuthenticated(String userId) {
        SharedPrefsUtil.saveToken(this, userId);
        Intent intent;
        if (SharedPrefsUtil.getSelectedTable(getBaseContext()) != -1) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, ChooseTableActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void onUserRegistered(String userId) {

    }

    @Override
    public void onFailed(String errorMessage) {
        startActivity(new Intent(this, AuthenticationActivity.class));
        finish();
    }
}
