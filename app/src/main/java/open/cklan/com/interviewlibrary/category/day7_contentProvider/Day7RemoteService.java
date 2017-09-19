package open.cklan.com.interviewlibrary.category.day7_contentProvider;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.UUID;

import open.cklan.com.interviewlibrary.category.day7_contentProvider.bean.Contact;
import open.cklan.com.interviewlibrary.category.day7_contentProvider.db.DBStorage;
import open.cklan.com.interviewlibrary.category.day7_contentProvider.sp.SPStorage;

/**
 * AUTHOR：lanchuanke on 17/9/15 16:04
 */
public class Day7RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Day7Binder();
    }

    public class Day7Binder extends Binder{

        public Day7RemoteService getService(){
            return Day7RemoteService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String type=intent.getStringExtra("storage_type");
        IStorage storage=null;
        if("sp".equals(type)){
            storage=new SPStorage(getContentResolver());
        }else if("db".equals(type)){
            storage=new DBStorage(getContentResolver(),null);
        }else{
            throw new RuntimeException("不支持的storage 类型");
        }
        Contact contact=new Contact("李四 add by remote service","18325152512");
        storage.putObject(UUID.randomUUID().toString(),contact);
        Toast.makeText(this,"添加成功,点击查询查看结果",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);

    }
}
