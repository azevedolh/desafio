package com.calindra.desafio.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DistanciaEnderecoRequestDto {

	@Size(min = 3, message = "{enderecos.min.itens}")
	private List<@NotBlank(message = "{enderecos.item.nao.preenchido}") String> enderecos;

	public List<String> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<String> enderecos) {
		this.enderecos = enderecos;
	}
	
}
