package cn.yuan.yu.library.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.yuan.yu.library.OkHttpUtilsConfig;
import cn.yuan.yu.library.ProgressResponseBody;
import cn.yuan.yu.library.R;
import cn.yuan.yu.library.bean.RequestPacket;
import cn.yuan.yu.library.listener.DonwloadResponseListener;
import cn.yuan.yu.library.listener.ResponseListener;


/**
 * Created by yukuo on 2016/3/18.
 * 这是一个okhttp请求的工具类
 */
public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    //请求类型
    public static final MediaType mJSON = MediaType.parse("application/json; charset=utf-8");
    //请求编码格式
    private static final String CHARSET_NAME = "UTF-8";


    static {
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
    }

    /**
     * 这是一个get请求拼接请求路径的方法
     *
     * @param requestPacket 请求参数
     * @return
     */
    public static String appendUrl(RequestPacket requestPacket) {
        return requestPacket.url + "?" + appendArguments(requestPacket);
    }

    /**
     * 这是一个拼接get请求请求参数的方法
     *
     * @param requestPacket 请求参数
     * @return
     */
    public static String appendArguments(RequestPacket requestPacket) {
        String argument = "";
        for (String key : requestPacket.arguments.keySet()) {
            if (requestPacket.getArgument(key) != null) {
                if (argument.equals("")) {
                    argument = key + "=" + requestPacket.getArgument(key);
                } else {
                    argument = argument + "&" + key + "=" + requestPacket.getArgument(key);
                }
            }
        }
        return argument;
    }

    /**
     * 这是一个请求的网络的方法
     *
     * @param method        请求方式
     * @param requestPacket 参数实体
     * @param listener      回调
     */
    public static void Request(int method, RequestPacket requestPacket, final ResponseListener listener) {
        Request request = null;
        Request.Builder builder = new Request.Builder();
        //设置请求类型
        builder.header("Content-Type", "application/json");
        // 添加头信息
        for (String key : requestPacket.headers.keySet()) {
            if (requestPacket.getHeader(key) != null) {
                builder.header(key, requestPacket.headers.get(key));
            }
        }
        //设置请求的url
        if (method == RequestPacket.POST) {
            //如果是post请求
            if (OkHttpUtilsConfig.getInstance().isPostJson()) {
                RequestBody body = RequestBody.create(mJSON, new Gson().toJson(requestPacket.arguments));
                request = new Request.Builder()
                        .url(requestPacket.url)
                        .post(body)
                        .build();
            } else {
                FormEncodingBuilder builer = new FormEncodingBuilder();
                Set<String> keysets = requestPacket.arguments.keySet();
                for (String key : keysets) {
                    builer.add(key, requestPacket.arguments.get(key).toString());
                }
                request = new Request.Builder()
                        .url(requestPacket.url)
                        .post(builer.build())
                        .build();
            }

        } else if (method == RequestPacket.GET) {
            String url = appendUrl(requestPacket);
            //如果是get请求
            request = builder.url(url).build();
        }
        L.i("请求路径和参数", request.toString() + new Gson().toJson(requestPacket.arguments));
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFaliure("", listener, e);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final String result = response.body().string();
                JsonObject re = new JsonParser().parse(result).getAsJsonObject();
                L.i("返回的结果", result);
                if (re.get(OkHttpUtilsConfig.getInstance().getResultCodeKey()).equals(OkHttpUtilsConfig.getInstance().getResultCodeValue())) {
                    SendSuccess(result, listener);
                } else {
                    String responseresult = re.get(OkHttpUtilsConfig.getInstance().getResultMsgKey()).getAsString();
                    sendFaliure(responseresult, listener, null);
                }
            }
        });
    }

    /**
     * 发送成功回到
     *
     * @param result   返回的结果
     * @param listener 回调监听
     */
    private static void SendSuccess(final String result, final ResponseListener listener) {
        OkHttpUtilsConfig.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                /**
                 * 获取要转换的json类型
                 */
                Type genType = listener.getClass().getGenericSuperclass();
                Class clzss = (Class) ((ParameterizedType) genType).getActualTypeArguments()[0];
                try {
                    listener.onSuccess(new Gson().fromJson(result, clzss));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 发送失败的消息
     *
     * @param responseresult 返回的错误信息
     * @param listener       回调监听
     * @param e              异常
     */
    private static void sendFaliure(final String responseresult, final ResponseListener listener, final IOException e) {
        OkHttpUtilsConfig.getInstance().getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(responseresult);
                if (e instanceof SocketTimeoutException) {
                    T.showLong(OkHttpUtilsConfig.getInstance().getContext(), R.string.net_error);
                    return;
                } else if (e instanceof ConnectException) {
                    T.showLong(OkHttpUtilsConfig.getInstance().getContext(), R.string.net_null);
                    return;
                }
            }
        });
    }

    /**
     * 这是一个下载文件的方法
     *
     * @param donwloadurl              下载路径
     * @param filepath                 文件路径
     * @param filename                 文件名字
     * @param donwloadResponseListener 回调监听
     */
    public static void donwloadFile(String donwloadurl, final String filepath, final String filename, final DonwloadResponseListener donwloadResponseListener) {

        //添加拦截器，自定义ResponseBody，添加下载进度
        mOkHttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) {
                Response originalResponse = null;
                try {
                    originalResponse = chain.proceed(chain.request());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return originalResponse.newBuilder().body(
                        new ProgressResponseBody(originalResponse.body(), donwloadResponseListener))
                        .build();
            }
        });
        //封装请求
        Request request = new Request.Builder()
                //下载地址
                .url(donwloadurl)
                .build();
        //发送异步请求
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                /**
                 * 失败回调监听
                 */
                OkHttpUtilsConfig.getInstance().getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        donwloadResponseListener.onfailure();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //将返回结果转化为流，并写入文件
                int len;
                byte[] buf = new byte[2048];
                InputStream inputStream = response.body().byteStream();
                //可以在这里自定义路径

                File file1 = new File(filepath + filename);
                FileOutputStream fileOutputStream = new FileOutputStream(file1);

                while ((len = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, len);
                }

                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            }
        });
    }


}
