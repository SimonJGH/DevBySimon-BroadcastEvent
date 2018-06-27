package com.simon.broadcast.event;

import android.os.Bundle;

/**
 * 广播回调
 * Created by Administrator on 2017/12/20.
 */

public interface BroadCastCallback {
    public void receiveEmptyMsg(String flag);

    public void receiveMsg(String flag, Bundle bundle);
}
