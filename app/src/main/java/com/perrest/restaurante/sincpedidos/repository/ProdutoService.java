package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {

    @GET("produto/lanches")
    Call<List<Produto>> findAllBurgers();

    @GET("produto/pizzas")
    Call<List<Produto>> findAllPizzas();

    @GET("produto/saladas")
    Call<List<Produto>> findAllSalads();

    @GET("produto/petiscos")
    Call<List<Produto>> findAllSnacks();

    @GET("produto/sobremesas")
    Call<List<Produto>> findAllDesserts();

    @GET("produto/bebidas")
    Call<List<Produto>> findAllDrinks();
}
