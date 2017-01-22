package com.example.andresarango.memeit;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.andresarango.memeit.edit_meme_activity.memes.FragmentAdapter;
import com.example.andresarango.memeit.edit_meme_activity.memes.VanillaMemeListener;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.VanillaMemeWrapper;
import com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme.adapter.EditVanillaMemeAdapter;
import com.example.andresarango.memeit.edit_meme_activity.utility.EditorViewHolder;

import java.io.IOException;

public class EditMemeActivity extends AppCompatActivity implements EditorViewHolder.Listener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter fragmentAdapter;
    private ImageView memeImage;
    private Button mNextActivityButton;
    private Button mChooseMemeButton;
    private Button mEditMemeButton;
    private RecyclerView.Adapter mEditAdapter;
    private Toolbar editMemeToolbar;
    private RelativeLayout rl;

    private Bitmap memeImageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);

        memeImageBitmap = getBitmapFromUri(getIntent().getStringExtra("ImageString"));

//        How to make danny meme fragment below, make instance of my fragment with bitmap and inflate it
//        DragMemeFragment dragMemeFragment = DragMemeFragment.newInstance(memeImageBitmap);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.activity_create_meme, dragMemeFragment)
//                .commit();

        initialize(savedInstanceState);
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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rl.setDrawingCacheEnabled(true);
                        Bitmap b = rl.getDrawingCache();

                        String uri = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), b, "image1", "an image");

                        MemeDatabaseHelper memeDatabaseHelper = MemeDatabaseHelper.getInstance(getApplicationContext());
                        SQLiteDatabase sqLiteDatabase = memeDatabaseHelper.getSQLiteDatabase(memeDatabaseHelper);
                        memeDatabaseHelper.addMeme(new MemeURI(uri), sqLiteDatabase);

                    }
                });

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                //intent.putExtra(Intent.EXTRA_STREAM, pictureUri);  //need to add URI
                String shareBody = "Put text here"; //This is optional not needed if you want to post something with image
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share via"));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpRecyclerView(VanillaMemeWrapper vanillaMemeWrapper) {
        mRecyclerView = (RecyclerView) findViewById(R.id.meme_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        fragmentAdapter = new FragmentAdapter(this);
        ((FragmentAdapter) fragmentAdapter).addMemeWrapper(vanillaMemeWrapper);
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
        mEditAdapter = editMemeAdapter;
    }

    @Override
    public void swapFragment(Fragment memeFragment) {
        startFragment(null, memeFragment);
    }

    private void showPicture() {
        Bitmap picture = getIntent().getParcelableExtra("BitmapCamera");
        Uri pictureUri = Uri.parse(getIntent().getStringExtra("CameraPhotoUri"));
        if (picture != null) {
            memeImage.setImageBitmap(picture);
        }
    }
}
