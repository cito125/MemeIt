package com.example.andresarango.memeit.viewpager.tabfragments.stock_pic_rv;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.andresarango.memeit.EditMemeActivity;
import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.model.imgflip_json.Meme;
import com.squareup.picasso.Picasso;

/**
 * Created by helenchan on 1/19/17.
 */
public class StockMemeViewHolder extends RecyclerView.ViewHolder {
    private ImageView stockMemeIV;
    String stockMemeURL;

    public StockMemeViewHolder(View itemView) {
        super(itemView);
        stockMemeIV = (ImageView) itemView.findViewById(R.id.stock_meme_iv);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), EditMemeActivity.class);
                intent.putExtra("urlMe", stockMemeURL);
                intent.putExtra("TypeOfPicture", 3);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Meme meme) {
        stockMemeURL = meme.getUrl();
        Picasso.with(itemView.getContext())
                .load(stockMemeURL)
                .fit()
                .into(stockMemeIV);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), EditMemeActivity.class);
                intent.putExtra("MemeUrl", meme.getUrl());
                itemView.getContext().startActivity(intent);
            }
        });
    }

}
