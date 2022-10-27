package com.calindra.desafio.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calindra.desafio.dto.GeoCoordinateDto;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;
import com.calindra.desafio.service.GeoApiProxy;
import com.calindra.desafio.service.GeoApiService;

@Service
public class GeoApiProxyImpl implements GeoApiProxy {

	private Map<String, GeoCoordinateDto> coordenadasEnderecos = new HashMap<String, GeoCoordinateDto>();

	@Autowired
	private GeoApiService geoApiServiceImpl;

	@Override
	public GeoCoordinateDto recuperarCoordenadas(String endereco)
			throws GeoApiServiceException, CoordinateNotFoundException {
		if (coordenadasEnderecos.containsKey(endereco)) {
			return coordenadasEnderecos.get(endereco);
		}

		GeoCoordinateDto coordenada = geoApiServiceImpl.recuperarCoordenadas(endereco);
		coordenadasEnderecos.put(endereco, coordenada);
		return coordenada;

	}
	
	public void limparCache() {
		coordenadasEnderecos.clear();
	}

}
