package com.giveof.insmessagehj.base;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.giveof.insmessagehj.entity.BaseEntity;

import java.util.List;

/**
 * Created by zhj on 10/15/16.
 */

public class BaseService extends Service {
    private Activity currActivity;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    interface CallbackInsMsg<T extends BaseEntity>{
        void transformDate(T data);

    }
    public void addObser(Activity currActivity){
        this.currActivity = currActivity;
    }
}
