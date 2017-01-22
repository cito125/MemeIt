package com.example.andresarango.memeit.edit_meme_activity.memes.draw_meme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.andresarango.memeit.R;

/**
 * Created by leighdouglas on 1/21/17.
 */

public class EditDrawViewHolder extends RecyclerView.ViewHolder {
    Listener listener;
    ImageView buttonImage;

    public EditDrawViewHolder(View itemView) {
        this(itemView, null);
    }

    public EditDrawViewHolder(View itemView, Listener listener){
        super(itemView);
        buttonImage = (ImageView) itemView.findViewById(R.id.drawing_icon);
        this.listener = listener;

    }

    public void bind(int i){
        switch (i){
            case 0:
                buttonImage.setImageResource(R.drawable.signs);
                buttonImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                        listener.undo();

                    }
                });
        }
    }

    public interface Listener{
        void undo();
    }
}
