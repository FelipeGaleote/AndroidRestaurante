package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.adapter.AuthenticationAdapter;
import com.perrest.restaurante.sincpedidos.presentation.fragment.LoginFragment;
import com.perrest.restaurante.sincpedidos.presentation.fragment.SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthenticationActivity extends AppCompatActivity{

    @BindView(R.id.authentication_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.authentication_view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);

        viewPager.setAdapter(new AuthenticationAdapter(getSupportFragmentManager(),this));
        tabLayout.setupWithViewPager(viewPager);
    }
}
