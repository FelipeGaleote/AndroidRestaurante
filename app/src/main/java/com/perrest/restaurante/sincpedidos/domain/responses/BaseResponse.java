package com.perrest.restaurante.sincpedidos.domain.responses;

public class BaseResponse {
	
	protected int statusCode;
	protected String statusMessage;
	
	public BaseResponse(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage= statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	

}
