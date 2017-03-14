package cn.yuan.yu;

/**
 * Created by yukuoyuan on 2017/3/14.
 * 这是一个网络管理类
 */

public class HttpManager {
    private static HttpManager sInstance;

    private HttpManager() {
    }

    public static HttpManager instance() {
        if (sInstance == null) {
            synchronized (HttpManager.class) {
                if (sInstance == null) {
                    sInstance = new HttpManager();
                }
            }
        }
        return sInstance;
    }

}
