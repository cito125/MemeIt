package com.example.andresarango.memeit.edit_meme_activity.utility;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by andresarango on 1/13/17.
 */
public abstract class MemeViewHolder<T extends MemeFragment.Listener & MemeViewHolder.Listener> extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected final T mListener;
    private MemeFragment mFragment;

    public MemeViewHolder(View itemView) {
        this(itemView,null,null);
    }

    public MemeViewHolder(View view, T listener, Class<MemeFragment> memeFragment){
        super(view);
        mListener = listener;
        mListener.setEditMemeAdapter(null);
        try {
            mFragment = memeFragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mFragment.setmListener(mListener);
        view.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        mListener.swapFragment(mFragment);

    }

    public interface Listener {
        void swapFragment(MemeFragment memeFragment);
    }
}
