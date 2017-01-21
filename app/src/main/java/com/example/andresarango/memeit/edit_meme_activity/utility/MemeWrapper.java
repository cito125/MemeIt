package com.example.andresarango.memeit.edit_meme_activity.utility;

import android.view.ViewGroup;

/**
 * Created by andresarango on 1/20/17.
 */
public interface MemeWrapper {
    EditorViewHolder getViewHolder(ViewGroup parent,EditorViewHolder.Listener listener);
}
