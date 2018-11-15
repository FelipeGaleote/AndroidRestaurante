package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Pedido;
import com.perrest.restaurante.sincpedidos.domain.util.Constants;
import com.perrest.restaurante.sincpedidos.presentation.fragment.CategoriesFragment;
import com.perrest.restaurante.sincpedidos.presentation.fragment.ProductsListFragment;
import com.perrest.restaurante.sincpedidos.repository.PedidoRepository;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.OnCategorySelectedListener, PedidoRepository.OrderListener {

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

        PedidoRepository pedidoRepository = new PedidoRepository(this);
        Pedido pedido = new Pedido();
        pedido.setIdMesa(SharedPrefsUtil.getSelectedTable(this));
        pedido.setIdUsuario(SharedPrefsUtil.getToken(this));
        pedidoRepository.saveOrder(pedido);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_orders:
                startActivity(new Intent(this, ShowOrdersActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onOrderCreated(long orderId) {
        SharedPrefsUtil.saveOrderId(this, orderId);
    }

    @Override
    public void onOrderCreateFailed() {

    }
}
