package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Item;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ItemService {

    @POST("itens")
    Call<CreatedResponse> adicionarItem(@Body Item item);
}
