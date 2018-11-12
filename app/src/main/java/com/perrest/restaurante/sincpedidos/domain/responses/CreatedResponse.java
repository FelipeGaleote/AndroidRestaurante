package com.perrest.restaurante.sincpedidos.domain.responses;

public class CreatedResponse extends BaseResponse {
	
	private long id;

	public CreatedResponse(String statusMessage, long id) {
		super(201, statusMessage);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
