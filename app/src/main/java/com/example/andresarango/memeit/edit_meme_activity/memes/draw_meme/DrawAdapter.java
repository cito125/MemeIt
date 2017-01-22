package com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leighdouglas on 1/21/17.
 */

public class DrawAdapter extends RecyclerView.Adapter<EditDrawViewHolder> {

    private EditDrawViewHolder.Listener listener;
    private List<Integer> buttons = new ArrayList<>();


    public DrawAdapter(EditDrawViewHolder.Listener listener){
        this.listener = listener;
        for (int i = 0; i < 3; i++){
            buttons.add(i);
        }
    }

    @Override
    public EditDrawViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_viewholder, parent, false);
        return new EditDrawViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(EditDrawViewHolder holder, int position) {
        int i = buttons.get(position);
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }
}
