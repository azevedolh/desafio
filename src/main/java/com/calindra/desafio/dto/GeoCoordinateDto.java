package com.calindra.desafio.dto;

public class GeoCoordinateDto {
	private final int EARTH_RADIUS_KM = 6371;
	private Double latitude;
	private Double longitude;

	public GeoCoordinateDto(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getDistanceInKm(GeoCoordinateDto coordenadaTo) {
		double firstLatToRad = Math.toRadians(this.getLatitude());
		double secondLatToRad = Math.toRadians(coordenadaTo.getLatitude());

		double deltaLongitudeInRad = Math.toRadians(coordenadaTo.getLongitude() - this.getLongitude());

		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad) * Math.cos(deltaLongitudeInRad)
				+ Math.sin(firstLatToRad) * Math.sin(secondLatToRad)) * EARTH_RADIUS_KM;
	}

}
