package com.example.andresarango.memeit;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class EditMemeActivity extends AppCompatActivity {

    private RecyclerView memeRv;
    private ImageView memeImage;
    private Button nextButton;
    private Button openDrawingMeme;
    private RelativeLayout memeToBeSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);

        Intent intent = getIntent();
        String uriString = intent.getStringExtra("ImageString");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        memeImage = (ImageView) findViewById(R.id.meme_image);
        memeToBeSaved = (RelativeLayout) findViewById(R.id.meme_to_be_saved);

        Glide
                .with(getApplicationContext())
                .load(uriString)
                .crossFade()
                .centerCrop()
                .into(memeImage);


        nextButton = (Button) findViewById(R.id.next_button);
        openDrawingMeme = (Button) findViewById(R.id.open);
        openDrawingMeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.meme_overlay_fragment, new DrawingMemeFragment())
                        .commit();
            }
        });

        memeRv = (RecyclerView) findViewById(R.id.meme_rv);
        memeRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                memeToBeSaved.setDrawingCacheEnabled(true);
                Bitmap b = memeToBeSaved.getDrawingCache();
                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), b, "image1", "an image");
                Log.d("path", path);
                Intent intent = new Intent(getApplicationContext(), SaveMemeActivity.class);
                startActivity(intent);
            }
        });
    }

}
