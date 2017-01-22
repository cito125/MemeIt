package com.example.andresarango.memeit.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.andresarango.memeit.EditMemeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leighdouglas on 1/20/17.
 */

public class DrawingView extends View  {

    private Path path;
    private Paint paint;
    private Bitmap mBitmap;

    private Canvas mCanvas;

    private List<Path> moves = new ArrayList<>();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
        path = new Path();

    }

    public void setBitmap(Bitmap bitmap){
        mBitmap = bitmap;
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }

    private void setUpPaint() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        mCanvas = new Canvas();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(path, paint);

        for (Path path : moves) {
            canvas.drawPath(path, paint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xCord = event.getX();
        float yCord = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xCord, yCord);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xCord, yCord);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(xCord, yCord);
                mCanvas.drawPath(path, paint);
                moves.add(path);
                path = new Path();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

//    @Override
//    public void undo() {
//        if(moves.size() > 0){
//            moves.remove(moves.size()-1);
//            invalidate();
//        }
//    }
}
