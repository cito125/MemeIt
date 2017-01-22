package com.example.andresarango.memeit.edit_meme_activity.memes.vanilla_meme;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.edit_meme_activity.memes.VanillaMemeListener;
import com.example.andresarango.memeit.view.MobileEditText;
import com.example.andresarango.memeit.view.MobileTextView;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by andresarango on 1/20/17.
 */

public class VanilleMemeFragment extends Fragment implements VanillaMemeListener {
    List<MobileEditText> mMobileTextViewList = new ArrayList<>();
    private float dX;
    private float dY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vanilla_meme,container,false);
        return rootView;
    }

    @Override
    public void addMobileEditText() {
        final ViewGroup rootView = (ViewGroup) getView().findViewById(R.id.fragement_vanilla_meme);
        rootView.setOnClickListener(view -> rootView.requestFocus());
        final MobileEditText mobileEditText = new MobileEditText(getActivity(), getView().findViewById(R.id.activity_main));
        mobileEditText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mobileEditText.setOnLongClickListener(view -> {
            mobileEditText.setOnTouchListener((view1, motionEvent) -> {
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = view1.getX() - motionEvent.getRawX();
                        dY = view1.getY() - motionEvent.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        view1.animate()
                                .x(motionEvent.getRawX() + dX)
                                .y(motionEvent.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    default:
                        return false;
                }
                return true;
            });
            return true;
        });
        mobileEditText.setOnClickListener(view -> mobileEditText.setEnabled(true));
        mMobileTextViewList.add(mobileEditText);

        rootView.addView(mobileEditText);
    }

    @Override
    public void changeTextColor() {
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Choose color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(selectedColor -> toast("onColorSelected: 0x" + Integer.toHexString(selectedColor)))
                .setPositiveButton("ok", (dialog, selectedColor, allColors) -> updateTextColor(selectedColor))
                .setNegativeButton("cancel", (dialog, which) -> {})
                .build()
                .show();
    }

    private void updateTextColor(int selectedColor) {
        for (int i = 0; i < mMobileTextViewList.size() ; i++) {
            mMobileTextViewList.get(i).setTextColor(selectedColor);
        }
    }

    private void toast(String string) {
        Toast.makeText(getActivity(), string,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeTextSize() {

    }
}
