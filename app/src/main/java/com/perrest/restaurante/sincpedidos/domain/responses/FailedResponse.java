package com.perrest.restaurante.sincpedidos.domain.responses;


public class FailedResponse extends BaseResponse{

	public FailedResponse(String statusMessage) {
		super(500, statusMessage);
	}

}
