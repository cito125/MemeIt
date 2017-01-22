<<<<<<< HEAD
package com.example.andresarango.memeit.edit_meme_activity.memes.expectation_Meme;
=======
package com.example.andresarango.memeit.edit_meme_activity.memes.Expectation_Meme;
>>>>>>> 193fdeef480bae233b2ce6afb962ae37f6571999

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

/**
 * Created by jordansmith on 1/21/17.
 */

public class ExpectationMemeWrapper implements MemeWrapper {
    ExpectationMemeFragment ExpectationMemeFragment = new ExpectationMemeFragment();

    @Override
    public EditorViewHolder getViewHolder(ViewGroup parent, EditorViewHolder.Listener listener) {
        return new ExpectationMemeViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.viewholder_expectation_meme, parent, false),listener,ExpectationMemeFragment);
    }
}
