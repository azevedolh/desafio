package com.calindra.desafio.builder;

import com.calindra.desafio.dto.GeoCoordinate;

public class GeoCoordinateBuilder {

	private Double latitude;
	private Double longitude;

	public GeoCoordinateBuilder latitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public GeoCoordinateBuilder longitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public GeoCoordinate build() {
		return new GeoCoordinate(this.latitude, this.longitude);
	}
}
