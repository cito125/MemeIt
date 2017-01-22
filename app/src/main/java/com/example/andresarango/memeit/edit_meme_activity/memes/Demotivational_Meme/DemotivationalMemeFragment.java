package com.example.andresarango.memeit.edit_meme_activity.memes.demotivational_meme;

import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.andresarango.memeit.R;

/**
 * Created by helenchan on 1/21/17.
 */

public class DemotivationalMemeFragment extends Fragment {
    private static final int MAX_NUM_OF_LETTERS_TITLE = 20;
    private static final int MAX_NUM_OF_LETTERS = 45;
    private View mRoot;
    EditText largeTextDemotivate;
    EditText smallTextDemotivate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_demotivational_meme, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return mRoot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        largeTextDemotivate = (EditText) mRoot.findViewById(R.id.demot_largetext);
        largeTextDemotivate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        setCharFilters(largeTextDemotivate, MAX_NUM_OF_LETTERS_TITLE);
        smallTextDemotivate = (EditText) mRoot.findViewById(R.id.demot_smalltext);
        setCharFilters(smallTextDemotivate, MAX_NUM_OF_LETTERS);

    }

    public void setCharFilters(EditText text, int length) {
        text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});

    }
}
