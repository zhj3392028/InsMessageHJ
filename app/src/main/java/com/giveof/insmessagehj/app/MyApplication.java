package com.giveof.insmessagehj.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.giveof.insmessagehj.service.InsMsgService;

/**
 * Created by Time on 16/10/8.
 */
public class MyApplication extends Application {

    public static int missedCalls = 0; //未接电话

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startInsMsgService();
            }
        }).start();

    }

    private void startInsMsgService() {
        Intent intent = new Intent();
        intent.setClass(this, InsMsgService.class);
        startService(intent);
    }

}
