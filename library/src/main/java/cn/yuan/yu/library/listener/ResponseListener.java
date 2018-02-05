package cn.yuan.yu.library.listener;

/**
 * Created by yukuo on 2016/3/18.
 * 这是一个服务器响应的监听器
 */
public abstract class ResponseListener {
    /**
     * 这是一个返回正确信息的回调方法
     *
     * @param result
     */
    public abstract void onSuccess(String result);

    /**
     * 这是一个返回错误信息的回调方法
     *
     * @param responseresult
     */
    public abstract void onFailure(String responseresult);
}
