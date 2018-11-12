package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Produto;
import com.perrest.restaurante.sincpedidos.domain.responses.RetrievedProductsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    public interface ProductsLoadListener {
        void onSuccess(List<Produto> products);

        void onEmptyList();

        void onError();
    }

    private ProdutoService produtoService;
    private ProductsLoadListener listener;

    public ProductRepository(ProductsLoadListener listener) {
        produtoService = RetrofitInstance.getRetrofitInstance().create(ProdutoService.class);
        this.listener = listener;
    }

    public void findAllBurgers() {
        enqueueGetCall(produtoService.findAllBurgers());
    }

    public void findAllPizzas() {
        enqueueGetCall(produtoService.findAllPizzas());
    }

    public void findAllSalads() {
        enqueueGetCall(produtoService.findAllSalads());
    }

    public void findAllSnacks() {
        enqueueGetCall(produtoService.findAllSnacks());
    }

    public void findAllDesserts() {
        enqueueGetCall(produtoService.findAllDesserts());
    }

    public void findAllDrinks() {
        enqueueGetCall(produtoService.findAllDrinks());
    }

    private void enqueueGetCall(Call<RetrievedProductsResponse> call) {
        call.enqueue(new Callback<RetrievedProductsResponse>() {
            @Override
            public void onResponse(Call<RetrievedProductsResponse> call, Response<RetrievedProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatusCode() == 200) {
                    listener.onSuccess(response.body().getProducts());
                } else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<RetrievedProductsResponse> call, Throwable t) {
                listener.onError();
            }
        });
    }
}
