package com.example.andresarango.memeit.edit_meme_activity.utility;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

/**
 * Created by andresarango on 1/23/17.
 */

public abstract class MemeFragment extends Fragment {

    protected Listener mListener;

    public void setmListener(Listener mListener) {
        this.mListener = mListener;
    }


    public interface Listener{
        void setEditMemeAdapter(RecyclerView.Adapter editMemeAdapter);
    }
}
