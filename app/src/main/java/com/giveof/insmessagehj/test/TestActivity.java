package com.giveof.insmessagehj.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.giveof.insmessagehj.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
