package com.calindra.desafio.builder;

import com.calindra.desafio.dto.DistanciaEnderecoDto;

public class DistanciaEnderecoDtoBuilder {

	private String enderecoOrigem;
	private String enderecoDestino;
	private Double distanciaEmKm;

	public DistanciaEnderecoDtoBuilder enderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
		return this;
	}
	
	public DistanciaEnderecoDtoBuilder enderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
		return this;
	}
	
	public DistanciaEnderecoDtoBuilder distanciaEmKm(Double distanciaEmKm) {
		this.distanciaEmKm = distanciaEmKm;
		return this;
	}
	
	public DistanciaEnderecoDto build() {
		return new DistanciaEnderecoDto(this.enderecoOrigem, this.enderecoDestino, this.distanciaEmKm);
	}
}
