package com.example.jreina.test.starwars;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jreina.test.R;
import com.example.jreina.test.databinding.ActivityStarWarsBinding;

public class StarWarsActivity extends AppCompatActivity implements ListFragment.ItemClickListener {

    private ActivityStarWarsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_star_wars);

        loadListFragment();
        binding.reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadListFragment();
            }
        });
    }

    private void loadListFragment() {
        if (!isNetworkAvailable()) {
            showViews(isNetworkAvailable());
        } else {
            showViews(isNetworkAvailable());
            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, listFragment, "listFragment")
                    .commit();
        }
    }

    private void showViews(boolean isOnline) {
        if (!isOnline) {
            binding.noWifiIv.setVisibility(View.VISIBLE);
            binding.noNetworkText.setVisibility(View.VISIBLE);
            binding.reloadBtn.setVisibility(View.VISIBLE);
        } else {
            binding.noWifiIv.setVisibility(View.INVISIBLE);
            binding.noNetworkText.setVisibility(View.INVISIBLE);
            binding.reloadBtn.setVisibility(View.INVISIBLE);
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
