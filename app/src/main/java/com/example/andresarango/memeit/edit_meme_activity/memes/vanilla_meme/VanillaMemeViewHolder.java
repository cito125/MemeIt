package com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andresarango.memeit.edit_meme_activity.memes.VanillaMemeListener;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.adapter.EditVanillaMemeAdapter;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;

/**
 * Created by andresarango on 1/20/17.
 */

public class VanillaMemeViewHolder extends EditorViewHolder {
    private VanillaMemeListener mVanillaLister;
    @Override
    public RecyclerView.Adapter getAdapter() {
        return new EditVanillaMemeAdapter(mVanillaLister);
    }

    public VanillaMemeViewHolder(View itemView) {
        super(itemView);
    }

    public VanillaMemeViewHolder(View view, Listener listener, Fragment memeFragment) {
        super(view, listener, memeFragment);
        if(memeFragment instanceof VanillaMemeListener){
            mVanillaLister = (VanillaMemeListener) memeFragment;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
