package com.example.jreina.test.starwars;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jreina.test.R;
import com.example.jreina.test.databinding.ItemRowBinding;

import java.util.List;

public class SwAdapter extends RecyclerView.Adapter<SwAdapter.SwViewHolder> {

    private static final String TAG = SwAdapter.class.getSimpleName();

    private List<Character> characterList;
    private ListFragment.ItemClickListener itemClickListener;

    public SwAdapter(List<Character> characterList, ListFragment.ItemClickListener itemClickListener) {
        this.characterList = characterList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SwViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemRowBinding binding = ItemRowBinding.inflate(inflater, viewGroup, false);
        return new SwViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SwViewHolder swViewHolder, int i) {
        Character character = characterList.get(i);
        swViewHolder.binding.rowName.setText(character.getName());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + characterList.size());
        return characterList.size();
    }

    class SwViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemRowBinding binding;

        public SwViewHolder(@NonNull ItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onItemClick(clickedPosition);
        }
    }
}
