# OkHttpUtils
这是一个自己封装的okhttputils工具类
##引用方式

-------
```
<dependency>
  <groupId>cn.yuan.yu</groupId>
  <artifactId>library</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```


----------
###graldew引用方式
```

compile 'cn.yuan.yu:library:1.0.2'
```
##初始化
```
/**
*以下是表单的示例
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
}
```
###代码
###内部已经处理了json的解析,只需要传递一个类型就可以,看回调监听的类型

```
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", pwd);
        L.d("登录", jsonObject.toJSONString());
        RequestPacket requestPacket = new RequestPacket();
        requestPacket.url = ConstantUrl.getBaseUrl();
        requestPacket.addArgument("data", jsonObject.toJSONString());
        requestPacket.addArgument("query", "login");
        OkHttpUtil.Request(RequestPacket.POST,requestPacket,new ResponseListener<RegistGetVCodeBean>() {
            @Override
            public void onSuccess(RegistGetVCodeBean registGetVCodeBean) {
                iForgetPwdView.showT("发送成功");
            }

            @Override
            public void onFailure(String responseresult) {
                iForgetPwdView.showT(responseresult);
            }
        });
        ```
