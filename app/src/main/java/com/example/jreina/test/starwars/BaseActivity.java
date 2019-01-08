package com.example.jreina.test.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jreina.test.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private RecyclerView starWarsRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        starWarsRv = findViewById(R.id.star_wars_rv);
        starWarsRv.setLayoutManager(new LinearLayoutManager(this));
        starWarsRv.setAdapter(new SwAdapter());




        SwViewModel viewModel = ViewModelProviders.of(this).get(SwViewModel.class);
        viewModel.getPeople().observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(@Nullable List<Results> results) {

            }
        });



    }
}
