package com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;

/**
 * Created by andresarango on 1/21/17.
 */
public class DrawMemeViewHolder extends EditorViewHolder {
    private EditDrawViewHolder.Listener mDrawListener;

    @Override
    public RecyclerView.Adapter getAdapter() {
        if(mDrawListener != null){
            System.out.println("yo");
        }
        return new DrawAdapter(mDrawListener);
    }

    public DrawMemeViewHolder(View inflate, Listener listener, Fragment mDrawMemeFragment) {
        super(inflate,listener,mDrawMemeFragment);
        if(mDrawMemeFragment instanceof DrawingMemeFragment){
            mDrawListener = ((DrawingMemeFragment) mDrawMemeFragment).getmDrawingView();
        }
    }
}
