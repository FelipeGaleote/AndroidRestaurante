package com.perrest.restaurante.sincpedidos.domain.responses;


public class UpdatedResponse extends BaseResponse{
	
	private long id;
	
	public UpdatedResponse(String statusMessage, long id) {
		super(200, statusMessage);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
