package com.deskconn.toponandroiddemo;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import com.anythink.core.api.ATSDK;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.Arrays;

public class AppGlobals extends Application {

    public static final String TopOnAppID = "a5aa1f9deda26d";
    public static final String TopOnAppKey = "4f7b9ac17decb9babec83aac078742c7";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName();
            if (!getPackageName().equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }

        new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("2D0FCCB7B0A1A79419173B4228BE8D72"));
        ATSDK.setNetworkLogDebug(true);//The SDK log function is recommended to be turned on during the integration test phase, and must be turned off before going online

        Log.i(TAG, "TopOn SDK version: " + ATSDK.getSDKVersionName());//SDK version

        ATSDK.integrationChecking(getApplicationContext());//Check the integration status of the advertising platform

        ATSDK.init(getApplicationContext(), TopOnAppID, TopOnAppKey);//Initialize SDK


        ATSDK.testModeDeviceInfo(this, deviceInfo -> Log.i(TAG, "deviceInfo: " + deviceInfo));
    }
}
