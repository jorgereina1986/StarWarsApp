package com.example.jreina.test.starwars;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jreina.test.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private static final String TAG = ListFragment.class.getSimpleName();
    private RecyclerView starWarsRv;
    private SwAdapter adapter;
    private List<Character> characterList = new ArrayList<>();
    private SwViewModel viewModel;
    private ItemClickListener itemClickListener;

    public ListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        starWarsRv = view.findViewById(R.id.star_wars_rv);
        starWarsRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new SwAdapter(characterList, itemClickListener);
        starWarsRv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SwViewModel.class);
        viewModel.getCharacterList().observe(getActivity(), new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> results) {
                characterList.addAll(results);
                adapter.notifyDataSetChanged();

            }
        });
    }

    interface ItemClickListener {
        void onItemClick(int position);
    }
}
