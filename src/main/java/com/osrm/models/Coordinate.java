package com.osrm.models;

public class Coordinate {
	
	private double latitude;
	private double longitude;
	
	public Coordinate(String lat, String lon) {
		this.latitude = Double.parseDouble(lat);
		this.longitude = Double.parseDouble(lon);
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
