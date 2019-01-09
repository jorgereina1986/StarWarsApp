package com.example.jreina.test.starwars;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {

    private static final String TAG = DataRepository.class.getSimpleName();
    private static final String BASE_URL = "https://swapi.co/api/";

    private List<Results> peoples = loadPeople();


    public List<Results> getPeople() {
        return peoples;
    }

    public Results getPerson(int position) {
        return peoples.get(position);
    }

    public int getPeopleListSize() {
        return (peoples != null ? peoples.size() : -1);
    }

    private List<Results> loadPeople() {
        final List<Results> people =  new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsApi api = retrofit.create(StarWarsApi.class);

        Call<StarWarsResponse> call = api.getPeople();
        call.enqueue(new Callback<StarWarsResponse>() {
            @Override
            public void onResponse(Call<StarWarsResponse> call, Response<StarWarsResponse> response) {
                Log.d(TAG, "onResponse: success" + response.body().getResults().get(0).getName());
                people.addAll(response.body().getResults());
            }

            @Override
            public void onFailure(Call<StarWarsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return people;
    }
}
