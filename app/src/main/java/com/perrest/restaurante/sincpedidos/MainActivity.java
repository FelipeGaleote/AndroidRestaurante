package com.perrest.restaurante.sincpedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.perrest.restaurante.sincpedidos.model.Produto;
import com.perrest.restaurante.sincpedidos.network.ProdutoService;
import com.perrest.restaurante.sincpedidos.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProdutoService produtoService = RetrofitInstance.getRetrofitInstance().create(ProdutoService.class);

        Call<List<Produto>> call = produtoService.consultarTodosProdutos();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                List<Produto> lista = response.body();
                Toast.makeText(MainActivity.this, lista.get(0).getNome(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
