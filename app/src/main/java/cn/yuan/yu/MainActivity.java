package cn.yuan.yu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.yuan.yu.library.utils.OkHttpUtil;
import cn.yuan.yu.library.bean.RequestPacket;
import cn.yuan.yu.library.listener.ResponseListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpUtil.Request(RequestPacket.POST, new RequestPacket(), new ResponseListener() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFailure(String responseresult) {

            }
        });
    }
}
