package com.example.andresarango.memeit.editmeme.utility;

import android.app.Fragment;
import android.view.ViewGroup;

import com.example.andresarango.memeit.editmeme.utility.EditorViewHolder;

/**
 * Created by andresarango on 1/20/17.
 */
public interface MemeWrapper {
    EditorViewHolder getViewHolder(ViewGroup parent,EditorViewHolder.Listener listener);
}
