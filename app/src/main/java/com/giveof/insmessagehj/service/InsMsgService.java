package com.giveof.insmessagehj.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.giveof.insmessagehj.base.BaseService;
import com.giveof.insmessagehj.conn.ConnManager;
import com.giveof.insmessagehj.receiver.MsgReceiver;
import com.giveof.insmessagehj.utils.Connector;

public class InsMsgService extends BaseService implements Connector.ConnectorListener{

    private ConnManager connManager;
    private Binder binder;
    private Thread thread;
    private String currentAction;//标记当前请求头信息，在获取服务器端反馈的数据后，进行验证，以免出现反馈信息和当前请求不一致问题。
    public InsMsgService() {
    }

    public class InterBinder extends Binder{
        public InsMsgService getService(){
            return InsMsgService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        binder = new InterBinder();
//        thread  = new Thread(InsMsgService.this);
//        thread.start();
        return binder;
    }

    /**
     * 连接服务
     */

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                connManager = ConnManager.getInstance();
                connManager.connect("#Application ");
            }
        }).start();
    }



    @Override
    public void putData(String data) {
        Intent intent = new Intent();
        intent.setAction(MsgReceiver.ACTION);
        intent.putExtra(MsgReceiver.DATA, data);
        sendBroadcast(intent);
    }
}
