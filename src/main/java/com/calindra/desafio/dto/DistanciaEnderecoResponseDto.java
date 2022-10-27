package com.calindra.desafio.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DistanciaEnderecoResponseDto {

	private List<DistanciaEnderecoDto> distanciaEnderecos = new ArrayList<DistanciaEnderecoDto>();
	private DistanciaEnderecoDto maiorDistancia;
	private DistanciaEnderecoDto menorDistancia;

	public void addDistanciaEnderecos(DistanciaEnderecoDto distanciaEndereco) {
		this.distanciaEnderecos.add(distanciaEndereco);
		setMaiorAndMenorDistancias();
	}

	private void setMaiorAndMenorDistancias() {
		List<DistanciaEnderecoDto> listaOrdenacao = new ArrayList<DistanciaEnderecoDto>(this.distanciaEnderecos);
		listaOrdenacao.sort(Comparator.comparing(d -> d.getDistanciaEmKm()));
		this.menorDistancia = listaOrdenacao.get(0);
		this.maiorDistancia = listaOrdenacao.get(this.distanciaEnderecos.size() - 1);
	}

	public List<DistanciaEnderecoDto> getDistanciaEnderecos() {
		return distanciaEnderecos;
	}

	public DistanciaEnderecoDto getMaiorDistancia() {
		return maiorDistancia;
	}

	public DistanciaEnderecoDto getMenorDistancia() {
		return menorDistancia;
	}

}
