package com.perrest.restaurante.sincpedidos.repository;

import android.content.Context;

import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableRepository {

    private TableListener listener;
    private MesaService service;
    private Context context;

    public interface TableListener {
        void onLoadSuccess(List<Mesa> tables);

        void onLoadFailed();

        void onTableTakeSuccess();

        void onTableTakeFailed();
    }

    public TableRepository(TableListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        service = RetrofitInstance.getRetrofitInstance().create(MesaService.class);
    }

    public void loadAllTables() {
        Call<List<Mesa>> call = service.retrieveAllTables();
        call.enqueue(new Callback<List<Mesa>>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                if (response.isSuccessful()) {
                    listener.onLoadSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                listener.onLoadFailed();
            }
        });
    }

    public void takeTable(int tableId) {
        Mesa mesa = new Mesa();
        mesa.setId(tableId);
        mesa.setStatus("Ocupada");
        Call<Void> call = service.updateTable(mesa);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                SharedPrefsUtil.saveSelectedTable(context, tableId);
                listener.onTableTakeSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onTableTakeFailed();
            }
        });
    }


}
