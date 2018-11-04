package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.util.Constants;
import com.perrest.restaurante.sincpedidos.presentation.fragment.CategoriesFragment;
import com.perrest.restaurante.sincpedidos.presentation.fragment.ProductsListFragment;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnCategorySelectedListener {

    private CategoriesFragment categoriesFragment;
    private ProductsListFragment productsListFragment;
    private final String CATEGORY_FRAGMENT = "category";
    private final String PRODUCT_LIST_FRAGMENT = "product_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriesFragment = new CategoriesFragment();
        categoriesFragment.setOnCategorySelectedListener(this);
        changeFragment(categoriesFragment, CATEGORY_FRAGMENT);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.categories);
    }

    @Override
    public void onCategorySelected(Constants.Category category) {
        if (productsListFragment == null)
            productsListFragment = new ProductsListFragment();
        productsListFragment.setCategory(category);

        changeFragment(productsListFragment, PRODUCT_LIST_FRAGMENT);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(category.toString(this));
    }

    private void changeFragment(android.support.v4.app.Fragment fragment, String fragmentTag) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, fragment);
        if (fragmentTag.equals(PRODUCT_LIST_FRAGMENT))
            fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.categories);
    }
}
