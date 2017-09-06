package open.cklan.com.interviewlibrary.category.day1_service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.Priority;
import com.example.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

@Schema(name = "service", priority = Priority.HIGH)
public class Day1_ServiceActivity extends BaseActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;

    Day1Service service;
    Receiver receiver;
    StringBuilder stringBuilder;
    Map<String, String> tasks = new HashMap<>();
    int taskCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day1__service);
        ButterKnife.bind(this);
        receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Receiver");
        intentFilter.addAction("AddTask");
        intentFilter.addAction("RemoveTask");
        registerReceiver(receiver, intentFilter);
        stringBuilder = new StringBuilder();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

            if (binder != null && binder instanceof Day1Service.MyBinder) {
                Day1Service.MyBinder myBinder = (Day1Service.MyBinder) binder;
                service = myBinder.getService();
                Intent broadcastIntent=new Intent("Receiver");
                broadcastIntent.putExtra("msg","onServiceConnected");
                sendBroadcast(broadcastIntent);
                service.execute("bind");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Intent broadcastIntent=new Intent("Receiver");
            broadcastIntent.putExtra("msg","onServiceDisconnected");
            sendBroadcast(broadcastIntent);
        }
    };

    @OnClick({R.id.tv_start_service, R.id.tv_stop_service, R.id.tv_bind_service, R.id.tv_unbind_service, R.id.tv_upload_file})
    public void click(View view) {
        stringBuilder.delete(0, stringBuilder.length());
        Intent intent = new Intent(this, Day1Service.class);
        switch (view.getId()) {
            case R.id.tv_start_service:
                startService(intent);
                break;
            case R.id.tv_stop_service:
                stopService(intent);
                break;
            case R.id.tv_bind_service:
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.tv_unbind_service:
                if(service!=null){
                    unbindService(serviceConnection);
                    service=null;
                }
                break;
            case R.id.tv_upload_file:
                String taskName = "taskName" + (taskCount++);
                String value = "图片正在排队上传";
                tasks.put(taskName, value);

                Intent broadcastIntent = new Intent("Receiver");
                broadcastIntent.setAction("AddTask");
                sendBroadcast(broadcastIntent);

                Intent serviceIntent = new Intent(this, Day1IntentService.class);
                serviceIntent.putExtra("taskName", taskName);
                startService(serviceIntent);
                break;
        }
    }

    public void showTasks() {
        StringBuilder sb = new StringBuilder();
        Set<String> keySet = tasks.keySet();
        for (String key : keySet) {
            sb.append(tasks.get(key) + "\n");
        }
        tvShow.setText(sb.toString());
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("Receiver".equals(action)) {
                String msg = intent.getStringExtra("msg");
                stringBuilder.append(msg + "\n");
                tvShow.setText(stringBuilder.toString());
            } else if ("AddTask".equals(action)) {
                showTasks();
            } else if ("RemoveTask".equals(action)) {
                String taskName = intent.getStringExtra("taskName");
                tasks.remove(taskName);
                showTasks();
            }

        }
    }


}
