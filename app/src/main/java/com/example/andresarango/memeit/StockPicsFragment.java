package com.example.andresarango.memeit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.network.Meme;
import com.example.andresarango.memeit.network.MemeResponse;
import com.example.andresarango.memeit.network.MemeService;
import com.example.andresarango.memeit.stockPicsRecyclerView.StockMemeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StockPicsFragment extends Fragment {
    private RecyclerView recyclerView;
    private StockMemeAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private Context context;
    List<Meme> memesList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.fragment_stock_pics, container, false);
        context = mRoot.getContext();
        recyclerView = (RecyclerView) mRoot.findViewById(R.id.stockpic_recyclerview);
        mLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new StockMemeAdapter(memesList);
        recyclerView.setAdapter(adapter);
        return mRoot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getStockMemes();
    }

    public void getStockMemes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgflip.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MemeService service = retrofit.create(MemeService.class);
        Call<MemeResponse> call = service.getMemememe();
        call.enqueue(new Callback<MemeResponse>() {
            @Override
            public void onResponse(Call<MemeResponse> call, Response<MemeResponse> response) {
                memesList = response.body().getData().getMemes();
                recyclerView.setAdapter(new StockMemeAdapter(memesList));
                adapter.notifyDataSetChanged();
//                Log.d("response", "in response");
            }

            @Override
            public void onFailure(Call<MemeResponse> call, Throwable t) {
                Log.d("response", "in fail");
            }
        });

    }
}
