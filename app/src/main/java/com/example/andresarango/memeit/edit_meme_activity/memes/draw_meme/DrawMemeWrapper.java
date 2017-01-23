package com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

/**
 * Created by andresarango on 1/21/17.
 */

public class DrawMemeWrapper implements MemeWrapper{
    @Override
    public MemeViewHolder getViewHolder(ViewGroup parent, MemeFragment.Listener listener) {
        return new DrawMemeViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.viewholder_draw_meme,parent,false),listener,DrawingMemeFragment.class);
    }
}
