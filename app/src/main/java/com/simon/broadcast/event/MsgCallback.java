package com.simon.broadcast.event;

import android.os.Bundle;

/**
 * 信息回调
 * Created by Administrator on 2017/12/20.
 */

public interface MsgCallback {

    public void receiveMsg(String flag);

    public void receiveMsg(String flag, Bundle bundle);
}
