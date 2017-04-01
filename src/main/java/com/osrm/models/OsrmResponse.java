package com.osrm.models;

import java.util.List;

public class OsrmResponse {

    public class Route{
        private double distance;
        private double duration;

        public double getDistance(){
            return distance/1000;
        }

        public double getDuration() {
            return duration;
        }
    }

	private List<Route> routes;

    public List<Route> getRoutes(){
        return routes;
    }
}