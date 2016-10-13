package com.giveof.insmessagehj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Time on 16/10/12.
 */

public class Connector {

    //ip
    private String dstName = "192.168.140.106";
    //端口
    private int dstPort = 8000;
    private Socket mClientSocket;
    private ConnectorListener mConnectorListener;
    InputStream is;
    byte[] buffer;
    //队列
    private ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(8);

    /**
     * 使用三次握手实现长连接。关闭连接需要4次握手
     */
    public void connect() {
        try {
            if (mClientSocket == null || mClientSocket.isClosed()) {
                mClientSocket = new Socket(dstName, dstPort);
            }
            //发送信息给服务器
            new Thread(new RequestWorker()).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        is = mClientSocket.getInputStream();
                        buffer = new byte[1024];
                        int len = -1;
                        while ((len = is.read(buffer)) != -1) {
                            final String text = new String(buffer, 0, len);
                            if (mConnectorListener != null) {
                                mConnectorListener.putData(text);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把验证放入队列
     * @param auth
     */
    public void auth(String auth) {
        putRequest(auth);
    }



    /**
     * 把信息放入队列
     *
     * @param content
     */
    public void putRequest(String content) {
        try {
            queue.put(content);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (mClientSocket != null && !mClientSocket.isClosed()) {
            try {
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mClientSocket = null;
        }
    }


    public void setConnectorListener(ConnectorListener mConnectorListener) {
        this.mConnectorListener = mConnectorListener;
    }

    public interface ConnectorListener {
        void putData(String data);
    }


    public class RequestWorker implements Runnable {

        @Override
        public void run() {
            //数据通信
            OutputStream os;
            try {
                os = mClientSocket.getOutputStream();
                while (true) {
                    String content = queue.take();
                    os.write(content.getBytes());
                }
            } catch (Exception e) {
                throw new RuntimeException("获取数据失败：" + e.getMessage());
            }
        }
    }
    /**
     * 循环，接收服务端数据
     */
    public void receiveData(){
        try {
        while (true){
                Thread.sleep(500);
            if (mClientSocket != null && !mClientSocket.isConnected()) {
                if (mClientSocket.isConnected()) {
                    if (mClientSocket.isInputShutdown()) {
                        String content;
                        int len = -1;
                        if ((len=is.read(buffer))!=-1){
                            String str = new String(buffer,0,len);
                            mConnectorListener.putData(str);
                        }
                    }
                }
            }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
