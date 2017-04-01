package com.osrm.models;

public class StoreDistance {

	private String storeId;
	private double distance;

	public StoreDistance(String id, double dist) {
		this.storeId = id;
		this.distance = dist;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
