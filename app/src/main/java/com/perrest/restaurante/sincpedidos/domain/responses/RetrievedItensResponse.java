package com.perrest.restaurante.sincpedidos.domain.responses;


import com.perrest.restaurante.sincpedidos.domain.entity.Item;
import com.perrest.restaurante.sincpedidos.domain.entity.Produto;

import java.util.List;

public class RetrievedItensResponse extends BaseResponse{
	
	private List<Item> itens;
	private List<Produto> produtos;
	
	public RetrievedItensResponse(String messageStatus, List<Item> itens, List<Produto> produtos) {
		super(200, messageStatus);
		this.itens = itens;
		this.produtos = produtos;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
