package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;
import com.perrest.restaurante.sincpedidos.domain.responses.RetrievedTablesResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.UpdatedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface MesaService {

    @GET("mesa")
    Call<RetrievedTablesResponse> retrieveAllTables();

    @PUT("mesa")
    Call<UpdatedResponse> updateTable(@Body Mesa mesa);
}
