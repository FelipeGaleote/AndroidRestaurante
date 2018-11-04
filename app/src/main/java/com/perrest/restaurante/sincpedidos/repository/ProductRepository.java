package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Produto;

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

    public ProductRepository(ProductsLoadListener listener){
       produtoService = RetrofitInstance.getRetrofitInstance().create(ProdutoService.class);
       this.listener = listener;
    }

    public void findAllBurgers(){
        enqueueGetCall(produtoService.findAllBurgers());
    }
    public void findAllPizzas(){
        enqueueGetCall(produtoService.findAllPizzas());
    }
    public void findAllSalads(){
        enqueueGetCall(produtoService.findAllSalads());
    }
    public void findAllSnacks(){
        enqueueGetCall(produtoService.findAllSnacks());
    }
    public void findAllDesserts(){
        enqueueGetCall(produtoService.findAllDesserts());
    }
    public void findAllDrinks(){
        enqueueGetCall(produtoService.findAllDrinks());
    }

    private void enqueueGetCall(Call<List<Produto>> call){
        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null)
                        listener.onSuccess(response.body());
                    else
                        listener.onEmptyList();
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                listener.onError();
            }
        });
    }
}
