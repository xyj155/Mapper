package com.example.administrator.mapper;

import android.app.Application;

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

}
