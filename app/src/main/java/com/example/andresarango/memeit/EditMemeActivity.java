package com.example.andresarango.memeit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

public class EditMemeActivity extends AppCompatActivity {

    private RecyclerView memeRv;
    private ImageView memeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);

        memeImage = (ImageView) findViewById(R.id.meme_image);

        memeRv = (RecyclerView) findViewById(R.id.meme_rv);
        memeRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

}
