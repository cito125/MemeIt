package com.example.andresarango.memeit;

import android.app.Application;

import com.adobe.creativesdk.aviary.IAdobeAuthRedirectCredentials;
import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

/**
 * Created by andresarango on 1/20/17.
 */

public class MainApplication extends Application implements IAdobeAuthClientCredentials, IAdobeAuthRedirectCredentials {

    /* Be sure to fill in the two strings below. */
    private static final String CREATIVE_SDK_CLIENT_ID = "d3fb97c74ed4430f858a508781218617";
    private static final String CREATIVE_SDK_CLIENT_SECRET = "d36a6ae8-6950-4997-b082-a7d189a62309";
    private static final String CREATIVE_SDK_REDIRECT_URI = "ams+7d6225511e55b0c490b0c71b35b81f71b636d5a9://adobeid/d3fb97c74ed4430f858a508781218617";

    @Override
    public void onCreate() {
        super.onCreate();
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
    }

    @Override
    public String getClientID() {
        return CREATIVE_SDK_CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CREATIVE_SDK_CLIENT_SECRET;
    }

    @Override
    public String getRedirectUri() {
        return CREATIVE_SDK_REDIRECT_URI;
    }
}
