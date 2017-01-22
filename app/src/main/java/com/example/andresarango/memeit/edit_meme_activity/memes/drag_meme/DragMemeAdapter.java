package com.example.andresarango.memeit.edit_meme_activity.memes.drag_meme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.andresarango.memeit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dannylui on 1/21/17.
 */

public class DragMemeAdapter extends RecyclerView.Adapter<DragMemeAdapter.DragMemeViewHolder> {
    private List<Integer> faces = new ArrayList<>();
    private MySurfaceView mySurfaceView = null;

    public DragMemeAdapter(MySurfaceView mySurfaceView, List<Integer> faces) {
        this.mySurfaceView = mySurfaceView;
        this.faces = faces;
    }

    @Override
    public DragMemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_drag_meme, parent, false);
        return new DragMemeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DragMemeViewHolder holder, int position) {
        holder.bind(faces.get(position));
    }

    @Override
    public int getItemCount() {
        return faces.size();
    }

    public class DragMemeViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIv;

        public DragMemeViewHolder(View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.icon_image);
        }

        public void bind(final int imageDrawableId) {
            iconIv.setImageResource(imageDrawableId);

            final Bitmap icon = BitmapFactory.decodeResource(itemView.getResources(), imageDrawableId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mySurfaceView.addIcon(icon);
                }
            });
        }
    }
}
