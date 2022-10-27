package com.calindra.desafio.config.exception;

public class ValidationHandlerDto {
	private String campo;
	private String mensagem;

	public ValidationHandlerDto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
}
