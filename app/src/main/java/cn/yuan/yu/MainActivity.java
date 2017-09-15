package cn.yuan.yu;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.yuan.yu.library.bean.RequestPacket;
import cn.yuan.yu.library.listener.DonwloadResponseListener;
import cn.yuan.yu.library.listener.ResponseListener;
import cn.yuan.yu.library.utils.OkHttpUtil;
import cn.yuan.yu.library.utils.T;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_test_show)
    TextView tvTestShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_get, R.id.bt_post, R.id.bt_json, R.id.bt_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                /**
                 * 我没有接口自己玩吧
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

//                TODO: 记得更改APplication中文件配置
                break;
            case R.id.bt_post:
                /**
                 * 我没有接口自己玩吧
                 */
//                TODO: 记得更改APplication中文件配置
                break;
            case R.id.bt_json:
                /**
                 * 我没有接口自己玩吧
                 */
//                TODO: 记得更改APplication中文件配置
                break;
            case R.id.bt_download:
                String url = "http://7xp26r.com1.z0.glb.clouddn.com/Video_20160403_135934.mp4";
                String path = Environment.getExternalStorageDirectory() + "/xiaoyu/";
                String filename = System.currentTimeMillis() + ".mp4";
                OkHttpUtil.donwloadFile(url, path, filename, new DonwloadResponseListener() {
                    @Override
                    public void OnSuccess(final long bytesRead, final long contentLength, final boolean done) {
                        MyApplication.getMainHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                if (done) {
                                    T.showLong(MainActivity.this, "下载成功");
                                } else {
                                    tvTestShow.setText("下载进度" + (100 * bytesRead) / contentLength + "");
                                }
                            }
                        });

                    }

                    @Override
                    public void onfailure() {

                    }
                });
                break;
        }
    }
}
