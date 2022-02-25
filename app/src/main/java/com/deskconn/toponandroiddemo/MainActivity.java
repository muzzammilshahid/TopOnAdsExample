package com.deskconn.toponandroiddemo;

import static android.content.ContentValues.TAG;

import static com.deskconn.toponandroiddemo.AppGlobals.TopOnAppID;
import static com.deskconn.toponandroiddemo.AppGlobals.TopOnAppKey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATSDK;
import com.anythink.core.api.AdError;
import com.anythink.core.api.DeviceInfoCallback;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("tthere it is ");
        ATSDK.setNetworkLogDebug(true);//The SDK log function is recommended to be turned on during the integration test phase, and must be turned off before going online

        Log.i(TAG, "TopOn SDK version: " + ATSDK.getSDKVersionName());//SDK version
        System.out.println("tthere  " + ATSDK.getSDKVersionName());

        ATSDK.integrationChecking(getApplicationContext());//Check the integration status of the advertising platform

        ATSDK.init(getApplicationContext(), TopOnAppID, TopOnAppKey);//Initialize SDK


        ATSDK.testModeDeviceInfo(this, new DeviceInfoCallback() {
            @Override
            public void deviceInfo(String deviceInfo) {
                System.out.println("tthere   " + deviceInfo);
                Log.i(TAG, "deviceInfo: " + deviceInfo);
            }
        });


        ATInterstitial mInterstitialAd = new ATInterstitial(this, "acaa8a8e-594b-4e5e-bacf-014bc8f49749");
        mInterstitialAd.setAdListener(new ATInterstitialListener() {
            @Override
            public void onInterstitialAdLoaded() {
                System.out.println("tthere loaded");
                mInterstitialAd.show(MainActivity.this);
            }

            @Override
            public void onInterstitialAdLoadFail(AdError adError) {
                System.out.println("tthere fail" + adError);
                //Note: Do not perform the retry loading method ad in this callback, otherwise it will cause a lot of useless requests and may cause the application to run slowly
                //AdError，please refer to https://docs.toponad.com/#/en-us/android/android_doc/android_test?id=aderror
                Log.e(TAG, "onInterstitialAdLoadFail:" + adError.getFullErrorInfo());
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo atAdInfo) {
                System.out.println("tthere clicked");
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo atAdInfo) {
                System.out.println("tthere clicked show " + atAdInfo);
                //ATAdInfo can distinguish between advertising platforms and obtain the advertising slot ID of the advertising platform, etc.
                //please refer to https://docs.toponad.com/#/en-us/android/android_doc/android_access_doc?id=callback_info
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo atAdInfo) {
                //It is recommended to call load in this callback to load the ads for the next ad display (No need to call isAdReady())
                mInterstitialAd.load();
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {
            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {
            }

            @Override
            public void onInterstitialAdVideoError(AdError adError) {
            }
        });

        findViewById(R.id.ad_button).setOnClickListener(view -> {
            if (mInterstitialAd.isAdReady()) {
                System.out.println("tthere is ready ");
                mInterstitialAd.show(this);
            } else {
                System.out.println("tthere not ready ");
                mInterstitialAd.load();
            }

        });

    }
}