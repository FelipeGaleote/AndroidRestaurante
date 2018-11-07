package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MesaService {

    @GET("mesa")
    Call<List<Mesa>> retrieveAllTables();
}
