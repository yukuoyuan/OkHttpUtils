# OkHttpUtils
这是一个自己封装的okhttputils工具类

> 本项目,只是让大家学习封装的过程,还有很多不足之处,不建议直接在项目中使用.
> 例如如果接口返回的是数组的情况没有进行处理,是否展示loading对话框也没有处理,按钮防抖操作也没有进行处理,还有缓存加密等.

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

### 表单的提交
```
/**
*配置网络框架
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


```
/**
*代码内部已经处理了json的解析,只需要传递一个类型就可以,看回调监听的类型
*/
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

### json数据的提交

```
/**
*框架的配置
*/
package cn.yuan.yu;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.yuan.yu.library.OkHttpUtilsConfig;

/**
 * Created by yukuoyuan on 2017/3/14.
 */

public class MyApplication extends Application {
    private static Handler mainHandler;
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
        OkHttpUtilsConfig.getInstance().init(true, "code", "200", "message", mainHandler, this);
        OkHttpUtilsConfig.getInstance().setShowLog(true);
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

```

```
/**
*post请求
*/
RequestPacket requestPacket = new RequestPacket();
                requestPacket.url = "http://cn.yuan.yu/api/login";
                requestPacket.addArgument("phone", "13721803180");
                requestPacket.addArgument("password", "123");
                OkHttpUtil.Request(RequestPacket.POST, requestPacket, new ResponseListener<RegistGetVCodeBean>() {
                    @Override
                    public void onSuccess(RegistGetVCodeBean registGetVCodeBean) {
                    }

                    @Override
                    public void onFailure(String responseresult) {
                    }
                });
```
