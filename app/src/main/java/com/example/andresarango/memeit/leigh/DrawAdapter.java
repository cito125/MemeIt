package com.example.andresarango.memeit.leigh;

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

public class DrawAdapter extends RecyclerView.Adapter<DrawViewHolder> {

    private List<Integer> buttons = new ArrayList<>();


    public DrawAdapter(List<Integer> buttons){
        this.buttons = buttons;
    }

    public DrawAdapter(){
        for (int i = 0; i < 3; i++){
            buttons.add(i);
        }
    }

    @Override
    public DrawViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.draw_viewholder, parent, false);
        return new DrawViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawViewHolder holder, int position) {
        int i = buttons.get(position);
        holder.bind(i);
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }
}
