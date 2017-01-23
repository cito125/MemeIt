package com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme.DrawMemeViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;


/**
 * Created by dannylui on 1/21/17.
 */

public class DragMemeWrapper implements MemeWrapper {


    private final DragMemeFragment mDragMemeFragment;

    public DragMemeWrapper(Bitmap bitmap) {
        mDragMemeFragment = DragMemeFragment.newInstance(bitmap);
    }

    @Override
    public MemeViewHolder getViewHolder(ViewGroup parent, MemeFragment.Listener listener) {
        return new DrawMemeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_drag_meme,parent,false),listener,DragMemeFragment.class);
    }
}
