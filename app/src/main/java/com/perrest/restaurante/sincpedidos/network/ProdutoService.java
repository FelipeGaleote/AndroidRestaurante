package com.perrest.restaurante.sincpedidos.network;

import com.perrest.restaurante.sincpedidos.model.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {

    @GET("produtos/todosprodutos")
    Call<List<Produto>> consultarTodosProdutos();
}
