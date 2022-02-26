package com.deskconn.toponandroiddemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.anythink.core.api.ATAdInfo;
import com.anythink.nativead.banner.api.ATNativeBannerConfig;
import com.anythink.nativead.banner.api.ATNativeBannerListener;
import com.anythink.nativead.banner.api.ATNativeBannerSize;
import com.anythink.nativead.banner.api.ATNativeBannerView;

import java.util.HashMap;
import java.util.Map;

public class NativeBannerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_native_banner);


        final LinearLayout frameLayout = findViewById(R.id.native_banner_frame);


        final ATNativeBannerView bannerViewAuto = new ATNativeBannerView(this);
        bannerViewAuto.setUnitId("b5aa1fa501d9f6");
        bannerViewAuto.setVisibility(View.GONE);
        ATNativeBannerConfig configAuto = new ATNativeBannerConfig();
        configAuto.bannerSize = ATNativeBannerSize.BANNER_SIZE_AUTO;
        configAuto.isCtaBtnShow = true;
        configAuto.ctaBgColor = 0xff000000;
        configAuto.ctaColor = 0xff00ff00;
        configAuto.titleColor = 0xffffffff;
        bannerViewAuto.setBannerConfig(configAuto);
        final Map<String, Object> localMapAuto = new HashMap<>();
        bannerViewAuto.setLocalExtra(localMapAuto);

        bannerViewAuto.setBackgroundColor(0xffffffff);
        LinearLayout.LayoutParams paramsAuto = new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 320), dip2px(getApplicationContext(), 205));
        paramsAuto.topMargin = dip2px(getApplicationContext(), 10);
        frameLayout.addView(bannerViewAuto, paramsAuto);

        bannerViewAuto.setAdListener(new ATNativeBannerListener() {
            @Override
            public void onAdLoaded() {
                Log.i("BannerActivity", "auto---onAdLoaded----");
                bannerViewAuto.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdError(String errorMsg) {
                Log.i("BannerActivity", "auto---onAdError----:" + errorMsg);
            }

            @Override
            public void onAdClick(ATAdInfo entity) {
                Log.i("BannerActivity", "auto---onAdClick:\n" + entity.toString());
            }

            @Override
            public void onAdClose() {
                Log.i("BannerActivity", "auto---onAdClose----");
            }

            @Override
            public void onAdShow(ATAdInfo entity) {
                Log.i("BannerActivity", "auto---onAdShow:\n" + entity.toString());
            }

            @Override
            public void onAutoRefresh(ATAdInfo entity) {
                Log.i("BannerActivity", "auto---onAutoRefresh:\n" + entity.toString());
            }

            @Override
            public void onAutoRefreshFail(String errorMsg) {
                Log.i("BannerActivity", "auto---onAutoRefreshFail:" + errorMsg);
            }
        });

        findViewById(R.id.load_native_banner).setOnClickListener(view -> bannerViewAuto.loadAd(null));

        findViewById(R.id.remove_native_banner).setOnClickListener(view -> bannerViewAuto.setVisibility(View.INVISIBLE));
    }

    public int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
