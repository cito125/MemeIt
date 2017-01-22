package com.example.andresarango.memeit.edit_meme_activity.memes.expectation_meme;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

/**
 * Created by jordansmith on 1/21/17.
 */

public class ExpectationMemeWrapper implements MemeWrapper {
    ExpectationMemeFragment expectationMemeFragment = new ExpectationMemeFragment();

    @Override
    public EditorViewHolder getViewHolder(ViewGroup parent, EditorViewHolder.Listener listener) {
        return new ExpectationMemeViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.viewholder_expectation_meme, parent, false),listener,expectationMemeFragment);
    }
}
