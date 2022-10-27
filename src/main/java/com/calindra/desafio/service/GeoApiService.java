package com.calindra.desafio.service;

import com.calindra.desafio.dto.GeoCoordinate;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;

public interface GeoApiService {
	
	GeoCoordinate recuperarCoordenadas(String endereco) throws GeoApiServiceException, CoordinateNotFoundException;
}
