package com.calindra.desafio.service;

import com.calindra.desafio.dto.GeoCoordinateDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;

public interface GeoApiService {
	
	GeoCoordinateDto recuperarCoordenadas(String endereco) throws GeoApiServiceException, CoordinateNotFoundException;
}
