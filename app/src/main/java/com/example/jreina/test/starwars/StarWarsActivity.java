package com.example.jreina.test.starwars;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jreina.test.R;

public class StarWarsActivity extends AppCompatActivity implements ListFragment.ItemClickListener {

    private TextView noNetworkTv;
    private Button reloadBtn;
    private ImageView wifiLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        noNetworkTv = findViewById(R.id.no_network_text);
        wifiLogo = findViewById(R.id.no_wifi_iv);
        reloadBtn = findViewById(R.id.reload_btn);

        loadListFragment();
        reloadBtn.setOnClickListener(new View.OnClickListener() {
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
            wifiLogo.setVisibility(View.VISIBLE);
            noNetworkTv.setVisibility(View.VISIBLE);
            reloadBtn.setVisibility(View.VISIBLE);
        } else {
            wifiLogo.setVisibility(View.INVISIBLE);
            noNetworkTv.setVisibility(View.INVISIBLE);
            reloadBtn.setVisibility(View.INVISIBLE);
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
