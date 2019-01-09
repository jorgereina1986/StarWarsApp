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
//    private DataRepository dataRepository = new DataRepository();

    private List<Results> characterList;

    public SwAdapter(List<Results> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public SwViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new SwViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwViewHolder swViewHolder, int i) {
        Results character = characterList.get(i);
        swViewHolder.nameTv.setText(character.getName());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + characterList.size());
        return characterList.size();
    }

    class SwViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;
        public SwViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.row_name);
        }
    }
}
