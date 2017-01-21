package com.example.andresarango.memeit.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;


/**
 * Created by andresarango on 1/20/17.
 */

public class MobileTextView extends TextView implements View.OnTouchListener, View.OnLongClickListener{

    private final View mParentView;

    public MobileTextView(Context context) {
        this(context,null);
    }
    public MobileTextView(Context context, View parentView){
        super(context);
        mParentView = parentView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        drag(motionEvent, view);
        return true;
    }

    public void drag(MotionEvent event, View v) {

        RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) v.getLayoutParams();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                params.topMargin = (int) event.getRawY() - (v.getHeight());
                params.leftMargin = (int) event.getRawX() - (v.getWidth() / 2);
                v.setLayoutParams(params);
                break;
            }
            case MotionEvent.ACTION_UP: {
                params.topMargin = (int) event.getRawY() - (v.getHeight());
                params.leftMargin = (int) event.getRawX() - (v.getWidth() / 2);
                v.setLayoutParams(params);
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                v.setLayoutParams(params);
                break;
            }
        }
        mParentView.invalidate();

    }

    @Override
    public boolean onLongClick(View view) {
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(selectedColor -> toast("onColorSelected: 0x" + Integer.toHexString(selectedColor)))
                .setPositiveButton("ok", (dialog, selectedColor, allColors) -> setTextColor(selectedColor))
                .setNegativeButton("cancel", (dialog, which) -> {})
                .build()
                .show();
        return true;
    }

    private void toast(String string) {
        Toast.makeText(getContext(), string,
                Toast.LENGTH_LONG).show();
    }


}
