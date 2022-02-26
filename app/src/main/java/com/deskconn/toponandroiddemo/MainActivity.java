package com.deskconn.toponandroiddemo;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.anythink.banner.api.ATBannerListener;
import com.anythink.banner.api.ATBannerView;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.frame);

        ATBannerView mBannerView = new ATBannerView(MainActivity.this);
        mBannerView.setPlacementId("b5baca41a2536f");

        int width = getResources().getDisplayMetrics().widthPixels;//Set a width value, such as screen width
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;

        mBannerView.setLayoutParams(new FrameLayout.LayoutParams(width, height));

        frameLayout.addView(mBannerView);
        mBannerView.setBannerAdListener(new ATBannerListener() {
            @Override
            public void onBannerLoaded() {
            }

            @Override
            public void onBannerFailed(AdError adError) {
                //Note: Do not perform the retry loading method ad in this callback, otherwise it will cause a lot of useless requests and may cause the application to run slowly
                //AdError，please refer to https://docs.toponad.com/#/en-us/android/android_doc/android_test?id=aderror
                Log.e(TAG, "onBannerFailed:" + adError.getFullErrorInfo());
            }

            @Override
            public void onBannerClicked(ATAdInfo atAdInfo) {
            }

            @Override
            public void onBannerShow(ATAdInfo atAdInfo) {
                //ATAdInfo can distinguish between advertising platforms and obtain the advertising slot ID of the advertising platform, etc.
                //please refer to https://docs.toponad.com/#/en-us/android/android_doc/android_access_doc?id=callback_info
            }

            @Override
            public void onBannerClose(ATAdInfo atAdInfo) {
            }

            @Override
            public void onBannerAutoRefreshed(ATAdInfo atAdInfo) {
            }

            @Override
            public void onBannerAutoRefreshFail(AdError adError) {
                //AdError，please refer to https://docs.toponad.com/#/en-us/android/android_doc/android_test?id=aderror
                Log.e(TAG, "onBannerAutoRefreshFail:" + adError.getFullErrorInfo());
            }
        });

        findViewById(R.id.ad_button).setOnClickListener(view -> mBannerView.loadAd());
    }
}