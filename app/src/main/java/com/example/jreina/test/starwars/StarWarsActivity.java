package com.example.jreina.test.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jreina.test.R;
import com.example.jreina.test.databinding.ActivityStarWarsBinding;

import java.util.List;

public class StarWarsActivity extends AppCompatActivity implements ListFragment.ItemClickListener {

    private ActivityStarWarsBinding binding;
    private SwViewModel viewModel;

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

        viewModel = ViewModelProviders.of(this).get(SwViewModel.class);

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


        viewModel.getCharacterList().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> characters) {
                viewModel.setCharacter(characters.get(position));
            }
        });

        DetailsFragment detailsFragment = new DetailsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
