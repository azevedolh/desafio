package com.calindra.desafio.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.calindra.desafio.constants.DesafioConstants.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.calindra.desafio.dto.GeoCoordinateDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class GeoApiServiceImplTest {
	
	@Test
	void deveriaRetornarCoordenadasParaOEnderecoInformado() throws GeoApiServiceException, CoordinateNotFoundException {
		GeoApiServiceImpl geoApiServiceImpl = new GeoApiServiceImpl(API_KEY);
		GeoCoordinateDto coordenadas = geoApiServiceImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO); 
		
		assertEquals(PRIMEIRO_ENDERECO_LATITUDE, coordenadas.getLatitude());
		assertEquals(PRIMEIRO_ENDERECO_LONGITUDE, coordenadas.getLongitude());
	}
	
	@Test
	void deveriaRetornarCoordinateNotFoundExceptionQuandoEnderecoNaoEncontrado() throws GeoApiServiceException, CoordinateNotFoundException {
		GeoApiServiceImpl geoApiServiceImpl = new GeoApiServiceImpl(API_KEY);
		assertThrows(CoordinateNotFoundException.class, () -> geoApiServiceImpl.recuperarCoordenadas(ENDERECO_INVALIDO));
	}
	
	@Test
	void deveriaRetornarGeoApiServiceExceptionQuandoEnderecoNaoEncontrado() throws GeoApiServiceException, CoordinateNotFoundException {
		GeoApiServiceImpl geoApiServiceImpl = new GeoApiServiceImpl(API_KEY_INVALIDA);
		assertThrows(GeoApiServiceException.class, () -> geoApiServiceImpl.recuperarCoordenadas(PRIMEIRO_ENDERECO));
	}
	
	

}
