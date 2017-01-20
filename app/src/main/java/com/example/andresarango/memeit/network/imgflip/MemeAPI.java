package com.example.andresarango.memeit.network.imgflip;

import com.example.andresarango.memeit.model.imgflip_json.MemeResponse;
import com.example.andresarango.memeit.network.NetworkServices;

import retrofit2.Call;

/**
 * Created by andresarango on 1/19/17.
 */

public class MemeAPI {
//    https://api.imgflip.com/
    private String BASE_URL = "https://api.imgflip.com/";
    private final MemeService mMemeService;
    private static MemeAPI instance;

    public static MemeAPI getInstance(){
        if(instance == null){
            instance = new MemeAPI();
        }
        return instance;
    }

    private MemeAPI(){
        mMemeService = (new NetworkServices()).getJSONService(BASE_URL,MemeService.class);
    }

    public Call<MemeResponse> getMemeResponse(){
       return mMemeService.getMemememe();
    }

}
