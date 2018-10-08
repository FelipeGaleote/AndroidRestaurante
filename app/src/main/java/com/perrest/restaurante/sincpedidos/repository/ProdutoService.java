package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {

    @GET("produtos/todosprodutos")
    Call<List<Produto>> consultarTodosProdutos();
}
