package com.giveof.insmessagehj.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.giveof.insmessagehj.conn.ConnManager;
import com.giveof.insmessagehj.receiver.MsgReceiver;
import com.giveof.insmessagehj.utils.Connector;

public class InsMsgService extends Service implements Connector.ConnectorListener{

    private ConnManager connManager;
    public InsMsgService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw null;
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
                connManager.connect("#A ");
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
