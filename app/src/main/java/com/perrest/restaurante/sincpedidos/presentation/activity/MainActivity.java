package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.util.Constants;
import com.perrest.restaurante.sincpedidos.presentation.fragment.CategoriesFragment;
import com.perrest.restaurante.sincpedidos.presentation.fragment.ProductsListFragment;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnCategorySelectedListener {

    private CategoriesFragment categoriesFragment;
    private ProductsListFragment productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriesFragment = new CategoriesFragment();
        categoriesFragment.setOnCategorySelectedListener(this);
        changeFragment(categoriesFragment);

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.categories);
    }

    @Override
    public void onCategorySelected(Constants.Category category) {
        if(productsListFragment == null)
            productsListFragment = new ProductsListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("category",category);
        productsListFragment.setArguments(bundle);
        changeFragment(productsListFragment);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(category.toString(this));
    }

    private void changeFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, fragment);
        fragmentTransaction.commit();
    }

}
