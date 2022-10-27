package com.calindra.desafio.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.calindra.desafio.constants.DesafioConstants.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.calindra.desafio.builder.GeoCoordinateDtoBuilder;
import com.calindra.desafio.dto.GeoCoordinateDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;
import com.calindra.desafio.service.GeoApiService;

@ExtendWith(MockitoExtension.class)
class GeoApiProxyImplTest {
	
	@Mock
	GeoApiService geoApiServiceImpl;
	
	@InjectMocks
	GeoApiProxyImpl geoApiProxyImpl; 
	
	@AfterEach
	void limparCache() { 
		geoApiProxyImpl.limparCache();
	}

	@Test
	void deveriaAcessarServicoQuandoCoordenadaDoEnderecoNaoExisteEmMemoria() throws GeoApiServiceException, CoordinateNotFoundException {
		GeoCoordinateDto coordenadaMock = new GeoCoordinateDtoBuilder()
				.latitude(PRIMEIRO_ENDERECO_LATITUDE)
				.longitude(PRIMEIRO_ENDERECO_LONGITUDE)
				.build();
		
		when(geoApiServiceImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO)).thenReturn(coordenadaMock);
		
		GeoCoordinateDto coordenada = geoApiProxyImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO);
		
		assertEquals(coordenadaMock.getLatitude(), coordenada.getLatitude());
		assertEquals(coordenadaMock.getLongitude(), coordenada.getLongitude());
		verify(geoApiServiceImpl, times(1)).recuperarCoordenadas(PRIMEIRO_ENDERECO);
	}
	
	@Test
	void deveriaRecuperarDaMemoriaQuandoEnderecoJaConsultadoAntes() throws GeoApiServiceException, CoordinateNotFoundException {
		GeoCoordinateDto coordenadaMock = new GeoCoordinateDtoBuilder()
				.latitude(PRIMEIRO_ENDERECO_LATITUDE)
				.longitude(PRIMEIRO_ENDERECO_LONGITUDE)
				.build();
		
		when(geoApiServiceImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO)).thenReturn(coordenadaMock);
		
		geoApiProxyImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO);
		
		GeoCoordinateDto coordenadaSegundaChamada = geoApiProxyImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO);
		assertEquals(coordenadaMock.getLatitude(), coordenadaSegundaChamada.getLatitude());
		assertEquals(coordenadaMock.getLongitude(), coordenadaSegundaChamada.getLongitude());
		verify(geoApiServiceImpl, times(1)).recuperarCoordenadas(PRIMEIRO_ENDERECO);
	}
}
