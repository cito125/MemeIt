package com.example.andresarango.memeit.editmeme.memes.vanilla_meme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.editmeme.memes.VanillaMemeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andresarango on 1/21/17.
 */
public class EditVanillaMemeAdapter extends RecyclerView.Adapter {
    private final VanillaMemeListener mListener;
    List<Integer> editMemelist = new ArrayList<>();

    public EditVanillaMemeAdapter(VanillaMemeListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = getLayout(viewType);
        return new EditVanillaMemeViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(layout,parent,false), mListener);
    }

    private int getLayout(int viewType) {
        switch (viewType){
            case 0:
                return R.layout.viewholder_vanilla_meme_add_text;
            case 1:
                return R.layout.viewholder_vanilla_meme_change_color;
            case 2:
                return R.layout.viewholder_vanilla_meme_change_size;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return editMemelist.get(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EditVanillaMemeViewHolder)holder).onBind(editMemelist.get(position));
    }

    @Override
    public int getItemCount() {
        return editMemelist.size();
    }
}
