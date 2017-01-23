package com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

/**
 * Created by andresarango on 1/20/17.
 */

public class VanillaMemeWrapper implements MemeWrapper {

    @Override
    public MemeViewHolder getViewHolder(ViewGroup parent, MemeFragment.Listener listener) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.vanilla_meme_viewholder, parent, false);
        return new VanillaMemeViewHolder(view, listener, VanilleMemeFragment.class);
    }
}
