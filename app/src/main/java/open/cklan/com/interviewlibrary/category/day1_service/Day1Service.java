package open.cklan.com.interviewlibrary.category.day1_service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * AUTHOR：lanchuanke on 17/9/6 11:10
 * 原理总结：1启动service的两种方式   startService   bindService
 *         2.startService启动service之后，会回调onCreate onStartCommand  多次启动不会重复调用onCreate,但会多次调用onStartCommand
 *         3.stopService停止service会回调onDestroy;
 *         4.bindService 如果之前没有startService，会回调onCreate onBind onServiceConnected,如果service已经create了，只会回调onBind onServiceConneted
 *         5.unbindService 解绑service会回调onUnbind onDestroy ,如果之前调用过onStart则不会回调onDestroy  调用stopService才会onDestroy
 */
public class Day1Service extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onCreate");
        sendBroadcast(broadcastIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onStartCommond");
        sendBroadcast(broadcastIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onDestroy");
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onConfigurationChanged");
        sendBroadcast(broadcastIntent);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onUbind");
        sendBroadcast(broadcastIntent);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onRebind");
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onTaskRemoved");
        sendBroadcast(broadcastIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","onBind");
        sendBroadcast(broadcastIntent);
        return new MyBinder();
    }

    public void execute(String msg){
        Intent broadcastIntent=new Intent("Receiver");
        broadcastIntent.putExtra("msg","execute and paramter:"+msg);
        sendBroadcast(broadcastIntent);
    }


    public class MyBinder extends Binder{

        public Day1Service getService(){
            return Day1Service.this;
        }
    }
}
