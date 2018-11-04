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
import com.perrest.restaurante.sincpedidos.domain.util.Constants;
import com.perrest.restaurante.sincpedidos.presentation.adapter.ProductsListAdapter;
import com.perrest.restaurante.sincpedidos.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListFragment extends Fragment implements ProductRepository.ProductsLoadListener {

    @BindView(R.id.products_list_recycler_view)
    RecyclerView recyclerView;

    private Constants.Category category;
    private ProductsListAdapter adapter;
    private ProductRepository repository;

    //Using this constants just to simulate the app behavior while backend isn't ready
    private final int fakeProductsQuantity = 15;

    public ProductsListFragment() {
        repository = new ProductRepository(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this, view);

        configRecyclerView();
        loadProducts();

        return view;
    }


    private void configRecyclerView(){
        adapter = new ProductsListAdapter(getContext(), getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void loadProducts(){
        switch (category){
            case BURGERS:
                repository.findAllBurgers();
                break;
            case DRINKS:
                repository.findAllDrinks();
                break;
            case PIZZAS:
                repository.findAllPizzas();
                break;
            case SALADS:
                repository.findAllSalads();
                break;
            case SNACKS:
                repository.findAllSnacks();
                break;
            case DESSERTS:
                repository.findAllDesserts();
                break;
        }
    }

    private ArrayList<Produto> generateFakeProducts() {
        ArrayList<Produto> generatedList = new ArrayList<>();
        for (int i = 0; i < fakeProductsQuantity; i++) {
            String name = String.format("%s %d", category, (i + 1));
            String description = "Aqui ficara a descrição do produto, no caso : " + name;
            double price = (Math.random() * fakeProductsQuantity * 4);
            Produto product = new Produto(i, name, description, price, "teste", "teste");
            generatedList.add(product);
        }
        return generatedList;
    }

    public void setCategory(Constants.Category category){
        this.category = category;
    }

    @Override
    public void onSuccess(List<Produto> products) {
        adapter.refreshProducts(products);
    }

    @Override
    public void onEmptyList() {

    }

    @Override
    public void onError() {

    }
}
