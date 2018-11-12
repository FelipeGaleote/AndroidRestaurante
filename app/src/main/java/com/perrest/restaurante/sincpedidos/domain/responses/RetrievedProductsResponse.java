package com.perrest.restaurante.sincpedidos.domain.responses;


import com.perrest.restaurante.sincpedidos.domain.entity.Produto;

import java.util.List;

public class RetrievedProductsResponse extends BaseResponse{
	
	private List<Produto> products;
	
	public RetrievedProductsResponse(String statusMessage, List<Produto> products) {
		super(200, statusMessage);
		this.products = products;
	}

	public List<Produto> getProducts() {
		return products;
	}

	public void setProducts(List<Produto> products) {
		this.products = products;
	}
	
}
