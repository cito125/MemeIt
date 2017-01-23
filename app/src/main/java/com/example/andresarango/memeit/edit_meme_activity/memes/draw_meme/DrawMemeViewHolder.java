package com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.andresarango.memeit.edit_meme_activity.utility.MemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeViewHolder;

/**
 * Created by andresarango on 1/21/17.
 */
public class DrawMemeViewHolder extends MemeViewHolder {

    public DrawMemeViewHolder(View view, MemeFragment.Listener listener, Class memeFragment) {
        super(view, listener, memeFragment);
    }
}
