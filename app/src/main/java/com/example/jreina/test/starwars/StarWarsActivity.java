package com.example.jreina.test.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jreina.test.R;

import java.util.List;

public class StarWarsActivity extends AppCompatActivity implements ListFragment.ItemClickListener {

    private TextView noNetworkTv;
    private SwViewModel swViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        noNetworkTv = findViewById(R.id.no_network_text);

        swViewModel = ViewModelProviders.of(this).get(SwViewModel.class);

        if (!isNetworkAvailable()) {
            noNetworkTv.setVisibility(View.VISIBLE);
        } else {
            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, listFragment, "listFragment")
                    .commit();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onItemClick(final int position) {

        DetailsFragment detailsFragment = DetailsFragment.newInstance(position);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
