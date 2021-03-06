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

import com.bumptech.glide.Glide;
import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Item;
import com.perrest.restaurante.sincpedidos.domain.entity.Produto;
import com.perrest.restaurante.sincpedidos.presentation.fragment.AddProductDialogFragment;
import com.perrest.restaurante.sincpedidos.repository.ItemRepository;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListAdapter extends RecyclerView.Adapter implements AddProductDialogFragment.OnClickListener, Serializable, ItemRepository.ItemListener {

    private List<Produto> products;
    private Context context;
    private FragmentManager fragmentManager;
    private ItemRepository itemRepository;

    public ProductsListAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.products = new ArrayList<>();
        this.itemRepository = new ItemRepository(this);
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
        Glide.with(context)
                .load(product.getUrlFoto())
                .into(viewHolder.productPhoto);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public void onConfirmClick(String quantity, Produto produto) {
        Item item = new Item();
        item.setIdPedido(SharedPrefsUtil.getOrderId(context));
        item.setIdProduto(produto.getId());
        item.setNomeProduto(produto.getNome());
        item.setQuantidade(Integer.parseInt(quantity));
        itemRepository.addItem(item);
    }

    public void refreshProducts(List<Produto> products){
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public void onItemAdded(String itemName, int itemQuantity) {
        Toast.makeText(context, String.format("Você acaba de pedir %s %s", itemQuantity ,itemName), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemAddFailed() {

    }

    @Override
    public void onItensRetrieved(List<Item> itens, List<Produto> produtos) {

    }

    @Override
    public void onItensRetrieveFailed() {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_photo)
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
