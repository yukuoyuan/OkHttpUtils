package cn.yuan.yu.library;

/**
 * Created by yukuoyuan on 2017/3/14.
 * 这是一个okhttp的配置类
 */

public class OkHttpUtilsConfig {
    //返回编码key
    private static String ResultCodeKey = "";
    //返回编码正确值
    private static String ResultCodeValue = "";
    //返回错误信息值
    private static String ResultMsgKey = "";
    //是否提交json数据//否的话使用表单提交的形式
    private static boolean isPostJson = false;

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
    public static void setResultCodeKey(String resultCodeKey) {
        ResultCodeKey = resultCodeKey;
    }

    /**
     * 设置返回正确的正确的value
     *
     * @param resultCodeValue
     */
    public static void setResultCodeValue(String resultCodeValue) {
        ResultCodeValue = resultCodeValue;
    }

    /**
     * 设置返回信息的字段key
     *
     * @param resultMsgKey key
     */
    public static void setResultMsgKey(String resultMsgKey) {
        ResultMsgKey = resultMsgKey;
    }

    public static boolean isPostJson() {
        return isPostJson;
    }


    public static String getResultCodeKey() {
        return ResultCodeKey;
    }


    public static String getResultCodeValue() {
        return ResultCodeValue;
    }


    public static String getResultMsgKey() {
        return ResultMsgKey;
    }

}
