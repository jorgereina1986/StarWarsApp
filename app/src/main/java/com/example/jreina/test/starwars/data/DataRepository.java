package com.example.jreina.test.starwars.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.net.HttpCookie;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {

    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String TAG = DataRepository.class.getSimpleName();
    private MutableLiveData<List<Character>> characterList = new MutableLiveData<>();

    public LiveData<List<Character>> getCharacterList() {
        if (characterList == null) {
            characterList = new MutableLiveData<>();
            loadCharacters();
        }
        return characterList;
    }

    private void loadCharacters() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsApi api = retrofit.create(StarWarsApi.class);

        Call<StarWarsResponse> call = api.getPeople();
        call.enqueue(new Callback<StarWarsResponse>() {
            @Override
            public void onResponse(Call<StarWarsResponse> call, Response<StarWarsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getCharacters().get(0).getName());
                characterList.setValue(response.body().getCharacters());
            }

            @Override
            public void onFailure(Call<StarWarsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
