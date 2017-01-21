package com.example.andresarango.memeit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by leighdouglas on 1/20/17.
 */

public class SaveMemeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_meme);
    }

    private void sharePic() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
//        intent.putExtra(Intent.EXTRA_STREAM, pictureUri);  //need to add URI
        String shareBody = "Put text here"; //This is optional not needed if you want to post something with image
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
