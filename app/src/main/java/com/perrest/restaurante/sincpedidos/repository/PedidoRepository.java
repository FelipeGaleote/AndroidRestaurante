package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.Pedido;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedOrderResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoRepository {

    public interface OrderListener {
        void onOrderCreated(long orderId);

        void onOrderCreateFailed();
    }

    private OrderListener listener;
    private PedidoService pedidoService;

    public PedidoRepository(OrderListener listener) {
        this.listener = listener;
        pedidoService = RetrofitInstance.getRetrofitInstance().create(PedidoService.class);
    }

    public void saveOrder(Pedido order) {
        Call<CreatedOrderResponse> call = pedidoService.criarPedido(order);
        call.enqueue(new Callback<CreatedOrderResponse>() {
            @Override
            public void onResponse(Call<CreatedOrderResponse> call, Response<CreatedOrderResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatusCode() == 201) {
                    if(response.body().getOrder() != null)
                        listener.onOrderCreated(response.body().getOrder().getId());
                } else {
                    listener.onOrderCreateFailed();
                }
            }

            @Override
            public void onFailure(Call<CreatedOrderResponse> call, Throwable t) {
                listener.onOrderCreateFailed();
            }
        });
    }
}
