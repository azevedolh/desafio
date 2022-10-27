package com.calindra.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class GeoApiServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public GeoApiServiceException(String message) {
		super(message);
	}
}
