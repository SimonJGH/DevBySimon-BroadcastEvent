package com.simon.broadcast.event;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.simon.broadcast.MyApplication;

/**
 * 广播
 * Created by Administrator on 2017/12/20.
 */

@SuppressWarnings("all")
public class BroadCast {

    private Context mContext;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter filter;
    private CommonReceiver mReceiver;

    private BroadCast() {
        this.mContext = MyApplication.getInstance();
        // 粘性广播可以先发送-后注册
        localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        filter = new IntentFilter("STICKY_ACTION");
    }

    public static BroadCast getInstance() {
        return SafeMode.mBroadcast;
    }

    public static class SafeMode {
        private static final BroadCast mBroadcast = new BroadCast();
    }

    /**
     * 发送广播信息-空
     */
    public void sendEmptyMsg(String flag) {
        if (TextUtils.isEmpty(flag)) {
            Toast.makeText(mContext, "Broadcast tag can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("STICKY_ACTION");
        intent.putExtra("FLAG", flag);
        localBroadcastManager.sendBroadcast(intent);
    }

    /**
     * 发送广播信息-Bundle
     *
     * @param flag   标识
     * @param bundle
     */
    public void sendMsg(String flag, Bundle bundle) {
        if (TextUtils.isEmpty(flag)) {
            Toast.makeText(mContext, "Broadcast tag can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("STICKY_ACTION");
        intent.putExtra("FLAG", flag);
        intent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(intent);
    }

    /**
     * 接收广播信息-空
     *
     * @param callback
     */
    public void receiveEmptyMsg(final MsgCallback callback) {
        mReceiver = new CommonReceiver(new BroadCastCallback() {
            @Override
            public void receiveEmptyMsg(String flag) {

            }

            @Override
            public void receiveMsg(String flag, Bundle bundle) {

            }
        });
        localBroadcastManager.registerReceiver(mReceiver, filter);
    }

    /**
     * 接收广播信息
     *
     * @param callback
     */
    public void receiveMsg(final MsgCallback callback) {
        mReceiver = new CommonReceiver(new BroadCastCallback() {
            @Override
            public void receiveEmptyMsg(String flag) {
                callback.receiveMsg(flag);
            }

            @Override
            public void receiveMsg(String flag, Bundle bundle) {
                if (bundle != null) {
                    callback.receiveMsg(flag, bundle);
                }
            }
        });
        localBroadcastManager.registerReceiver(mReceiver, filter);
    }

    /**
     * 取消注册广播
     */
    public void unregisterReceiver() {
        if (mReceiver != null) {
            localBroadcastManager.unregisterReceiver(mReceiver);
        }
    }
}
