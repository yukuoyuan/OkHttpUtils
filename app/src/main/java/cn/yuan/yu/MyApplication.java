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
         * 第一个参数是是否提交json数据的形式还是表单的形式
         * 第二份参数是请求返回的标识code
         * 第三个参数是请求返回的正确的code值
         * 第四个参数是请求返回的提示信息key
         * 第五个参数是为了同步线程的问题
         * 第六个参数是为了弹出提示信息的上下文
         */
        OkHttpUtilsConfig.getInstance().init(false, "resultcode", "100", "msg", mainHandler,this);
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }

    public static void setMainHandler(Handler mainHandler) {
        MyApplication.mainHandler = mainHandler;
    }

    public static Context getInstance() {
        return instance;
    }

    public static void setInstance(Context instance) {
        MyApplication.instance = instance;
    }
}
