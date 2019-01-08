package com.example.jreina.test.starwars;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jreina.test.R;

public class SwAdapter extends RecyclerView.Adapter<SwAdapter.SwViewHolder> {

    private DataManager dataManager = new DataManager();

    @NonNull
    @Override
    public SwViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new SwViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwViewHolder swViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return dataManager.getPeopleListSize();
    }

    class SwViewHolder extends RecyclerView.ViewHolder {

        public SwViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
