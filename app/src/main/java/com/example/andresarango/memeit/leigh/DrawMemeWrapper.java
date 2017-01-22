package com.example.andresarango.memeit.leigh;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

/**
 * Created by andresarango on 1/21/17.
 */

public class DrawMemeWrapper implements MemeWrapper{

    Fragment mDrawMemeFragment = new DrawingMemeFragment();

    @Override
    public EditorViewHolder getViewHolder(ViewGroup parent, EditorViewHolder.Listener listener) {
        return new DrawMemeViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.viewholder_draw_meme,parent,false),listener,mDrawMemeFragment);
    }
}
