package com.perrest.restaurante.sincpedidos.domain.responses;


public class UserLoggedResponse extends BaseResponse{
	
	private String userId;
	
	public UserLoggedResponse (String statusMessage, String userId) {
		super(202, statusMessage);
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
