package com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;

/**
 * Created by dannylui on 1/21/17.
 */
public class DragMemeViewHolder extends EditorViewHolder {

    private final DragMemeFragment mDragMemeFragment;

    @Override
    public RecyclerView.Adapter getAdapter() {
        return mDragMemeFragment.getDragMemeAdapter();
    }

    public DragMemeViewHolder(View inflate, Listener listener, DragMemeFragment mDragMemeFragment) {
        super(inflate,listener,mDragMemeFragment);
        this.mDragMemeFragment = mDragMemeFragment;

    }
}
