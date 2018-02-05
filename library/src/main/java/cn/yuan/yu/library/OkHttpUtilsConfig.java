package cn.yuan.yu.library;

import android.content.Context;
import android.os.Handler;

/**
 * Created by yukuoyuan on 2017/3/14.
 * 这是一个okhttp的配置类
 */

public class OkHttpUtilsConfig {
    //是否提交json数据//否的话使用表单提交的形式
    private boolean isPostJson = false;
    //用于同步线程
    private Handler mainHandler;
    //是否展示和日志
    private boolean isShowLog;
    //上下文
    private Context context;

    /**
     * 设置是否使用提交json的形式
     * 不设置的话则使用提交表单的形式提交
     *
     * @param postJson 是否
     */
    public void setPostJson(boolean postJson) {
        isPostJson = postJson;
    }


    /**
     * 这是一个初始化okhttputils的方法
     */
    public void init(boolean postJson, Handler mainHandler, Context context) {
        setPostJson(postJson);

        setMainHandler(mainHandler);
        setContext(context);
    }
    public boolean isPostJson() {
        return isPostJson;
    }


    public Handler getMainHandler() {
        return mainHandler;
    }

    public void setMainHandler(Handler mainhandler) {
        this.mainHandler = mainhandler;
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public void setShowLog(boolean showLog) {
        isShowLog = showLog;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private static OkHttpUtilsConfig sInstance;

    private OkHttpUtilsConfig() {
    }

    public static OkHttpUtilsConfig getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpUtilsConfig.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpUtilsConfig();
                }
            }
        }
        return sInstance;
    }

}
