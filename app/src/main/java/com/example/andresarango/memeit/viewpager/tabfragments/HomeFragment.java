package com.example.andresarango.memeit.viewpager.tabfragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.andresarango.memeit.EditMemeActivity;
import com.example.andresarango.memeit.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by dannylui on 1/18/17.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    LinearLayout makeMemeFromGalleryButton;
    LinearLayout createMemeFromPopularButton;
    private int PICK_IMAGE_REQUEST = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        initializeButtons(rootView);
        return rootView;
    }

    private void initializeButtons(View rootView){
        makeMemeFromGalleryButton = (LinearLayout) rootView.findViewById(R.id.make_meme_from_gallery_button);
        makeMemeFromGalleryButton.setOnClickListener(this);
        createMemeFromPopularButton = (LinearLayout) rootView.findViewById(R.id.make_meme_popular_image_button);
        createMemeFromPopularButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.make_meme_from_gallery_button:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
            case R.id.make_meme_popular_image_button:
                Intent tempIntent = new Intent(getContext(), EditMemeActivity.class);
                startActivity(tempIntent);
                break;

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Intent intent = new Intent(rootView.getContext(), EditMemeActivity.class);
            intent.putExtra("ImageString", uri.toString());
            startActivity(intent);
        }
    }


}
