package com.calindra.desafio.service;

import com.calindra.desafio.dto.DistanciaEnderecoRequestDto;
import com.calindra.desafio.dto.DistanciaEnderecoResponseDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;

public interface EnderecoService {
	
	DistanciaEnderecoResponseDto calcularDistanciaEntreEnderecos(DistanciaEnderecoRequestDto requestDto) throws GeoApiServiceException, CoordinateNotFoundException;
}
