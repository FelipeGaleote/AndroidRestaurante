package com.perrest.restaurante.sincpedidos.domain.responses;


import com.perrest.restaurante.sincpedidos.domain.entity.Pedido;

public class CreatedOrderResponse extends BaseResponse{
	
	private Pedido order;

	public CreatedOrderResponse(String messageStatus, Pedido order) {
		super(201, messageStatus);
		this.order = order;
	}

	public Pedido getOrder() {
		return order;
	}

	public void setOrder(Pedido order) {
		this.order = order;
	}
	
	
}
