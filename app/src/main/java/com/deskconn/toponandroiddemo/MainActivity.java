package com.deskconn.toponandroiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.interstitial_button).setOnClickListener(view -> startActivity(new Intent(this, InterstitialActivity.class)));

        findViewById(R.id.banner_button).setOnClickListener(view -> startActivity(new Intent(this, BannerAdsActivity.class)));

        findViewById(R.id.native_button).setOnClickListener(view -> startActivity(new Intent(this, NativeBannerActivity.class)));
    }
}