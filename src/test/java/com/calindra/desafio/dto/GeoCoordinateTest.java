package com.calindra.desafio.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.calindra.desafio.constants.DesafioConstants.*;

import org.junit.jupiter.api.Test;

import com.calindra.desafio.builder.GeoCoordinateBuilder;

class GeoCoordinateTest {

	@Test
	void deveriaCalcularDistanciaEntreDuasCoordenadasQuandoCoordenadaInformada() {
		GeoCoordinate coordenadaMockPrimeioEndereco = new GeoCoordinateBuilder()
				.latitude(PRIMEIRO_ENDERECO_LATITUDE)
				.longitude(PRIMEIRO_ENDERECO_LONGITUDE).build();
		GeoCoordinate coordenadaMockSegundoEndereco = new GeoCoordinateBuilder()
				.latitude(SEGUNDO_ENDERECO_LATITUDE)
				.longitude(SEGUNDO_ENDERECO_LONGITUDE).build();
		
		Double distanceInKm = coordenadaMockPrimeioEndereco.getDistanceInKm(coordenadaMockSegundoEndereco);
		
		assertEquals(DISTANCIA_PRIMEIRO_SEGUNDO_ENDERECO, distanceInKm);
	}

}
