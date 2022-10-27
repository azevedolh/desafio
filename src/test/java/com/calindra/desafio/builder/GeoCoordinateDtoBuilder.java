package com.calindra.desafio.builder;

import com.calindra.desafio.dto.GeoCoordinateDto;

public class GeoCoordinateDtoBuilder {

	private Double latitude;
	private Double longitude;

	public GeoCoordinateDtoBuilder latitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public GeoCoordinateDtoBuilder longitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public GeoCoordinateDto build() {
		return new GeoCoordinateDto(this.latitude, this.longitude);
	}
}
