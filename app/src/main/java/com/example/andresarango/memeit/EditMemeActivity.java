package com.example.andresarango.memeit;

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

public class EditMemeActivity extends AppCompatActivity {

    private RecyclerView memeRv;
    private ImageView memeImage;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meme);
        initialize();
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

}
