package com.example.andresarango.memeit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class EditMemeActivity extends AppCompatActivity {
    private RecyclerView memeRv;
    private ImageView memeImage;
    private Button nextButton;
    private String memeURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);
        initialize();
        showPicture();
    }

    private void initialize() {
        memeImage = (ImageView) findViewById(R.id.meme_image);
        nextButton = (Button) findViewById(R.id.next_button);
        memeRv = (RecyclerView) findViewById(R.id.meme_rv);
        memeRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        nextButton.setOnClickListener(onClick());
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

    private void showPicture() {
        Bitmap picture = getIntent().getParcelableExtra("BitmapCamera");
        if (picture != null) {
            memeImage.setImageBitmap(picture);
        }
    }

    private void loadStockImage(){
        Intent intent = getIntent();
        memeURL = intent.getStringExtra("urlMe");
        Picasso.with(this)
                .load(memeURL)
                .into(memeImage);
    }
}
