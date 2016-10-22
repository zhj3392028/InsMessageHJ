package com.giveof.insmessagehj.conn;

import com.giveof.insmessagehj.callback.ConnCallback;
import com.giveof.insmessagehj.utils.Connector;

import java.lang.reflect.AccessibleObject;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Time on 16/10/12.
 */

public class ConnManager implements Connector.ConnectorListener{

    //连接器
    private Connector connector;
    private Connector.ConnectorListener mListener;

    private static final ConnManager INSTANCE = new ConnManager();

    public static ConnManager getInstance(){
        return INSTANCE;
    }

    private ConnManager() {  //AccessibleObject.setAccessible();通过反射可以调用私有构造器，解决方法第二次实例的时候抛出异常即可！

    }


    /**
     * 创建连接发送验证
     * @param auth
     *
     */
    public void connect(String auth, ConnCallback callback){
        connector = new Connector();
        connector.setConnectorListener(this);
        connector.connect(callback);
        connector.auth(auth);

    }

    public void disConnect(ConnCallback callback){
        connector.disconnect(callback);
    }

    public void putRequest(String request) {
        connector.putRequest(request);
    }

    @Override
    public void putData(String data) {
        if (mListener != null) {
            mListener.putData(data);
        }
    }
}
