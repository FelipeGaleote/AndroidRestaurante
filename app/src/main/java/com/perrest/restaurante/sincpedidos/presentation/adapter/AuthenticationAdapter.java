package com.perrest.restaurante.sincpedidos.presentation.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.fragment.LoginFragment;
import com.perrest.restaurante.sincpedidos.presentation.fragment.SignUpFragment;

public class AuthenticationAdapter extends FragmentPagerAdapter {

    private Context context;

    public AuthenticationAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoginFragment();
            case 1:
                return new SignUpFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.login);
            case 1:
                return context.getString(R.string.cadastrar_conta);
            default:
                return null;
        }
    }
}
