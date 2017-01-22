package com.example.andresarango.memeit;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.andresarango.memeit.edit_meme_activity.memes.FragmentAdapter;
import com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme.DragMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme.DrawMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.expectation_meme.ExpectationMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.VanillaMemeListener;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.VanillaMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.adapter.EditVanillaMemeAdapter;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;
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
    private RelativeLayout rl;

    private Bitmap mMemeImageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);

        initialize(savedInstanceState);

        Intent intent = getIntent();
        int picKey = intent.getIntExtra("TypeOfPicture",5);
        if(picKey == 1){
            mMemeImageBitmap = getBitmapFromUri(getIntent().getStringExtra("ImageString"));
            memeImage.setImageBitmap(mMemeImageBitmap);
        }
        if(picKey == 0){
            String newString = intent.getStringExtra("CameraPhotoUri");
            mMemeImageBitmap = getBitmapFromUri(newString);
            memeImage.setImageBitmap(mMemeImageBitmap);
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

        rl = (RelativeLayout) findViewById(R.id.meme_to_be_saved);

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

                Thread thread = new
                        Thread(new Runnable() {
                    @Override
                    public void run() {
                        ActivityCompat.requestPermissions(EditMemeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1 );

                        rl.setDrawingCacheEnabled(true);
                        Bitmap b = rl.getDrawingCache();

                        String uri = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), b, "image1", "an image");
                        Log.d("This is the uri", uri);

                        MemeDatabaseHelper memeDatabaseHelper = MemeDatabaseHelper.getInstance(getApplicationContext());
                        SQLiteDatabase sqLiteDatabase = memeDatabaseHelper.getSQLiteDatabase(memeDatabaseHelper);
                        memeDatabaseHelper.addMeme(new MemeURI(uri), sqLiteDatabase);

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(uri));  //need to add URI
                        String shareBody = "Put text here"; //This is optional not needed if you want to post something with image
                        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(intent, "Share via"));

                    }
                });

                thread.start();


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
