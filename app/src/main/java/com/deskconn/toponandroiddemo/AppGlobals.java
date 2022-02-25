package com.deskconn.toponandroiddemo;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;

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

    }
}
