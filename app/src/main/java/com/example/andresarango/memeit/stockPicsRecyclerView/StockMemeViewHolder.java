package com.example.andresarango.memeit.stockPicsRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.network.Meme;
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
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(itemView.getContext(), MemeActivity.class);
//                intent.putExtra("urlMe", stockMemeURL);
//                itemView.getContext().startActivity(intent);
//            }
//        });
    }

    public void bind(Meme meme) {
        stockMemeURL = meme.getUrl();
        Picasso.with(itemView.getContext())
                .load(stockMemeURL)
                .fit()
                .into(stockMemeIV);
    }

}
