package com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;

/**
 * Created by leighdouglas on 1/20/17.
 */

public class DrawingMemeFragment extends Fragment {

    private DrawingView mDrawingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawing_fragment, container, false);
        mDrawingView = (DrawingView) view.findViewById(R.id.drawing_view);
        return view;
    }

    public DrawingView getmDrawingView() {
        return mDrawingView;
    }
}
