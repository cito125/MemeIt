package com.example.andresarango.memeit.edit_meme_activity.memes.demotivational_meme;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

/**
 * Created by andresarango on 1/22/17.
 */

public class DemotivationalMemeWrapper implements MemeWrapper {
    @Override
    public MemeViewHolder getViewHolder(ViewGroup parent, MemeFragment.Listener listener) {
        return new DemotivationalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_demotivational_meme,parent,false),listener,DemotivationalMemeFragment.class);
    }
}
