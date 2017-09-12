package open.cklan.com.interviewlibrary.category.day2_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.Note;
import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/7 14:39
 */
@Note("broadcast 三种发送方式：sendBroadcast sendOrderedBroadcast sendStickyBroadcast(已过时)\n" +
        " * sendBroadcast 无序广播，跨进程 ，接受广播无序\n" +
        " * sendOrderedBroadcast 有序的 跨进程 优先级高的广播会先接收到，并且有权决定继续下发还是终止广播\n" +
        " * sendStickyBroadcast 粘性广播，已过时，不推荐使用，跨进程，广播已经发送了，但是Receiver还没注册，注册后同样能收到消息\n" +
        " * 取消注册 unregisterReceiver\n" +
        " * LocalBroadManager 应用内使用，不能跨进程\n" +
        " * LocalBroadManager 原理分析:\n" +
        " * 基于主线程的Looper新建了一个Handler,通过handleMessage方法来回调onReceive()方法\n" +
        " * 1.注册接收器 registerReceiver 时保存到内部变量receivers和actions，\n" +
        " * mReceivers 存储广播和过滤器，是接收器和IntentFilter的对应表，\n" +
        " * 主要作用是方便在unregisterReceiver(…)取消注册，同时作为对象锁限制注册接收器、发送广播、取消接收器注册等几个过程的并发访问。\n" +
        " * 是一个以BraodcastReceiver为key，List<IntentFilter>为value的Map\n" +
        " * mActions 主要作用是方便在广播发送后快速得到可以接收它的BroadcastReceive\n" +
        " * 是一个以action为key，ReceiverRecord为value的Map，ReceiverRecord里面含有BroadcastReceiver,IntentFilter的变量\n" +
        " * 注册广播时，主要将BroadcastReceiver和IntentFilter存入mReceivers和mActions\n" +
        " * 2.发送广播 sendBroadcast(),通过发送广播时Intent设置的Action和其他的一些条件，取出mReceivers的ReceiverRecord存入一个List里面，\n" +
        " * 这个List就是分发的Receiver,Handler 发送 what 为MSG_EXEC_PENDING_BROADCASTS的消息\n" +
        " * 3.处理消息 在Handler的handleMessage里面处理消息，遍历List，每个Receiver回调onReceive方法\n" +
        " * 4.取消注册 unRegisterReceiver，将传入的receiver从mActions和mReceivers里面移除")
@Schema(name = "broadcast")
public class Day2BroadcastActivity extends BaseActivity {
    private static final int MSG_REGISTE_RECEIVER_DELAY = 10;
    private static final int DELAY_TIME = 1500;

    @BindView(R.id.tv_show)
    TextView tvShow;

    StringBuilder stringBuilder;

    NormalPriorityReceiver normalReceiver;
    HighPriorityReceiver orderdReceiver;
    StickyReceiver stickyReceiver;
    LocalBroadcastManagerReceiver localBroadcastManagerReceiver;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_REGISTE_RECEIVER_DELAY:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day2_broadcast);
        ButterKnife.bind(this);

        stringBuilder = new StringBuilder();

        normalReceiver = new NormalPriorityReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("normalReceiver");
        intentFilter.addAction("highReceiver");
        registerReceiver(normalReceiver, intentFilter);
        orderdReceiver = new HighPriorityReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("normalReceiver");
        intentFilter.addAction("highReceiver");
        intentFilter.setPriority(100);
        registerReceiver(orderdReceiver, intentFilter);
        localBroadcastManagerReceiver = new LocalBroadcastManagerReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastManagerReceiver, new IntentFilter("localReceiver"));

        tvShow.setMovementMethod(new ScrollingMovementMethod());//当TextView显示不下，支持滚动显示
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (normalReceiver != null) {
            unregisterReceiver(normalReceiver);
        }
        if (orderdReceiver != null) {
            unregisterReceiver(orderdReceiver);
        }
        if (stickyReceiver != null) {
            unregisterReceiver(stickyReceiver);
        }
        if (localBroadcastManagerReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastManagerReceiver);
        }
    }

    @OnClick({R.id.tv_send_local_broadcast, R.id.tv_send_normal_broadcast, R.id.tv_send_orderd_broadcast, R.id.tv_send_sticky_broadcast})
    public void click(View view) {
        stringBuilder.delete(0, stringBuilder.length());
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_send_normal_broadcast:
                intent.setAction("normalReceiver");
                sendMultBroadcast(intent, false);
                break;
            case R.id.tv_send_orderd_broadcast:
                intent.setAction("highReceiver");
                sendMultBroadcast(intent, true);
                break;
            case R.id.tv_send_sticky_broadcast:
                intent.setAction("stickyReceiver");
                sendStickyBroadcast(intent);
                stringBuilder.append("广播已发送了，但是我还没注册，赶紧注册挽救下\n");
                showLog();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (stickyReceiver == null) {
                            stickyReceiver = new StickyReceiver();
                            registerReceiver(stickyReceiver, new IntentFilter("stickyReceiver"));
                            stringBuilder.append("注册成功了，看看收到消息没\n");
                        }
                        showLog();
                    }
                }, DELAY_TIME);
                break;
            case R.id.tv_send_local_broadcast:
                intent.setAction("localReceiver");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                stringBuilder.append("通过LocalBroadcastManager 已发出消息\n");
                break;
        }
    }

    private void sendMultBroadcast(Intent intent, boolean isOrderd) {
        for (int i = 1; i <= 3; i++) {
            stringBuilder.append("发送了第" + i + "条广播\n");
            intent.putExtra("index", i);
            if (isOrderd) {
                sendOrderedBroadcast(intent, null);
            } else {
                sendBroadcast(intent);
            }
            showLog();
        }
    }

    private void showLog() {
        tvShow.setText(stringBuilder.toString());
    }


    public class NormalPriorityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getIntExtra("index", -1);
            stringBuilder.append("NormalPriorityReceiver 收到第" + index + "条消息\n");
            showLog();
        }
    }

    public class HighPriorityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getIntExtra("index", -1);
            stringBuilder.append("HighPriorityReceiver 收到第" + index + "条消息\n");
            showLog();
        }
    }

    public class StickyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            stringBuilder.append("还是收到消息了\n");
            showLog();
        }
    }

    public class LocalBroadcastManagerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            stringBuilder.append("接收到LocalBroadcastManager 发出的消息\n");
            showLog();
        }
    }
}
