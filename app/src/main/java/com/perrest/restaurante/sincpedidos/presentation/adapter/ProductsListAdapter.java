package com.perrest.restaurante.sincpedidos.presentation.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Produto;
import com.perrest.restaurante.sincpedidos.presentation.fragment.AddProductDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListAdapter extends RecyclerView.Adapter implements AddProductDialogFragment.OnClickListener, Serializable {

    private ArrayList<Produto> products;
    private Context context;
    private FragmentManager fragmentManager;

    public ProductsListAdapter(Context context, ArrayList<Produto> products, FragmentManager fragmentManager) {
        this.context = context;
        this.products = products != null ? products : new ArrayList<>();
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Produto product = products.get(position);
        viewHolder.productName.setText(product.getNome());
        viewHolder.productDescription.setText(product.getDescricao());
        viewHolder.productPrice.setText(String.format("R$ %.2f", product.getValor()));
        viewHolder.productBuy.setOnClickListener(v -> {
            AddProductDialogFragment dialog = new AddProductDialogFragment();
            dialog.setListener(this);
            dialog.setProduto(product);
            dialog.show(fragmentManager, "AddProductDialogFragment");
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onConfirmClick(String quantity, Produto produto) {
        Toast.makeText(context, "Você acaba de pedir " + quantity + " produtos!", Toast.LENGTH_LONG).show();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productPhoto;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_description)
        TextView productDescription;
        @BindView(R.id.product_price)
        TextView productPrice;
        @BindView(R.id.product_buy)
        ImageView productBuy;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
