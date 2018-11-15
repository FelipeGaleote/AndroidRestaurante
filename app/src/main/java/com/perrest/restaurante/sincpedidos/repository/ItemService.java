package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Item;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.RetrievedItensResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemService {

    @POST("itens")
    Call<CreatedResponse> adicionarItem(@Body Item item);

    @GET("itens/{id}")
    Call<RetrievedItensResponse> consultarItens(@Path("id") long id);
}
