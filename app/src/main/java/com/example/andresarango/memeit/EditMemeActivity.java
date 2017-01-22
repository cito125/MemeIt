package com.example.andresarango.memeit;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.andresarango.memeit.edit_meme_activity.memes.FragmentAdapter;
import com.example.andresarango.memeit.edit_meme_activity.memes.VanillaMemeListener;
import com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme.DragMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.expectation_meme.ExpectationMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.VanillaMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.adapter.EditVanillaMemeAdapter;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;
import com.example.andresarango.memeit.edit_meme_activity.memes.expectation_meme.ExpectationMemeWrapper;
import com.example.andresarango.memeit.leigh.DrawMemeWrapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class EditMemeActivity extends AppCompatActivity implements EditorViewHolder.Listener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter fragmentAdapter;
    private ImageView memeImage;
    private Button mNextActivityButton;
    private Button mChooseMemeButton;
    private Button mEditMemeButton;
    private RecyclerView.Adapter mEditAdapter;
    private String memeURL;
    private Toolbar editMemeToolbar;

    private Bitmap mMemeImageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);

        mMemeImageBitmap = getBitmapFromUri(getIntent().getStringExtra("ImageString"));

//        How to make danny meme fragment below, make instance of my fragment with bitmap and inflate it
//        DragMemeFragment dragMemeFragment = DragMemeFragment.newInstance(mMemeImageBitmap);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.activity_create_meme, dragMemeFragment)
//                .commit();

        initialize(savedInstanceState);

        Intent intent = getIntent();
        int picKey = intent.getIntExtra("TypeOfPicture",5);
        if(picKey == 1){
            memeImageBitmap = getBitmapFromUri(getIntent().getStringExtra("ImageString"));
            memeImage.setImageBitmap(memeImageBitmap);
        }
        if(picKey == 0){
            String newString = intent.getStringExtra("CameraPhotoUri");
            memeImageBitmap = getBitmapFromUri(newString);
            memeImage.setImageBitmap(memeImageBitmap);
        }
        if(picKey == 3){
            loadStockImage();
        }
    }

    private Bitmap getBitmapFromUri(String imageUriString) {
        Bitmap bitmap = null;
        Uri imageUri = Uri.parse(imageUriString);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    private void initialize(Bundle savedInstanceState) {
        memeImage = (ImageView) findViewById(R.id.meme_image);
//        mNextActivityButton = (Button) findViewById(R.id.btn_next_activity);
        mChooseMemeButton = (Button) findViewById(R.id.btn_choose_meme);
        mEditMemeButton = (Button) findViewById(R.id.btn_edit_meme);
        mChooseMemeButton.setOnClickListener(onClickButton());
        mEditMemeButton.setOnClickListener(onClickButton());

        editMemeToolbar = (Toolbar) findViewById(R.id.edit_activity_toolbar);
        editMemeToolbar.setTitle("Edit your meme");
        editMemeToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(editMemeToolbar);

        VanillaMemeWrapper vanillaMemeWrapper = new VanillaMemeWrapper();

        startFragment(savedInstanceState, vanillaMemeWrapper.getFragment());
        setUpRecyclerView(vanillaMemeWrapper);


//        mNextActivityButton.setOnClickListener(onClick());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next:
                Intent intent = new Intent(getApplicationContext(), SaveMemeActivity.class);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpRecyclerView(VanillaMemeWrapper vanillaMemeWrapper) {
        mRecyclerView = (RecyclerView) findViewById(R.id.meme_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        fragmentAdapter = new FragmentAdapter(this);
        ((FragmentAdapter) fragmentAdapter).addMemeWrapper(vanillaMemeWrapper);
        ((FragmentAdapter) fragmentAdapter).addMemeWrapper(new DragMemeWrapper(mMemeImageBitmap));
        ((FragmentAdapter) fragmentAdapter).addMemeWrapper(new ExpectationMemeWrapper());
        ((FragmentAdapter) fragmentAdapter).addMemeWrapper(new DrawMemeWrapper());
        mRecyclerView.setAdapter(fragmentAdapter);
        mEditAdapter = new EditVanillaMemeAdapter((VanillaMemeListener) vanillaMemeWrapper.getFragment());
    }

    private View.OnClickListener onClickButton() {
        return view -> {
            switch (view.getId()) {
                case R.id.btn_choose_meme:
                    mRecyclerView.setAdapter(fragmentAdapter);
                    break;
                case R.id.btn_edit_meme:
                    mRecyclerView.setAdapter(mEditAdapter);
            }
        };
    }

    private void startFragment(Bundle savedInstanceState, Fragment memeFragment) {
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.meme_overlay_fragment, memeFragment)
                    .commit();
        }
    }

    private View.OnClickListener onClick() {
        return view -> {
            Intent intent = new Intent(getApplicationContext(), SaveMemeActivity.class);
            startActivity(intent);
        };
    }

    @Override
    public void setEditMemeAdapter(RecyclerView.Adapter editMemeAdapter) {
        if(editMemeAdapter != null) {
            mEditAdapter = editMemeAdapter;
            mEditMemeButton.setEnabled(true);
        }else{
            System.out.println("no adapter");
            mEditMemeButton.setEnabled(false);
        }
    }

    @Override
    public void swapFragment(Fragment memeFragment) {
        startFragment(null, memeFragment);
    }


    private void loadStockImage() {
        Intent intent = getIntent();
        memeURL = intent.getStringExtra("urlMe");
        Picasso.with(this)
                .load(memeURL)
                .into(memeImage);
    }
}
