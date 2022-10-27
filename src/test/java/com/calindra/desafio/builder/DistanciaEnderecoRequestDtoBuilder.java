package com.calindra.desafio.builder;

import java.util.ArrayList;
import java.util.List;

import com.calindra.desafio.dto.DistanciaEnderecoRequestDto;

public class DistanciaEnderecoRequestDtoBuilder {

	private List<String> enderecos = new ArrayList<String>();

	public DistanciaEnderecoRequestDtoBuilder addEndereco(String endereco) {
		enderecos.add(endereco);
		return this;
	}
	
	public DistanciaEnderecoRequestDto build() {
		DistanciaEnderecoRequestDto dto = new DistanciaEnderecoRequestDto();
		dto.setEnderecos(this.enderecos);
		return dto;
	}
}
