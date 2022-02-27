package com.deskconn.toponandroiddemo;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATAdStatusInfo;
import com.anythink.core.api.AdError;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialListener;

public class InterstitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        ATInterstitial mInterstitialAd = new ATInterstitial(this, "b5baca54674522");
        mInterstitialAd.setAdListener(new ATInterstitialListener() {
            @Override
            public void onInterstitialAdLoaded() {
                Log.e(TAG, "Ad loaded");
                Toast.makeText(InterstitialActivity.this, "Add loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdLoadFail(AdError adError) {
                Log.e(TAG, "onInterstitialAdLoadFail:" + adError.getFullErrorInfo());
                Toast.makeText(InterstitialActivity.this, "Error:  " + adError, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo atAdInfo) {
                Log.e(TAG, "Ad Clicked");
                Toast.makeText(InterstitialActivity.this, "Ad clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo atAdInfo) {
                Log.e(TAG, "Ad shown");
                Toast.makeText(InterstitialActivity.this, "Ad show", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo atAdInfo) {
                Log.e(TAG, "Ad close");
                Toast.makeText(InterstitialActivity.this, "Ad close", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {
                Log.e(TAG, "Ad video start");
                Toast.makeText(InterstitialActivity.this, "Ad video start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {
                Log.e(TAG, "Ad video end");
                Toast.makeText(InterstitialActivity.this, "Ad video end", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdVideoError(AdError adError) {
                Log.e(TAG, "Ad video error");
                Toast.makeText(InterstitialActivity.this, "Ad video error", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.is_ad_ready_btn).setOnClickListener(v -> {
            ATAdStatusInfo atAdStatusInfo = mInterstitialAd.checkAdStatus();
            Toast.makeText(InterstitialActivity.this, "interstitial ad ready status:" + atAdStatusInfo.isReady(), Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.loadAd_btn).setOnClickListener(v -> mInterstitialAd.load());

        findViewById(R.id.show_ad_btn).setOnClickListener(v -> mInterstitialAd.show(InterstitialActivity.this));
    }
}