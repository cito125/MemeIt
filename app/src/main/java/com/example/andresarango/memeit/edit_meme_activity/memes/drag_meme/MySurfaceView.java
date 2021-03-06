package com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.github.nisrulz.sensey.PinchScaleDetector;
import com.github.nisrulz.sensey.Sensey;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by dannylui on 1/21/17.
 */

public class MySurfaceView extends SurfaceView implements Runnable, View.OnTouchListener {
    private Stack<MoveableIcon> iconStack = new Stack<>();
    private Thread t;
    private SurfaceHolder holder;
    private boolean isViewReady = false;
    private boolean isIteratingStack = false;
    private Bitmap backgroundImage;

    private Canvas canvas = null;
    public static Bitmap lastSavedImage = null;

    public DragMemeFragment dragMemeFragment;
    private boolean isScaling = false;

    public static MySurfaceView instance;

    public MySurfaceView(Context context, Bitmap bmp, DragMemeFragment dragMemeFragment) {
        super(context);
        this.dragMemeFragment = dragMemeFragment;
        instance = this;
        backgroundImage = bmp;
        setOnTouchListener(this);
        holder = getHolder();

        setupPinchZoom(context);
    }

    public void run() {
        while (isViewReady) {
            if (!holder.getSurface().isValid()) {
                continue;
            }
            canvas = holder.lockCanvas();
            if (lastSavedImage == null) {
                lastSavedImage = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.RGB_565);
                backgroundImage = Bitmap.createScaledBitmap(backgroundImage, canvas.getWidth(), canvas.getHeight(), false);
            }
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Iterator<MoveableIcon> it = iconStack.iterator();
            while (it.hasNext()) {
                isIteratingStack = true;
                MoveableIcon moveableIcon = it.next();
                Bitmap bitmap = moveableIcon.getBitmap();
                canvas.drawBitmap(bitmap, moveableIcon.getxPos() - bitmap.getWidth() / 2, moveableIcon.getyPos() - bitmap.getHeight() / 2, null);

            }
            isIteratingStack = false;

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void addIcon(Bitmap bitmap) {
        MoveableIcon newIcon = new MoveableIcon(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        if (!iconStack.isEmpty()) {
            iconStack.peek().setActive(false);
        }
        while (true) {
            if (!isIteratingStack) {
                iconStack.push(newIcon);
                break;
            }
        }
        System.out.println(iconStack);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!isScaling) {
                    updateCurrentIconPosition(motionEvent.getX(), motionEvent.getY());
                }
                break;
        }
        return true;
    }

    private void setupPinchZoom(Context context) {
        Sensey.getInstance().init(context);

        PinchScaleDetector.PinchScaleListener pinchScaleListener = new PinchScaleDetector.PinchScaleListener() {
            @Override
            public void onScale(ScaleGestureDetector scaleGestureDetector, boolean scalingOut) {
                if (scalingOut) {
                    // Scaling Out;
                    scaleOutIcon();
                } else {
                    // Scaling In
                    scaleInIcon();
                }
            }

            @Override
            public void onScaleStart(ScaleGestureDetector scaleGestureDetector) {
                // Scaling Started
                isScaling = true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                // Scaling Stopped
                isScaling = false;
            }
        };

        Sensey.getInstance().startPinchScaleDetection(pinchScaleListener);
    }

    private void scaleOutIcon() {
        Iterator<MoveableIcon> it = iconStack.iterator();
        while (it.hasNext()) {
            MoveableIcon moveableIcon = it.next();
            if (moveableIcon.isActive()) {
                Bitmap bitmap = moveableIcon.getBitmap();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                        bitmap, bitmap.getWidth() + 10, bitmap.getHeight() + 10, true);
                moveableIcon.setBitmap(resizedBitmap);

            }
        }
    }

    private void scaleInIcon() {
        Iterator<MoveableIcon> it = iconStack.iterator();
        while (it.hasNext()) {
            MoveableIcon moveableIcon = it.next();
            if (moveableIcon.isActive()) {
                Bitmap bitmap = moveableIcon.getBitmap();
                if (bitmap.getWidth() <= 150 && bitmap.getHeight() <= 150) {
                    bitmap = Bitmap.createScaledBitmap(
                            bitmap, 150, 150, false);
                } else {
                    bitmap = Bitmap.createScaledBitmap(
                            bitmap, bitmap.getWidth() - 10, bitmap.getHeight() - 10, true);
                }
                moveableIcon.setBitmap(bitmap);

            }
        }
    }

    public void updateCurrentIconPosition(float x, float y) {
        Iterator<MoveableIcon> it = iconStack.iterator();
        while (it.hasNext()) {
            MoveableIcon currentActiveIcon = it.next();
            if (currentActiveIcon.isActive()) {
                float currentX = currentActiveIcon.getxPos();
                float currentY = currentActiveIcon.getyPos();
                Bitmap bitmap = currentActiveIcon.getBitmap();
                if (x >= currentX - bitmap.getWidth() / 2 && x <= currentX + bitmap.getWidth() / 2
                        && y >= currentY - bitmap.getHeight() / 2 && y <= currentY + bitmap.getHeight() / 2) {
                    currentActiveIcon.setxPos(x);
                    currentActiveIcon.setyPos(y);
                }
            }
        }
    }

    public void undo() {
        while (true) {
            if (!isIteratingStack) {
                if (!iconStack.isEmpty()) {
                    iconStack.pop();
                }
                if (!iconStack.isEmpty()) {
                    iconStack.peek().setActive(true);
                }
                break;
            }
        }
    }

    public void clear() {
        while (true) {
            if (!isIteratingStack) {
                iconStack.clear();
                break;
            }
        }
    }

    public void save() {
        //dragMemeFragment.readyForSave();

        canvas.setBitmap(lastSavedImage);
        canvas.drawBitmap(backgroundImage, 0, 0, null);
        Iterator<MoveableIcon> it = iconStack.iterator();
        while (it.hasNext()) {
            isIteratingStack = true;
            MoveableIcon moveableIcon = it.next();
            Bitmap bitmap = moveableIcon.getBitmap();
            canvas.setBitmap(lastSavedImage);
            canvas.drawBitmap(bitmap, moveableIcon.getxPos() - bitmap.getWidth() / 2, moveableIcon.getyPos() - bitmap.getHeight() / 2, null);
        }
    }

    public void onPause() {
        isViewReady = false;
        while (true) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        t = null;
    }

    public void onResume() {
        isViewReady = true;
        t = new Thread(this);
        t.start();
    }

    // For sensey libray to work
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // Setup onTouchEvent for detecting type of touch gesture
        Sensey.getInstance().setupDispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
}
