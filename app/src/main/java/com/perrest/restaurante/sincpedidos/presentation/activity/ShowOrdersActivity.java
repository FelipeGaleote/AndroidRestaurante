package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.Item;
import com.perrest.restaurante.sincpedidos.domain.entity.ItemPedido;
import com.perrest.restaurante.sincpedidos.domain.entity.Produto;
import com.perrest.restaurante.sincpedidos.presentation.adapter.OrdersAdapter;
import com.perrest.restaurante.sincpedidos.repository.ItemRepository;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowOrdersActivity extends AppCompatActivity implements ItemRepository.ItemListener {

    @BindView(R.id.show_orders_recycler_view)
    RecyclerView recyclerView;
    private OrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);
        ItemRepository repository = new ItemRepository(this);
        repository.retrieveItensByOrderId(SharedPrefsUtil.getOrderId(this));

        ButterKnife.bind(this);

        adapter = new OrdersAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemAdded(String itemName, int itemQuantity) {

    }

    @Override
    public void onItemAddFailed() {

    }

    @Override
    public void onItensRetrieved(List<Item> itens, List<Produto> produtos) {
        List<ItemPedido> itensPedidos = new ArrayList<>();
        for(Item item : itens) {
            for(Produto produto: produtos) {
                if(item.getIdProduto() == produto.getId()) {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setFotoUrl(produto.getUrlFoto());
                    itemPedido.setNomeProduto(produto.getNome());
                    itemPedido.setQuantidade(item.getQuantidade());
                    itemPedido.setPreco(produto.getValor());
                    itensPedidos.add(itemPedido);
                }
            }
        }
        adapter.setItensPedidos(itensPedidos);
    }

    @Override
    public void onItensRetrieveFailed() {

    }
}
