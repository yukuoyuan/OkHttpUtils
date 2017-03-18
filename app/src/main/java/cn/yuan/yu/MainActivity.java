package cn.yuan.yu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

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
                break;
            case R.id.bt_post:
                break;
            case R.id.bt_json:
                break;
            case R.id.bt_download:
                break;
        }
    }
}
