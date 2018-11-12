package com.perrest.restaurante.sincpedidos.domain.responses;


import com.perrest.restaurante.sincpedidos.domain.entity.Mesa;

import java.util.List;

public class RetrievedTablesResponse extends BaseResponse{
	
	private List<Mesa> tables;

	public RetrievedTablesResponse(String statusMessage, List<Mesa> tables) {
		super(200, statusMessage);
		this.tables = tables;
	}

	public List<Mesa> getTables() {
		return tables;
	}

	public void setTables(List<Mesa> tables) {
		this.tables = tables;
	}
	
}
