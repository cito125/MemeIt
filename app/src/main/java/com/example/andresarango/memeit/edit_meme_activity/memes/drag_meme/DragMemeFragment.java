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
    private RelativeLayout surfaceViewContainer;
    private Bitmap image;
    private MySurfaceView mySurfaceView;
    public DragMemeAdapter dragMemeAdapter;

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

//    public void readyForSave(){
//        Intent intent = new Intent(rootView.getContext(), SaveActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = getArguments().getParcelable(IMAGE_KEY);
        setupToolkit();
        addSurfaceViewToFragment();
        setupRecyclerView();


    }

    private void addSurfaceViewToFragment() {
        surfaceViewContainer = (RelativeLayout) rootView.findViewById(R.id.sv_container) ;

        // Image goes behind/under the surface view
        ImageView backgroundImage = (ImageView) rootView.findViewById(R.id.bg_photo);
        backgroundImage.setImageBitmap(image);

        // Sets the surface view on top and transparent
        //mySurfaceView = new MySurfaceView(getActivity(), image, this);
        mySurfaceView = new MySurfaceView(getActivity(), image, this);
        mySurfaceView.setZOrderOnTop(true);
        mySurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        surfaceViewContainer.addView(mySurfaceView);
//        dragMemeAdapter.setMySurfaceView(mySurfaceView);

    }

    private void setupRecyclerView() {
        List<Integer> iconIds = Arrays.asList(
                R.drawable.drag_icon_jj, R.drawable.drag_icon_josev, R.drawable.drag_icon_andres, R.drawable.drag_icon_helen,
                R.drawable.drag_icon_lily, R.drawable.drag_icon_lilycat, R.drawable.drag_icon_jordan, R.drawable.drag_icon_jon,
                R.drawable.drag_icon_eddie, R.drawable.drag_icon_shannon, R.drawable.drag_icon_mila, R.drawable.drag_icon_ashique2
        );

        dragMemeAdapter = new DragMemeAdapter(mySurfaceView, iconIds);

//        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(new DragMemeAdapter(mySurfaceView, iconIds));

    }

    public DragMemeAdapter getDragMemeAdapter() {
        return dragMemeAdapter;
    }


    private void setupToolkit() {
        ImageView undoIv = (ImageView) rootView.findViewById(R.id.undo);
        ImageView clearIv = (ImageView) rootView.findViewById(R.id.clear);
        ImageView saveIv = (ImageView) rootView.findViewById(R.id.save);

        undoIv.setOnClickListener(view -> mySurfaceView.undo());

        clearIv.setOnClickListener(view -> mySurfaceView.clear());

        saveIv.setOnClickListener(view -> mySurfaceView.save());

    }

    @Override
    public void onPause() {
        super.onPause();
        mySurfaceView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mySurfaceView.onResume();
    }
}
