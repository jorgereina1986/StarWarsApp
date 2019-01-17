package com.example.jreina.test.starwars;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jreina.test.BR;
import com.example.jreina.test.databinding.ItemRowBinding;

import java.util.List;

public class SwAdapter extends RecyclerView.Adapter<SwAdapter.SwViewHolder> {

    private static final String TAG = SwAdapter.class.getSimpleName();

    private List<Character> characterList;
    private ListFragment.ItemClickListener itemClickListener;

    public SwAdapter(ListFragment.ItemClickListener itemClickListener) {
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
    public void onBindViewHolder(@NonNull SwViewHolder holder, int i) {
        Character character = characterList.get(i);
        holder.bind(character);
    }

    @Override
    public int getItemCount() {
        return characterList != null ? characterList.size() : 0;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
        notifyDataSetChanged();
    }

    class SwViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemRowBinding binding;

        private SwViewHolder(@NonNull ItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        private void bind(Character character) {
            binding.setVariable(BR.character, character);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onItemClick(clickedPosition);
        }
    }
}
