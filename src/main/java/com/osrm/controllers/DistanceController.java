package com.osrm.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osrm.models.Coordinate;
import com.osrm.models.StoreDistance;
import com.osrm.client.*;

@RestController
@RequestMapping("/distance")
public class DistanceController {

	private OsrmWrapper osrmWrapper;

	public DistanceController() {
		osrmWrapper = new OsrmWrapper();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<StoreDistance> GetDistance(@RequestParam(value = "userCoordinate") String userCoordinate,
			@RequestParam(value = "storeCoordinates") String storeCoordinates) {

		List<StoreDistance> storeDistances = null;
		try {
			HashMap<String, Coordinate> stores = extractStoreCoordinates(storeCoordinates);
			Coordinate userLocation = extractUserCoordinates(userCoordinate);
			storeDistances = osrmWrapper.getDistanceForAllStores(userLocation, stores);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return storeDistances;
	}

	private HashMap<String, Coordinate> extractStoreCoordinates(String coordinateString) {

		HashMap<String, Coordinate> storeCoordinates = new HashMap<String, Coordinate>();

		String[] locations = coordinateString.split(";");
		for (String location : locations) {
			String[] coordinates = location.split(",");
			storeCoordinates.put(coordinates[0], new Coordinate(coordinates[1], coordinates[2]));
		}
		return storeCoordinates;
	}

	private Coordinate extractUserCoordinates(String coordinates) {
		String[] userLocation = coordinates.split(",");
		return new Coordinate(userLocation[0], userLocation[1]);
	}

}
