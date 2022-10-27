package com.calindra.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CoordinateNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	public CoordinateNotFoundException(String endereco) {
		super("Endereco \"" + endereco + "\" nao localizado." );
	}
}
