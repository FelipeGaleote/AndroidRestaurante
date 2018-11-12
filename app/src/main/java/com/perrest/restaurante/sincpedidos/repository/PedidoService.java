package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Pedido;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PedidoService {

    @POST("pedido")
    Call<CreatedOrderResponse> criarPedido(@Body Pedido pedido);
}
