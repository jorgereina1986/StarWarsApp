package com.example.jreina.test.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jreina.test.R;
import com.example.jreina.test.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    private static final String TAG = ListFragment.class.getSimpleName();
    private SwAdapter adapter;
    private List<Character> characterList = new ArrayList<>();
    private SwViewModel viewModel;
    private ItemClickListener itemClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ItemClickListener) {
            itemClickListener = (ItemClickListener) context;
        } else {
            throw new ClassCastException("Error");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        binding.starWarsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SwAdapter(characterList, itemClickListener);
        binding.starWarsRv.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (characterList == null | characterList.isEmpty()) {
            viewModel = ViewModelProviders.of(this).get(SwViewModel.class);
            viewModel.getCharacterList().observe(getActivity(), new Observer<List<Character>>() {
                @Override
                public void onChanged(@Nullable List<Character> results) {
                    characterList.addAll(results);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    interface ItemClickListener {
        void onItemClick(int position);
    }
}
