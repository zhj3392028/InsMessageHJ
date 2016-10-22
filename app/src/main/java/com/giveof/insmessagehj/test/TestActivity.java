package com.giveof.insmessagehj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.giveof.insmessagehj.R;
import com.giveof.insmessagehj.callback.ConnCallback;
import com.giveof.insmessagehj.conn.ConnManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhj on 10/15/16.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.editText4)
    EditText editText4;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.btn_open)
    Button btnOpen;
    @Bind(R.id.btn_close)
    Button btnClose;
    @Bind(R.id.receive)
    TextView receive;

    private ConnManager conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initEvent();
        conn = ConnManager.getInstance();
    }

    private void initEvent() {
        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_open:

                conn.connect("", new ConnCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "通信已打开", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(TestActivity.this, "连接失败", Toast.LENGTH_SHORT).show();

                    }
                });

                break;
            case R.id.btn_close:
                conn.disConnect(new ConnCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(TestActivity.this, "通信已关闭", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(TestActivity.this, "关闭失败", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.btn_send:
                Toast.makeText(TestActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
