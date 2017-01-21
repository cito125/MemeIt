package com.example.andresarango.memeit;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.example.andresarango.memeit.edit_meme_activity.FragmentAdapter;
import com.example.andresarango.memeit.edit_meme_activity.memes.VanillaMemeListener;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.VanillaMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.VanilleMemeFragment;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.adapter.EditVanillaMemeAdapter;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;

public class EditMemeActivity extends AppCompatActivity implements EditorViewHolder.Listener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter fragmentAdapter;
    private ImageView memeImage;
    private Button mNextActivityButton;
    private Button mChooseMemeButton;
    private Button mEditMemeButton;
    private RecyclerView.Adapter mEditAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);
        initialize(savedInstanceState);
//        startImageEditing();
    }

    private void startImageEditing() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /* 1) Make a new Uri object (Replace this with a real image on your device) */
        Uri imageUri = Uri.parse("content://media/external/images/media/####");

    /* 2) Create a new Intent */
        Intent imageEditorIntent = new AdobeImageIntent.Builder(this)
                .setData(imageUri)
                .build();

    /* 3) Start the Image Editor with request code 1 */
        startActivityForResult(imageEditorIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                /* 4) Make a case for the request code we passed to startActivityForResult() */
                case 1:
                    /* 5) Show the image! */
                    Uri editedImageUri = data.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
                    memeImage.setImageURI(null);
                    memeImage.setImageURI(editedImageUri);
                    break;
            }
        }
    }

    private void initialize(Bundle savedInstanceState) {
        memeImage = (ImageView) findViewById(R.id.meme_image);
//        mNextActivityButton = (Button) findViewById(R.id.btn_next_activity);
        mChooseMemeButton = (Button) findViewById(R.id.btn_choose_meme);
        mEditMemeButton = (Button) findViewById(R.id.btn_edit_meme);
        mRecyclerView = (RecyclerView) findViewById(R.id.meme_rv);

        mChooseMemeButton.setOnClickListener(onClickButton());
        mEditMemeButton.setOnClickListener(onClickButton());
        startFragment(savedInstanceState, new VanilleMemeFragment());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        fragmentAdapter = new FragmentAdapter(this);

        VanillaMemeWrapper vanillaMemeWrapper = new VanillaMemeWrapper();
        mEditAdapter = new EditVanillaMemeAdapter((VanillaMemeListener) vanillaMemeWrapper.getmFragment());
        ((FragmentAdapter) fragmentAdapter).addMemeWrapper(vanillaMemeWrapper);
//        mNextActivityButton.setOnClickListener(onClick());
    }

    private View.OnClickListener onClickButton() {
        return view -> {
            switch(view.getId()){
                case R.id.btn_choose_meme:
                    mRecyclerView.setAdapter(fragmentAdapter);
                    break;
                case R.id.btn_edit_meme:
                    mRecyclerView.setAdapter(mEditAdapter);


            }
        };
    }

    private void startFragment(Bundle savedInstanceState, Fragment memeFragment) {
        if(savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.meme_overlay_fragment, memeFragment)
                    .commit();
        }
    }

    @NonNull
    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SaveMemeActivity.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public void setEditMemeAdapter(RecyclerView.Adapter editMemeAdapter) {
        mEditAdapter = editMemeAdapter;
    }

    @Override
    public void swapFragment(Fragment memeFragment) {
        startFragment(null,memeFragment);
    }
}
