package com.perrest.restaurante.sincpedidos.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Produto;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListAdapter extends RecyclerView.Adapter {

    private ArrayList<Produto> products;
    private Context context;

    public ProductsListAdapter(Context context, ArrayList<Produto> products) {
        this.context = context;
        this.products = products != null ? products : new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Produto product = products.get(position);
        viewHolder.productName.setText(product.getNome());
        viewHolder.productDescription.setText(product.getDescricao());
        viewHolder.productPrice.setText(String.format("R$ %.2f",product.getValor()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productPhoto;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_description)
        TextView productDescription;
        @BindView(R.id.product_price)
        TextView productPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
