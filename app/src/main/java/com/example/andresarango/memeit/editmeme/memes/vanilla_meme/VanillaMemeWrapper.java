package com.example.andresarango.memeit.editmeme.memes.vanilla_meme;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.editmeme.utility.EditorViewHolder;
import com.example.andresarango.memeit.editmeme.utility.MemeWrapper;

/**
 * Created by andresarango on 1/20/17.
 */

public class VanillaMemeWrapper implements MemeWrapper {
    Fragment mFragment = new VanilleMemeFragment();

    @Override
    public EditorViewHolder getViewHolder(ViewGroup parent, EditorViewHolder.Listener listener) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.vanilla_meme_viewholder, parent, false);
        return new VanillaMemeViewHolder(view, listener, mFragment);
    }
}
