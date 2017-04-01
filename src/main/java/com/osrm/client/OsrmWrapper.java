package com.osrm.client;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.osrm.models.Coordinate;
import com.osrm.models.OsrmResponse.Route;
import com.osrm.models.StoreDistance;

public class OsrmWrapper {

	private OsrmClient client;

	public OsrmWrapper() {
		client = new OsrmClient();
	}

	public List<StoreDistance> getDistanceForAllStores(Coordinate userCoordinate,
			HashMap<String, Coordinate> stores) {

		List<StoreDistance> storeDistances = new ArrayList<StoreDistance>();

		for (Map.Entry<String, Coordinate> store : stores.entrySet()) {

			double distance = getDistanceForStore(userCoordinate, store.getValue(), store.getKey());
			storeDistances.add(new StoreDistance(store.getKey(), distance));
		}
		return storeDistances;
	}

	public double getDistanceForStore(Coordinate user, Coordinate store, String storeId) {
		double distance = 0;
		try {
			List<Route> routes = client.callOsrmForDistance(user, store).getRoutes();
			distance = routes.get(0).getDistance();
		} catch (Exception ex) {
			System.out.println("Failed to get the distance for store" + storeId);
			System.out.println(ex.getMessage());
		}
		return distance;
	}

}
