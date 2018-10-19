package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.util.Constants;
import com.perrest.restaurante.sincpedidos.presentation.fragment.CategoriesFragment;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnCategorySelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoriesFragment categoriesFragment = new CategoriesFragment();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_placeholder, categoriesFragment);
        fragmentTransaction.commit();

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.categories);
    }

    @Override
    public void onCategorySelected(Constants.Category category) {

    }
}
