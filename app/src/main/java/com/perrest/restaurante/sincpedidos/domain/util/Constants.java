package com.perrest.restaurante.sincpedidos.domain.util;

import android.content.Context;

import com.perrest.restaurante.sincpedidos.R;

public class Constants {

    public enum Category {
        PIZZAS, BURGERS, SALADS, SNACKS, DESSERTS, DRINKS;

        public String toString(Context context){
            switch (this){
                case BURGERS:
                    return context.getString(R.string.burgers);
                case PIZZAS:
                    return context.getString(R.string.pizza);
                case SALADS:
                    return context.getString(R.string.salads);
                case SNACKS:
                    return context.getString(R.string.snacks);
                case DESSERTS:
                    return context.getString(R.string.desserts);
                case DRINKS:
                    return context.getString(R.string.drinks);
                default:
                    return "Produtos";
            }
        }
    }
}
