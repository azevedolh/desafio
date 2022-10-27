package com.calindra.desafio.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.calindra.desafio.dto.GeoCoordinate;
import com.calindra.desafio.exception.CoordinateNotFoundException;
import com.calindra.desafio.exception.GeoApiServiceException;
import com.calindra.desafio.service.GeoApiService;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class GeoApiServiceImpl implements GeoApiService {

	private GeoApiContext context;

	public GeoApiServiceImpl(@Value("${desafio.geoapi.key}") String apiKey) {
		this.context = new GeoApiContext.Builder().apiKey(apiKey).build();
	}

	@Override
	public GeoCoordinate recuperarCoordenadas(String endereco)
			throws GeoApiServiceException, CoordinateNotFoundException {
		GeocodingResult[] response = null;

		try {
			response = GeocodingApi.geocode(context, endereco).await();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeoApiServiceException(e.getMessage());
		}

		if (response.length == 0) {
			throw new CoordinateNotFoundException(endereco);
		}

		LatLng location = response[0].geometry.location;
		
		return new GeoCoordinate(location.lat, location.lng);
	}

}
