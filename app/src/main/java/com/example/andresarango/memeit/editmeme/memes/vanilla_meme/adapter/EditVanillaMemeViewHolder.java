package com.example.andresarango.memeit.editmeme.memes.vanilla_meme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andresarango.memeit.editmeme.memes.VanillaMemeListener;

/**
 * Created by andresarango on 1/21/17.
 */
public class EditVanillaMemeViewHolder extends RecyclerView.ViewHolder {

    private final VanillaMemeListener mListener;

    public EditVanillaMemeViewHolder(View inflate) {
        this(inflate,null);
    }

    public EditVanillaMemeViewHolder(View inflate, VanillaMemeListener listener) {
        super(inflate);
        mListener = listener;
    }

    public void onBind(Integer integer) {
        itemView.setOnClickListener(onClick(integer));
    }

    private View.OnClickListener onClick(final Integer integer) {
        return view -> {
            switch (integer){
                case 0:
                    mListener.addTextView();
                    break;
                case 1:
                    mListener.changeTextColor();
                    break;
                case 2:
                    mListener.changeTextSize();

            }
        };
    }


}
