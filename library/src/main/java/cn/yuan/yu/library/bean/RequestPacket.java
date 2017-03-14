package cn.yuan.yu.library.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yukuo on 2016/3/18.
 * 这是一个请求实体类
 */
public class RequestPacket implements Serializable {
    public static final int POST = 1000, GET = 1001;// 定义两个请求类型
    public String url;// 请求的网络地址
    public Map<String, Object> arguments = new HashMap<>();// 请求参数
    public Map<String, String> headers = new HashMap<>();// 请求头

    /**
     * 这是一个添加请求参数的方法
     *
     * @param key
     * @param value
     */
    public void addArgument(String key, Object value) {
        arguments.put(key, value);
    }

    /**
     * 这是一个添加头信息参数的方法
     *
     * @param key
     * @param value
     */
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    /**
     * 这是一个根据键获取参数值得方法
     *
     * @param key
     * @return
     */
    public Object getArgument(String key) {
        return arguments.get(key);
    }

    /**
     * 这是一个根据键获取头信息值得方法
     *
     * @param key
     * @return
     */
    public String getHeader(String key) {
        return headers.get(key);
    }
}
