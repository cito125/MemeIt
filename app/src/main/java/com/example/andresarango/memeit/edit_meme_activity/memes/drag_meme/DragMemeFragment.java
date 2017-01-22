package com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.andresarango.memeit.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dannylui on 1/21/17.
 */

public class DragMemeFragment extends Fragment {
    public static final String IMAGE_KEY = "image.key";
    private View rootView;
    public DragMemeAdapter dragMemeAdapter;
    private Bitmap image;

    public static DragMemeFragment newInstance(@Nullable Parcelable bmp) {
        DragMemeFragment dragMemeFragment = new DragMemeFragment();
        Bundle args = new Bundle();
        args.putParcelable(IMAGE_KEY, bmp);
        dragMemeFragment.setArguments(args);
        return dragMemeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_drag_meme, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = getArguments().getParcelable(IMAGE_KEY);
        setupSurfaceView();
        setupAdapter();
        setupTopBar();
    }

    private void setupSurfaceView() {
        RelativeLayout surfaceViewContainer = (RelativeLayout) rootView.findViewById(R.id.sv_container) ;

        // Image goes behind/under the surface view
        ImageView backgroundImage = (ImageView) rootView.findViewById(R.id.bg_photo);
        backgroundImage.setImageBitmap(image);

        // Sets the surface view on top and transparent
        MySurfaceView mySurfaceView = new MySurfaceView(getActivity(), image, this);
        mySurfaceView.setZOrderOnTop(true);
        mySurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surfaceViewContainer.addView(mySurfaceView);
    }

    private void setupAdapter() {
        List<Integer> iconIds = Arrays.asList(
                R.drawable.drag_icon_jj, R.drawable.drag_icon_josev, R.drawable.drag_icon_andres, R.drawable.drag_icon_helen,
                R.drawable.drag_icon_lily, R.drawable.drag_icon_lilycat, R.drawable.drag_icon_jordan, R.drawable.drag_icon_jon,
                R.drawable.drag_icon_eddie, R.drawable.drag_icon_shannon, R.drawable.drag_icon_mila, R.drawable.drag_icon_ashique2
        );

        dragMemeAdapter = new DragMemeAdapter(iconIds);
    }

    public DragMemeAdapter getDragMemeAdapter() {
        return dragMemeAdapter;
    }

    private void setupTopBar() {
        ImageView undoIv = (ImageView) rootView.findViewById(R.id.undo);
        ImageView clearIv = (ImageView) rootView.findViewById(R.id.clear);
        ImageView saveIv = (ImageView) rootView.findViewById(R.id.save);

        // Yellow button to undo last added icon
        undoIv.setOnClickListener(view -> MySurfaceView.instance.undo());

        // Red button to clear all icons
        clearIv.setOnClickListener(view -> MySurfaceView.instance.clear());

        // Saves image to a bitmap TODO but doesn't go anywhere with it
        saveIv.setOnClickListener(view -> MySurfaceView.instance.save());

    }

//    public void readyForSave(){
//        Intent intent = new Intent(rootView.getContext(), SaveActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onPause() {
        super.onPause();
        MySurfaceView.instance.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        MySurfaceView.instance.onResume();
    }
}
