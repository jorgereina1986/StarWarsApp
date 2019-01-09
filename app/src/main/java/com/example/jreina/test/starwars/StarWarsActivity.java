package com.example.jreina.test.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jreina.test.R;

public class StarWarsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ListFragment listFragment = new ListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, listFragment, "listFragment")
                .commit();
    }
}
