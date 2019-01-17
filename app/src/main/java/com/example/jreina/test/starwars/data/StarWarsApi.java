package com.example.jreina.test.starwars.data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StarWarsApi {
    @GET("people/")
    Call<StarWarsResponse> getPeople();
}
