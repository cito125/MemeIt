package com.example.andresarango.memeit.leigh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.andresarango.memeit.R;

/**
 * Created by leighdouglas on 1/21/17.
 */

public class DrawViewHolder extends RecyclerView.ViewHolder {
    ImageView buttonImage;

    public DrawViewHolder(View itemView) {
        super(itemView);
        buttonImage = (ImageView) itemView.findViewById(R.id.drawing_icon);
    }

    public void bind(int i){
        switch (i){
            case 0:
                buttonImage.setImageResource(R.drawable.signs);
                buttonImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DrawingView.drawingView.undo();
                    }
                });
                break;
            case 1:
                buttonImage.setImageResource(R.drawable.scribble);
                buttonImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case 2:
                buttonImage.setImageResource(R.drawable.undoarrow);
                buttonImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        }
    }
}
