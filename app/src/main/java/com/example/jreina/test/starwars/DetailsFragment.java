package com.example.jreina.test.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jreina.test.R;
import com.example.jreina.test.databinding.FragmentDetailsBinding;

import java.util.List;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private static final String POSITION = "postion";

    public static DetailsFragment newInstance(int pos) {
        Bundle args = new Bundle();
        args.putInt(POSITION, pos);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SwViewModel swViewModel = ViewModelProviders.of(this).get(SwViewModel.class);
        swViewModel.getCharacterList().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> characters) {
                int pos = getArguments().getInt(POSITION);
                binding.detailsNameTv.setText(characters.get(pos).getName());
            }
        });
    }
}
