package cn.yuan.yu.library;

import android.content.Context;
import android.os.Handler;

/**
 * Created by yukuoyuan on 2017/3/14.
 * 这是一个okhttp的配置类
 */

public class OkHttpUtilsConfig {
    //返回编码key
    private String ResultCodeKey = "";
    //返回编码正确值
    private String ResultCodeValue = "";
    //返回错误信息值
    private String ResultMsgKey = "";
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
     * 设置返回的编码的key
     *
     * @param resultCodeKey key
     */
    public void setResultCodeKey(String resultCodeKey) {
        ResultCodeKey = resultCodeKey;
    }

    /**
     * 设置返回正确的正确的value
     *
     * @param resultCodeValue
     */
    public void setResultCodeValue(String resultCodeValue) {
        ResultCodeValue = resultCodeValue;
    }

    /**
     * 设置返回信息的字段key
     *
     * @param resultMsgKey key
     */
    public void setResultMsgKey(String resultMsgKey) {
        ResultMsgKey = resultMsgKey;
    }

    public boolean isPostJson() {
        return isPostJson;
    }


    public String getResultCodeKey() {
        return ResultCodeKey;
    }


    public String getResultCodeValue() {
        return ResultCodeValue;
    }


    public String getResultMsgKey() {
        return ResultMsgKey;
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

    /**
     * 这是一个初始化okhttputils的方法
     */
    public void init(boolean postJson, String resultCodeKey, String resultCodeValue, String resultMsgKey, Handler mainHandler, Context context) {
        setPostJson(postJson);
        setResultCodeKey(resultCodeKey);
        setResultCodeValue(resultCodeValue);
        setResultMsgKey(resultMsgKey);
        setMainHandler(mainHandler);
        setContext(context);
    }
}
