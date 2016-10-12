package com.giveof.insmessagehj.activity;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.giveof.insmessagehj.R;
import com.giveof.insmessagehj.adapter.IMainAdapter;
import com.giveof.insmessagehj.entity.Contract;
import com.giveof.insmessagehj.receiver.MsgReceiver;
import com.giveof.insmessagehj.receiver.PushReceiver;
import com.giveof.insmessagehj.viewUtil.PopDialogView;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private TranslateAnimation myAnimation_Translate;
    private PopDialogView diaView;
    private List<Contract> mData;
    private IMainAdapter adapter;
    private LinearLayoutManager linear;
    private boolean onPress;
    private PushReceiver receiver;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView.setVisibility(View.VISIBLE);
        myAnimation_Translate = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        myAnimation_Translate.setDuration(1000);
        myAnimation_Translate.setInterpolator(AnimationUtils
                .loadInterpolator(MainActivity.this,
                        android.R.anim.accelerate_decelerate_interpolator));
        recyclerView.startAnimation(myAnimation_Translate);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(MainActivity.this, SmsActivity.class);
                ActivityTransitionLauncher.with(MainActivity.this).from(view).launch(in);
            }
        });
        initData();
        initReceiver();
        linear = new LinearLayoutManager(MainActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        //
        recyclerView.setLayoutManager(linear);
        adapter = new IMainAdapter(MainActivity.this);
        adapter.getData(mData);
        adapter.setItemViewType(0);
        recyclerView.setAdapter(adapter);
    }


    private void initReceiver() {
        receiver = new PushReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                if (MsgReceiver.ACTION.equals(intent.getAction())) {
                    String response = intent.getStringExtra(MsgReceiver.DATA);
                    toolbar.setTitle(response);

                }
            }
        };

        IntentFilter filter = new IntentFilter(MsgReceiver.ACTION);
        registerReceiver(receiver,filter);
    }

    private void initData() {
        mData = new ArrayList<>();
        Contract content = new Contract();
        for (int i = 'A'; i < 'z'; i++) {
            content.setName("" + (char) i);
            mData.add(content);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_call) {
            diaView = new PopDialogView(MainActivity.this);
            diaView.showAtLocation(recyclerView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        }
        if (id == R.id.action_close) {
            Toast.makeText(MainActivity.this, "通信服务已关闭~", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.action_contract) {
            leftToRightAnimation();
            recyclerView.startAnimation(myAnimation_Translate);

            recyclerView.setVisibility(View.INVISIBLE);
            downToUpAnimation();
            recyclerView.startAnimation(myAnimation_Translate);
            recyclerView.setVisibility(View.INVISIBLE);
            adapter.setItemViewType(0);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
        if (id == R.id.action_notification) {

            leftToRightAnimation();
            recyclerView.startAnimation(myAnimation_Translate);
            recyclerView.setVisibility(View.INVISIBLE);

            downToUpAnimation();
            recyclerView.startAnimation(myAnimation_Translate);
            recyclerView.setVisibility(View.INVISIBLE);
            adapter.setItemViewType(1);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    public void leftToRightAnimation() {
        myAnimation_Translate = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        myAnimation_Translate.setDuration(1000);
        myAnimation_Translate.setInterpolator(AnimationUtils
                .loadInterpolator(MainActivity.this,
                        android.R.anim.accelerate_decelerate_interpolator));
    }

    public void downToUpAnimation() {
        myAnimation_Translate = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1);
        myAnimation_Translate.setDuration(1000);
        myAnimation_Translate.setInterpolator(AnimationUtils
                .loadInterpolator(MainActivity.this,
                        android.R.anim.accelerate_decelerate_interpolator));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
