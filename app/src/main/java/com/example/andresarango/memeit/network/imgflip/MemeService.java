package com.example.andresarango.memeit.network.imgflip;

import com.example.andresarango.memeit.modello.MemeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by helenchan on 1/19/17.
 */

public interface MemeService {
    @GET("get_memes")
    Call<MemeResponse> getMemememe();
}
