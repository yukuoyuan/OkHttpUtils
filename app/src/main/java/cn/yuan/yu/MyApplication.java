package cn.yuan.yu;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.yuan.yu.library.OkHttpUtilsConfig;

/**
 * Created by yukuoyuan on 2017/3/14.
 */

public class MyApplication extends Application {
    private static   Handler mainHandler;
    private static Context instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainHandler = new Handler();
        /**
         * 初始化okhttputils
         */
        OkHttpUtilsConfig.getInstance().init(false, "resultcode", "100", "msg", mainHandler,this);
    }
}
