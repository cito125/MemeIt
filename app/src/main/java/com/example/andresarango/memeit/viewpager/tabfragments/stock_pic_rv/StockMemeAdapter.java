package com.example.andresarango.memeit.viewpager.tabfragments.stock_pic_rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.model.Meme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helenchan on 1/19/17.
 */
public class StockMemeAdapter extends RecyclerView.Adapter<StockMemeViewHolder> {

    List<Meme> mMemeList = new ArrayList<>();


    @Override
    public StockMemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_stockpics, parent, false);
        return new StockMemeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StockMemeViewHolder holder, int position) {
        Meme meme = mMemeList.get(position);
        holder.bind(meme);
    }

    @Override
    public int getItemCount() {
        return mMemeList.size();
    }

    public void setMemesList(List<Meme> memesList) {
        mMemeList.clear();
        mMemeList.addAll(memesList);
        notifyDataSetChanged();
    }
}
