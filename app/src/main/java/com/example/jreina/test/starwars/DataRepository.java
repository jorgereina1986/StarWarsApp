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

    private List<Character> peoples = new ArrayList<>();

    public List<Character> getPeoples() {
        if (peoples == null || peoples.size() <= 0) {
            loadPeople();
        }
        return peoples;
    }

    public void setPeoples(List<Character> peoples) {
        this.peoples = peoples;
    }

    public Character getPerson(int position) {
        return peoples.get(position);
    }

    public int getPeopleListSize() {
        return (peoples != null ? peoples.size() : -1);
    }

    private void loadPeople() {
        final List<Character> people =  new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsApi api = retrofit.create(StarWarsApi.class);

        Call<StarWarsResponse> call = api.getPeople();
        call.enqueue(new Callback<StarWarsResponse>() {
            @Override
            public void onResponse(Call<StarWarsResponse> call, Response<StarWarsResponse> response) {
                Log.d(TAG, "onResponse: success" + response.body().getCharacters().get(0).getName());
//                people.addAll(response.body().getCharacters());
                peoples.addAll(response.body().getCharacters());
            }

            @Override
            public void onFailure(Call<StarWarsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
