package open.cklan.com.interviewlibrary.category.day10_websocket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.example.Priority;
import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/22 11:43
 */
@Schema(name = "WebSocket",priority = Priority.LOW)
public class Day10WSActivity extends BaseActivity {
    public static final String ACTION_WS_RECEIVER="WS_Receiver";
    public static final String ARG_RECEIVER_MSG="WS_Receiver_msg";
    @BindView(R.id.tv_show)
    TextView tvShow;
    WSBroadcastReceiver receiver;
    StringBuilder stringBuilder=new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day10_ws);
        ButterKnife.bind(this);
        receiver=new WSBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter(ACTION_WS_RECEIVER));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        stopService(null);
    }

    @OnClick({R.id.tv_start_service,R.id.tv_stop_service})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_start_service:
                startService();
                break;
            case R.id.tv_stop_service:
                stopService();
                break;
        }
    }

    private void stopService() {
        Intent intent=new Intent(this,WSService.class);
        stopService(intent);
    }

    private void startService() {
        Intent intent=new Intent(this,WSService.class);
        startService(intent);
    }


    public void show(){
        tvShow.setText(stringBuilder.toString());
    }

    private class WSBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null && ACTION_WS_RECEIVER.equals(intent.getAction())){
                String msg=intent.getStringExtra(ARG_RECEIVER_MSG);
                stringBuilder.append(msg+"\n");
                show();
            }
        }
    }

}
