package com.perrest.restaurante.sincpedidos.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    public interface OnCategorySelectedListener {
        void onCategorySelected(Constants.Category category);
    }

    @BindView(R.id.categories_burgers)
    CardView burgersButton;
    @BindView(R.id.categories_pizzas)
    CardView pizzasButton;
    @BindView(R.id.categories_salads)
    CardView saladsButton;
    @BindView(R.id.categories_snacks)
    CardView snacksButton;
    @BindView(R.id.categories_desserts)
    CardView dessertsButton;
    @BindView(R.id.categories_drinks)
    CardView drinknsButton;

    private OnCategorySelectedListener listener;

    public CategoriesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.categories_burgers, R.id.categories_pizzas,R.id.categories_salads,
            R.id.categories_snacks, R.id.categories_desserts, R.id.categories_drinks})
    protected void goToProductsList(View v){
        Constants.Category category;
        switch (v.getId()){
            case R.id.categories_burgers:
                category = Constants.Category.BURGERS;
                break;
            case R.id.categories_pizzas:
                category = Constants.Category.PIZZA;
                break;
            case R.id.categories_salads:
                category = Constants.Category.SALADS;
                break;
            case R.id.categories_snacks:
                category = Constants.Category.SNACKS;
                break;
            case R.id.categories_desserts:
                category = Constants.Category.DESSERTS;
                break;
            case R.id.categories_drinks:
                category = Constants.Category.DRINKS;
                break;
            default:
                category = Constants.Category.BURGERS;
        }
        listener.onCategorySelected(category);
    }

    public void setOnCategorySelectedListener(OnCategorySelectedListener listener){
        this.listener = listener;
    }
}
