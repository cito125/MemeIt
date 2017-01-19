package com.example.andresarango.memeit.stockPicsRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.network.Meme;

import java.util.List;

/**
 * Created by helenchan on 1/19/17.
 */
public class StockMemeAdapter extends RecyclerView.Adapter<StockMemeViewHolder> {
    List<Meme> memesList;

    public StockMemeAdapter(List<Meme> memesList) {
        this.memesList = memesList;
    }

    @Override
    public StockMemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_stockpics, parent, false);
        return new StockMemeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StockMemeViewHolder holder, int position) {
        Meme meme = memesList.get(position);
        holder.bind(meme);
    }

    @Override
    public int getItemCount() {
        return memesList.size();
    }
}
