package com.calindra.desafio.dto;

import static com.calindra.desafio.constants.DesafioConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.calindra.desafio.builder.DistanciaEnderecoDtoBuilder;

class DistanciaEnderecoResponseDtoTest {

	@Test
	void deveriaAdicionarDistanciaNaListaESetarMaiorEMenorDistancias() {
		DistanciaEnderecoDto distancia1 = new DistanciaEnderecoDtoBuilder()
				.enderecoOrigem(PRIMEIRO_ENDERECO)
				.enderecoDestino(SEGUNDO_ENDERECO)
				.distanciaEmKm(DISTANCIA_PRIMEIRO_SEGUNDO_ENDERECO)
				.build();
		
		DistanciaEnderecoDto distancia2 = new DistanciaEnderecoDtoBuilder()
				.enderecoOrigem(PRIMEIRO_ENDERECO)
				.enderecoDestino(TERCEIRO_ENDERECO)
				.distanciaEmKm(DISTANCIA_PRIMEIRO_TERCEIRO_ENDERECO)
				.build();
		
		DistanciaEnderecoDto distancia3 = new DistanciaEnderecoDtoBuilder()
				.enderecoOrigem(SEGUNDO_ENDERECO)
				.enderecoDestino(TERCEIRO_ENDERECO)
				.distanciaEmKm(DISTANCIA_SEGUNDO_TERCEIRO_ENDERECO)
				.build();
		
		DistanciaEnderecoResponseDto distanciaEnderecoResponseDto = new DistanciaEnderecoResponseDto();
		distanciaEnderecoResponseDto.addDistanciaEnderecos(distancia1);
		distanciaEnderecoResponseDto.addDistanciaEnderecos(distancia2);
		distanciaEnderecoResponseDto.addDistanciaEnderecos(distancia3);
		
		assertEquals(3, distanciaEnderecoResponseDto.getDistanciaEnderecos().size());
		assertEquals(DISTANCIA_PRIMEIRO_TERCEIRO_ENDERECO, distanciaEnderecoResponseDto.getMaiorDistancia().getDistanciaEmKm());
		assertEquals(DISTANCIA_PRIMEIRO_SEGUNDO_ENDERECO, distanciaEnderecoResponseDto.getMenorDistancia().getDistanciaEmKm());
	}

}
