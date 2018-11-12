package com.perrest.restaurante.sincpedidos.repository;


import com.perrest.restaurante.sincpedidos.domain.entity.Item;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {

    public interface ItemListener {
        void onItemAdded(String itemName, int itemQuantity);

        void onItemAddFailed();
    }

    private ItemListener listener;
    private ItemService itemService;

    public ItemRepository(ItemListener listener) {
        this.listener = listener;
        itemService = RetrofitInstance.getRetrofitInstance().create(ItemService.class);
    }

    public void addItem(Item item) {
        Call<CreatedResponse> call = itemService.adicionarItem(item);
        call.enqueue(new Callback<CreatedResponse>() {
            @Override
            public void onResponse(Call<CreatedResponse> call, Response<CreatedResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatusCode() == 201) {
                    listener.onItemAdded(item.getNomeProduto(), item.getQuantidade());
                } else {
                    listener.onItemAddFailed();
                }
            }

            @Override
            public void onFailure(Call<CreatedResponse> call, Throwable t) {
                listener.onItemAddFailed();
            }
        });
    }
}
