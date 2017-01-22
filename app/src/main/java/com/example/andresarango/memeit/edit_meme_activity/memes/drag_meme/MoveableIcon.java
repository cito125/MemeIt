package com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme;

import android.graphics.Bitmap;

/**
 * Created by dannylui on 1/21/17.
 */

public class MoveableIcon {
    private Bitmap bitmap;
    private float xPos;
    private float yPos;
    private boolean active;

    public MoveableIcon(Bitmap bitmap, float xPos, float yPos, boolean active) {
        this.bitmap = bitmap;
        this.xPos = xPos;
        this.yPos = yPos;
        this.active = active;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
