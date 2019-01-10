package com.example.jreina.test.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jreina.test.R;

import java.util.List;

public class DetailsFragment extends Fragment {

    private static SwViewModel swViewModel;
    private TextView detailsNameTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        swViewModel = ViewModelProviders.of(this).get(SwViewModel.class);

        swViewModel.getCharacter().observe(this, new Observer<Character>() {
            @Override
            public void onChanged(@Nullable Character character) {
                detailsNameTv.setText(character.getName());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_details, container, false);
        detailsNameTv = view.findViewById(R.id.details_name_tv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
