package com.calindra.desafio.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static com.calindra.desafio.constants.DesafioConstants.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.calindra.desafio.builder.DistanciaEnderecoRequestDtoBuilder;
import com.calindra.desafio.builder.GeoCoordinateBuilder;
import com.calindra.desafio.dto.DistanciaEnderecoRequestDto;
import com.calindra.desafio.dto.DistanciaEnderecoResponseDto;
import com.calindra.desafio.dto.GeoCoordinate;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;
import com.calindra.desafio.service.GeoApiProxy;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceImplTest {

	@Mock
	GeoApiProxy geoApiProxyImpl;

	@InjectMocks
	EnderecoServiceImpl enderecoServiceImpl;
	
	DistanciaEnderecoRequestDto requestDtoMock;
	
	@Test
	void deveriaRetornarListaDeDistanciasQuandoEnderecosInformados()
			throws GeoApiServiceException, CoordinateNotFoundException {
		
		requestDtoMock = new DistanciaEnderecoRequestDtoBuilder()
				.addEndereco(PRIMEIRO_ENDERECO)
				.addEndereco(SEGUNDO_ENDERECO)
				.addEndereco(TERCEIRO_ENDERECO)
				.build();

		GeoCoordinate coordenadaMockPrimeioEndereco = new GeoCoordinateBuilder()
				.latitude(PRIMEIRO_ENDERECO_LATITUDE)
				.longitude(PRIMEIRO_ENDERECO_LONGITUDE).build();
		GeoCoordinate coordenadaMockSegundoEndereco = new GeoCoordinateBuilder()
				.latitude(SEGUNDO_ENDERECO_LATITUDE)
				.longitude(SEGUNDO_ENDERECO_LONGITUDE).build();
		GeoCoordinate coordenadaMockTerceiroEndereco = new GeoCoordinateBuilder()
				.latitude(TERCEIRO_ENDERECO_LATITUDE)
				.longitude(TERCEIRO_ENDERECO_LONGITUDE).build();

		when(geoApiProxyImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO)).thenReturn(coordenadaMockPrimeioEndereco);
		when(geoApiProxyImpl.recuperarCoordenadas(SEGUNDO_ENDERECO)).thenReturn(coordenadaMockSegundoEndereco);
		when(geoApiProxyImpl.recuperarCoordenadas(TERCEIRO_ENDERECO)).thenReturn(coordenadaMockTerceiroEndereco);
		
		DistanciaEnderecoResponseDto distanciaEnderecos = enderecoServiceImpl
				.calcularDistanciaEntreEnderecos(requestDtoMock);

		assertEquals(3, distanciaEnderecos.getDistanciaEnderecos().size());

		assertEquals(PRIMEIRO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(0).getEnderecoOrigem());
		assertEquals(SEGUNDO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(0).getEnderecoDestino());
		assertEquals(DISTANCIA_PRIMEIRO_SEGUNDO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(0).getDistanciaEmKm());

		assertEquals(PRIMEIRO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(1).getEnderecoOrigem());
		assertEquals(TERCEIRO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(1).getEnderecoDestino());
		assertEquals(DISTANCIA_PRIMEIRO_TERCEIRO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(1).getDistanciaEmKm());

		assertEquals(SEGUNDO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(2).getEnderecoOrigem());
		assertEquals(TERCEIRO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(2).getEnderecoDestino());
		assertEquals(DISTANCIA_SEGUNDO_TERCEIRO_ENDERECO, distanciaEnderecos.getDistanciaEnderecos().get(2).getDistanciaEmKm());

		assertEquals(DISTANCIA_PRIMEIRO_TERCEIRO_ENDERECO, distanciaEnderecos.getMaiorDistancia().getDistanciaEmKm());
		assertEquals(DISTANCIA_PRIMEIRO_SEGUNDO_ENDERECO, distanciaEnderecos.getMenorDistancia().getDistanciaEmKm());

	}
	
}
