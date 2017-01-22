package com.example.andresarango.memeit.edit_meme_activity.utility;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by andresarango on 1/13/17.
 */
public abstract class EditorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected final Listener mListener;
    private final Fragment mFragment;

    public RecyclerView.Adapter getAdapter(){
        return null;
    };
    public Fragment getMemeFragment(){
        return mFragment;
    };

    public EditorViewHolder(View itemView) {
        this(itemView,null,null);
    }

    public EditorViewHolder(View view, Listener listener, Fragment memeFragment){
        super(view);
        view.setOnClickListener(this);
        mListener = listener;
        mFragment = memeFragment;
    }

    @Override
    public void onClick(View view) {
        mListener.swapFragment(getMemeFragment());
        mListener.setEditMemeAdapter(getAdapter());

    }

    public interface Listener {
        void setEditMemeAdapter(RecyclerView.Adapter editMemeAdapter);
        void swapFragment(Fragment memeFragment);
    }
}
