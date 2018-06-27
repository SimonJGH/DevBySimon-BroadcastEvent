package com.simon.broadcast;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.simon.broadcast.event.BroadCast;
import com.simon.broadcast.event.BroadcastConstants;
import com.simon.broadcast.event.MsgCallback;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private TextView mTv_msg;
    private BroadCast mBroadcast;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mBroadcast.sendEmptyMsg(BroadcastConstants.TEST_EMPTY_MSG);
                    break;
                case 1:
                    Bundle bundle = new Bundle();
                    bundle.putString(BroadcastConstants.TEST_MSG, "测试传值数据");
                    mBroadcast.sendMsg(BroadcastConstants.TEST_MSG, bundle);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBroadcast = BroadCast.getInstance();
        mTv_msg = findViewById(R.id.tv_msg);

        initBroadcast();

        mHandler.sendEmptyMessageDelayed(0, 5000);
        mHandler.sendEmptyMessageDelayed(1, 10000);
    }


    /*初始化广播*/
    private void initBroadcast() {
        BroadCast.getInstance().receiveMsg(new MsgCallback() {
            @Override
            public void receiveMsg(String flag) {
                switch (flag) {
                    case BroadcastConstants.TEST_EMPTY_MSG:
                        mTv_msg.setText("测试空值数据" + "\n" + sdf.format(System.currentTimeMillis()));
                        break;
                }
            }

            @Override
            public void receiveMsg(String flag, Bundle bundle) {

                switch (flag) {
                    case BroadcastConstants.TEST_MSG:
                        String msg = bundle.getString(BroadcastConstants.TEST_MSG);
                        mTv_msg.setText(msg + "\n" + sdf.format(System.currentTimeMillis()));
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBroadcast.unregisterReceiver();
    }
}
