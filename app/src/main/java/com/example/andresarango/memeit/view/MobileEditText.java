package com.example.andresarango.memeit.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.example.andresarango.memeit.R;


/**
 * Created by andresarango on 1/20/17.
 */

public class MobileEditText extends EditText {


    private final String mHintString = "ENTER TEXT";
    private float dX;
    private float dY;
    View mParentView;

    public MobileEditText(Context context, View view) {
        super(context);
        mParentView = view;
        setHint(mHintString);
        setBackground(null);
    }

}
