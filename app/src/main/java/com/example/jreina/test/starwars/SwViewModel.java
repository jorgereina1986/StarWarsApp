package com.example.jreina.test.starwars;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SwViewModel extends ViewModel {

    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String TAG = SwViewModel.class.getSimpleName();

    private DataRepository dataRepository = new DataRepository();

    private MutableLiveData<List<Results>> characterList;

    public MutableLiveData<List<Results>> getCharacterList() {

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
                Log.d(TAG, "onResponse: " + response.body().getResults().get(0).getName());
                characterList.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<StarWarsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
