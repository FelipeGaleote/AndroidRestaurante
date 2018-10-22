package com.perrest.restaurante.sincpedidos.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Produto;
import com.perrest.restaurante.sincpedidos.presentation.adapter.ProductsListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListFragment extends Fragment {

    @BindView(R.id.products_list_recycler_view)
    RecyclerView recyclerView;

    //Using this constants just to simulate the app behavior while backend isn't ready
    private final int fakeProductsQuantity = 15;

    public ProductsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this, view);

        ArrayList<Produto> products = generateFakeProducts();

        ProductsListAdapter adapter = new ProductsListAdapter(getContext(), products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getAdapter().notifyDataSetChanged();
        return view;
    }

    private ArrayList<Produto> generateFakeProducts() {
        ArrayList<Produto> generatedList = new ArrayList<>();
        for (int i = 0; i < fakeProductsQuantity; i++) {
            String name = "Produto " + (i+1);
            String description = "Esta é a descrição do " + name;
            double price = (Math.random() * fakeProductsQuantity * 4);
            Produto product = new Produto(i, name, description, price);
            generatedList.add(product);
        }
        return generatedList;
    }
}
