package com.example.andresarango.memeit.edit_meme_activity.memes;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.andresarango.memeit.edit_meme_activity.utility.MemeViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.utility.MemeWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andresarango on 1/20/17.
 */
public class FragmentAdapter<T extends MemeViewHolder.Listener & MemeFragment.Listener> extends RecyclerView.Adapter<MemeViewHolder> {
    private final T mListener;
    List<MemeWrapper> mEditorViewHolderList = new ArrayList<>();

    public FragmentAdapter(T listener) {
        mListener = listener;
    }

    @Override
    public MemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mEditorViewHolderList.get(viewType).getViewHolder(parent, mListener);
    }

    @Override
    public void onBindViewHolder(MemeViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mEditorViewHolderList.size();
    }

    public void addMemeWrapper(MemeWrapper memeWrapper) {
        mEditorViewHolderList.add(memeWrapper);
        notifyItemInserted(mEditorViewHolderList.size() - 1);
    }
}
