package com.perrest.restaurante.sincpedidos.domain.responses;


import com.perrest.restaurante.sincpedidos.domain.entity.Pedido;

public class RetrievedOrderResponse extends BaseResponse{
	
	private Pedido pedido;
	
	public RetrievedOrderResponse(String messageStatus, Pedido pedido) {
		super(202, messageStatus);
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
