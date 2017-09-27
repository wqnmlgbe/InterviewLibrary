package open.cklan.com.interviewlibrary.category.day10_websocket;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

import open.cklan.com.interviewlibrary.home.MainActivity;

/**
 * AUTHOR：lanchuanke on 17/9/22 11:42
 */
public class WSService extends Service implements IWSCallBack{

    private WSClient wsClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        sendBroadcast("onOpen:"+handshakedata.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        sendBroadcast("receive message:\t"+message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        sendBroadcast("onClose:"+reason);
    }

    @Override
    public void onError(Exception ex) {
        sendBroadcast("onError:"+ex.getMessage());
    }

    private void sendBroadcast(String msg){
        Intent intent=new Intent(Day10WSActivity.ACTION_WS_RECEIVER);
        intent.putExtra(Day10WSActivity.ARG_RECEIVER_MSG,msg);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        doStart(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean stopSelf = false;
        try {
            if (null == intent) {
                stopSelf();
                stopSelf = true;
            } else {
                stopSelf = doStart(intent);
            }
        } catch (Exception e) {
            stopSelf();//非用户stop 需要重启服务。
            e.printStackTrace();
        }
        return stopSelf ? START_NOT_STICKY : START_REDELIVER_INTENT;
    }

    private boolean doStart(Intent intent){
        initWSClient(intent);
        return false;
    }

    private void initWSClient(Intent intent){
        String url="ws://172.20.80.66:8080//websocket";
        wsClient=new WSClient(URI.create(url),this);
        wsClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(wsClient!=null && wsClient.isOpen()){
            wsClient.close();
        }
    }
}
