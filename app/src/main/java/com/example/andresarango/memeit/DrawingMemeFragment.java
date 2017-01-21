package com.example.andresarango.memeit;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.model.custom_views.DrawingView;

/**
 * Created by leighdouglas on 1/20/17.
 */

public class DrawingMemeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawing_fragment, container, false);
        return view;
    }
}
