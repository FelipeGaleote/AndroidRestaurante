package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface MesaService {

    @GET("mesa")
    Call<List<Mesa>> retrieveAllTables();

    @PUT("mesa")
    Call<Void> updateTable(@Body Mesa mesa);
}
