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

    private MutableLiveData<List<Results>> people;

    public LiveData<List<Results>> getPeople() {
        if (people == null) {
            people = new MutableLiveData<>();
            loadPeople();
        }
        return people;
    }

    private void loadPeople() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsApi api = retrofit.create(StarWarsApi.class);

        Call<StarWarsResponse> call = api.getPeople();
        call.enqueue(new Callback<StarWarsResponse>() {
            @Override
            public void onResponse(Call<StarWarsResponse> call, Response<StarWarsResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getResults().get(0).getName());
            }

            @Override
            public void onFailure(Call<StarWarsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
