package com.example.jreina.test.starwars;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jreina.test.R;

public class SwAdapter extends RecyclerView.Adapter<SwAdapter.SwViewHolder> {

    private DataRepository dataRepository = new DataRepository();

    @NonNull
    @Override
    public SwViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new SwViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwViewHolder swViewHolder, int i) {
        swViewHolder.nameTv.setText(dataRepository.getPeople().get(i).getName());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataRepository.getPeopleListSize();
    }

    class SwViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        public SwViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.row_name);
        }
    }
}
