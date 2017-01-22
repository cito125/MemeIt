package com.example.andresarango.memeit.viewpager.tabfragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.andresarango.memeit.EditMemeActivity;
import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.viewpager.ViewPagerAdapter;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static com.example.andresarango.memeit.R.drawable.picture;

/**
 * Created by dannylui on 1/18/17.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
   // private static final int PHOTO_ID = 22;
    private View rootView;
    LinearLayout createMemeFromCamera;
    LinearLayout makeMemeFromGalleryButton;
    LinearLayout createMemeFromPopularButton;
    private int PICK_IMAGE_REQUEST = 1;

    public final String APP_TAG = "MyCustomApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        initializeButtons(rootView);
        return rootView;
    }

    private void initializeButtons(View rootView) {
        createMemeFromCamera = (LinearLayout) rootView.findViewById(R.id.make_meme_from_camera);
        createMemeFromCamera.setOnClickListener(this);
        makeMemeFromGalleryButton = (LinearLayout) rootView.findViewById(R.id.make_meme_from_gallery_button);
        makeMemeFromGalleryButton.setOnClickListener(this);
        createMemeFromPopularButton = (LinearLayout) rootView.findViewById(R.id.make_meme_popular_image_button);
        createMemeFromPopularButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.make_meme_from_camera:
                // create Intent to take a picture and return control to the calling application
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

                // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                // So as long as the result is not null, it's safe to use the intent.
                if (camIntent.resolveActivity(rootView.getContext().getPackageManager()) != null) {
                    // Start the image capture intent to take photo
                    startActivityForResult(camIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
                break;
            case R.id.make_meme_from_gallery_button:
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
            case R.id.make_meme_popular_image_button:
                ViewPagerAdapter.vpInstance.setStockFragment();
                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // From gallery
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Intent intent = new Intent(rootView.getContext(), EditMemeActivity.class);
            intent.putExtra("ImageString", uri.toString());
            intent.putExtra("TypeOfPicture", 1);
            startActivity(intent);
        }

        // From camera
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                Intent intent = new Intent(rootView.getContext(), EditMemeActivity.class);
                intent.putExtra("CameraPhotoUri", takenPhotoUri.toString());
                intent.putExtra("BitmapCamera", picture);
                intent.putExtra("TypeOfPicture", 0);
                startActivity(intent);
            } else { // Result was a failure
                Toast.makeText(rootView.getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Returns the Uri for a photo stored on disk given the fileName
    public Uri getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(
                    rootView.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                Log.d(APP_TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    // Returns true if external storage for photos is available
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
