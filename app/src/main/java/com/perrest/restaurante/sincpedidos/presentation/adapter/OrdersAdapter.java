package com.perrest.restaurante.sincpedidos.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.ItemPedido;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private List<ItemPedido> pedidos;
    private Context context;

    public OrdersAdapter(Context context) {
        this.context = context;
        pedidos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPedido itemPedido = pedidos.get(position);
        holder.pedidoNome.setText(itemPedido.getNomeProduto());
        if (itemPedido.getQuantidade() > 1) {
            holder.pedidoQuantidade.setText(String.format("%s unidades", itemPedido.getQuantidade()));
        } else {
            holder.pedidoQuantidade.setText(String.format("%s unidade", itemPedido.getQuantidade()));
        }
        holder.pedidoPrecoUnitario.setText(String.format("Preço unitário R$ %.2f", itemPedido.getPreco()));
        holder.pedidoPreco.setText(String.format("Total R$ %.2f", (itemPedido.getPreco() * itemPedido.getQuantidade())));
        Glide.with(context)
                .load(itemPedido.getFotoUrl())
                .into(holder.pedidoFoto);
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.pedidos = itensPedidos;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pedido_foto)
        ImageView pedidoFoto;
        @BindView(R.id.pedido_nome)
        TextView pedidoNome;
        @BindView(R.id.pedido_quantidade)
        TextView pedidoQuantidade;
        @BindView(R.id.pedido_preco)
        TextView pedidoPreco;
        @BindView(R.id.pedido_preco_unitario)
        TextView pedidoPrecoUnitario;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
