package com.example.andresarango.memeit.viewpager.tabfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.memeit.R;
import com.example.andresarango.memeit.model.MemeResponse;
import com.example.andresarango.memeit.network.imgflip.MemeAPI;
import com.example.andresarango.memeit.viewpager.tabfragments.stock_pic_rv.StockMemeAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StockPicsFragment extends Fragment {
    private RecyclerView recyclerView;
    private StockMemeAdapter mMemeAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.fragment_stock_pics, container, false);
        setRecyclerView(mRoot);
        return mRoot;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMemeAdapter();
    }

    private void setRecyclerView(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.stockpic_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mMemeAdapter = new StockMemeAdapter();
        recyclerView.setAdapter(mMemeAdapter);
    }



    public void setMemeAdapter() {
        MemeAPI.getInstance().getMemeResponse().enqueue(new Callback<MemeResponse>() {
            @Override
            public void onResponse(Call<MemeResponse> call, Response<MemeResponse> response) {
                mMemeAdapter.setMemesList(response.body().getData().getMemes());
            }

            @Override
            public void onFailure(Call<MemeResponse> call, Throwable t) {

            }
        });


    }
}
