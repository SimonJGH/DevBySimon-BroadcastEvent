package com.simon.broadcast.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 广播公用接收器
 * Created by Administrator on 2017/12/20.
 */
@SuppressWarnings("all")
public class CommonReceiver extends BroadcastReceiver {

    private BroadCastCallback mCastCallback;
    private Bundle bundle;

    public CommonReceiver() {
    }

    public CommonReceiver(BroadCastCallback mCastCallback) {
        this.mCastCallback = mCastCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String flag = intent.getStringExtra("FLAG");
        bundle = intent.getExtras();
        mCastCallback.receiveEmptyMsg(flag);
        mCastCallback.receiveMsg(flag, bundle);
    }
}
