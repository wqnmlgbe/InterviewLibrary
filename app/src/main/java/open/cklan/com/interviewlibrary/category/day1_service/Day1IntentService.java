package open.cklan.com.interviewlibrary.category.day1_service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * AUTHOR：lanchuanke on 17/9/6 14:48
 * 1.原理分析：IntentService是用来处理异步操作的Service
 * 2.IntentService 是内部拥有一个Looper(HandlerThread的looper),Handler，通过HandlerThread 来轮询MessageQueue,这就是为什么能异步操作
 * 3.每次startService 就会通过handler发送一个消息，handler处理消息就会回调onHandleIntent并通过startId来销毁此次service
 */
public class Day1IntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Day1IntentService(String name) {
        super(name);
    }

    public Day1IntentService(){
        super("Day1IntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(1500);
            String taskName=intent.getStringExtra("taskName");
            Intent broadcastIntent=new Intent("Receiver");
            broadcastIntent.setAction("RemoveTask");
            broadcastIntent.putExtra("taskName",taskName);
            sendBroadcast(broadcastIntent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
