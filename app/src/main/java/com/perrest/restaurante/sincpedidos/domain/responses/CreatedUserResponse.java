package com.perrest.restaurante.sincpedidos.domain.responses;


public class CreatedUserResponse extends BaseResponse{
	
	private String id;
	public CreatedUserResponse(String statusMessage, String id) {
		super(202, statusMessage);
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
