package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Produto;
import com.perrest.restaurante.sincpedidos.domain.responses.RetrievedProductsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {

    @GET("produto/lanches")
    Call<RetrievedProductsResponse> findAllBurgers();

    @GET("produto/pizzas")
    Call<RetrievedProductsResponse> findAllPizzas();

    @GET("produto/saladas")
    Call<RetrievedProductsResponse> findAllSalads();

    @GET("produto/petiscos")
    Call<RetrievedProductsResponse> findAllSnacks();

    @GET("produto/sobremesas")
    Call<RetrievedProductsResponse> findAllDesserts();

    @GET("produto/bebidas")
    Call<RetrievedProductsResponse> findAllDrinks();
}
