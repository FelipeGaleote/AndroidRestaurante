package com.perrest.restaurante.sincpedidos.repository;

import android.content.Context;

import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;
import com.perrest.restaurante.sincpedidos.domain.responses.RetrievedTablesResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.UpdatedResponse;
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

        void onTableTakeSuccess(long tableId);

        void onTableTakeFailed();
    }

    public TableRepository(TableListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        service = RetrofitInstance.getRetrofitInstance().create(MesaService.class);
    }

    public void loadAllTables() {
        Call<RetrievedTablesResponse> call = service.retrieveAllTables();
        call.enqueue(new Callback<RetrievedTablesResponse>() {
            @Override
            public void onResponse(Call<RetrievedTablesResponse> call, Response<RetrievedTablesResponse> response) {
                if (response.isSuccessful() && response.body().getStatusCode() == 200) {
                    listener.onLoadSuccess(response.body().getTables());
                } else {
                    listener.onLoadFailed();
                }
            }

            @Override
            public void onFailure(Call<RetrievedTablesResponse> call, Throwable t) {
                listener.onLoadFailed();
            }
        });
    }

    public void takeTable(int tableId) {
        Mesa mesa = new Mesa();
        mesa.setId(tableId);
        mesa.setStatus("Ocupada");
        Call<UpdatedResponse> call = service.updateTable(mesa);
        call.enqueue(new Callback<UpdatedResponse>() {
            @Override
            public void onResponse(Call<UpdatedResponse> call, Response<UpdatedResponse> response) {
                if(response.isSuccessful() && response.body().getStatusCode() == 200) {
                    SharedPrefsUtil.saveSelectedTable(context, tableId);
                    listener.onTableTakeSuccess(tableId);
                } else {
                    listener.onTableTakeFailed();
                }

            }

            @Override
            public void onFailure(Call<UpdatedResponse> call, Throwable t) {
                listener.onTableTakeFailed();
            }
        });
    }


}
