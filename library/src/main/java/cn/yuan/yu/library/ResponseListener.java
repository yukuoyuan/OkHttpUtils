package cn.yuan.yu.library;

/**
 * Created by yukuo on 2016/3/18.
 * 这是一个服务器响应的监听器
 */
public abstract class ResponseListener<T> {
    /**
     * 这是一个返回正确信息的回调方法
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 这是一个返回错误信息的回调方法
     *
     * @param responseresult
     */
    public abstract void onFailure(String responseresult);
}
