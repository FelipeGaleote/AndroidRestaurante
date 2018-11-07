package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableRepository {

    private TableLoadListener listener;

    public interface TableLoadListener {
        void onSuccess(List<Mesa> tables);

        void onFailed();
    }

    public TableRepository(TableLoadListener listener) {
        this.listener = listener;
    }

    public void loadAllTables() {
        MesaService service = RetrofitInstance.getRetrofitInstance().create(MesaService.class);
        Call<List<Mesa>> call = service.retrieveAllTables();
        call.enqueue(new Callback<List<Mesa>>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                listener.onFailed();
            }
        });
    }


}
