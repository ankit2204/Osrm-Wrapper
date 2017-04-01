package com.osrm.client;

import com.osrm.models.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OsrmAPI {
	
	@GET("route/v1/driving/{coordinates}")
	Call<OsrmResponse> getRoute(@Path("coordinates")String coordinates,@Query("overview")String overview);
		
}
