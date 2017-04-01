package com.osrm.client;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osrm.models.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OsrmClient implements Callback<OsrmResponse> {

	static final String BASE_URL = "http://52.187.179.149:5000";

	public OsrmResponse callOsrmForDistance(Coordinate userCor, Coordinate storeCor) throws IOException {

		String coordinateString = appendCoordinates(userCor, storeCor);

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		OsrmAPI osrmAPI = retrofit.create(OsrmAPI.class);

		Call<OsrmResponse> call = osrmAPI.getRoute(coordinateString, "false");
		OsrmResponse response = call.execute().body();

		return response;

	}

	private String appendCoordinates(Coordinate userCor, Coordinate storeCor) {
		StringBuffer s = new StringBuffer();
		s.append(userCor.getLongitude());
		s.append(",");
		s.append(userCor.getLatitude());
		s.append(";");
		s.append(storeCor.getLongitude());
		s.append(",");
		s.append(storeCor.getLatitude());
		return s.toString();

	}

	@Override
	public void onResponse(Call<OsrmResponse> call, Response<OsrmResponse> response) {
		if (response.isSuccessful()) {
			OsrmResponse osrmResponse = response.body();
			System.out.println(osrmResponse.getRoutes().get(0).getDistance());
		}

	}

	@Override
	public void onFailure(Call<OsrmResponse> call, Throwable t) {
		t.printStackTrace();

	}

}
