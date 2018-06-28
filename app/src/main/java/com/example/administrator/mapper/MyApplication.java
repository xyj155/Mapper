package com.example.administrator.mapper;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

/**
 * Created by Administrator on 2018/6/27.
 */

public class MyApplication extends Application {
    public static MyApplication application;
    public static MyApplication getInstance() {
        if (application==null){
            return application=new MyApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        SpeechUtility.createUtility(MyApplication.this, "appid=5a461a15");
        super.onCreate();
    }
}
