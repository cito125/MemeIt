package com.example.andresarango.memeit.editmeme;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.andresarango.memeit.editmeme.utility.EditorViewHolder;
import com.example.andresarango.memeit.editmeme.utility.MemeWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andresarango on 1/20/17.
 */
public class FragmentAdapter extends RecyclerView.Adapter<EditorViewHolder> {
    private final EditorViewHolder.Listener listener;
    List<MemeWrapper> mEditorViewHolderList = new ArrayList<>();

    public FragmentAdapter(EditorViewHolder.Listener editMemeActivity) {
        listener = editMemeActivity;
    }

    @Override
    public EditorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mEditorViewHolderList.get(viewType).getViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(EditorViewHolder holder, int position) {

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
