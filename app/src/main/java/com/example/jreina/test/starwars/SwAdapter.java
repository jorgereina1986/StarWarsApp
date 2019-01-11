package com.example.jreina.test.starwars;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jreina.test.R;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new SwViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwViewHolder swViewHolder, int i) {
        Character character = characterList.get(i);
        swViewHolder.nameTv.setText(character.getName());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + characterList.size());
        return characterList.size();
    }

    class SwViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTv;

        public SwViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.row_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onItemClick(clickedPosition);
        }
    }
}
