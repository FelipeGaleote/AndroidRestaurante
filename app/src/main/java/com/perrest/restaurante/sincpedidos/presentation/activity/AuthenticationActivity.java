package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.fragment.LoginFragment;
import com.perrest.restaurante.sincpedidos.presentation.fragment.SignUpFragment;

import butterknife.ButterKnife;

public class AuthenticationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);

        LoginFragment loginFragment = new LoginFragment();
        SignUpFragment signUpFragment = new SignUpFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,signUpFragment).commit();
    }
}
