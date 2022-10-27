package com.calindra.desafio.dto;

public class DistanciaEnderecoDto {

	private String enderecoOrigem;
	private String enderecoDestino;
	private Double distanciaEmKm;
	
	

	public DistanciaEnderecoDto(String enderecoOrigem, String enderecoDestino, Double distancia) {
		this.enderecoOrigem = enderecoOrigem;
		this.enderecoDestino = enderecoDestino;
		this.distanciaEmKm = distancia;
	}

	public String getEnderecoOrigem() {
		return enderecoOrigem;
	}

	public String getEnderecoDestino() {
		return enderecoDestino;
	}

	public Double getDistanciaEmKm() {
		return distanciaEmKm;
	}

}
