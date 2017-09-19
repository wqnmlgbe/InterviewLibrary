package open.cklan.com.interviewlibrary.category.day7_contentProvider;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.Note;
import com.example.Schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day7_contentProvider.bean.Contact;
import open.cklan.com.interviewlibrary.category.day7_contentProvider.db.DBStorage;
import open.cklan.com.interviewlibrary.category.day7_contentProvider.sp.SPStorage;

/**
 * AUTHOR：lanchuanke on 17/9/14 11:59
 *
 */
@Note("* 1.What ContentProvider:应用程序间共享数据的方式，通常用于跨进程使用\n" +
        " * 2.Why ContentProvider:ContentProvider相当于定义了一套规范，比如你的应用数据想让别人使用，那你就会有一套标准让别人遵循，如果每个应用就有套单独的标准\n" +
        " * 对于调用者使用起来是很不方便的，而且ContentProvider可以定义权限等，保障了数据的安全性\n" +
        " * 3.What Uri:<scheme>://<authority><path>?<query> 例如content://com.example.provider/contact  每个provider都有一个唯一的authority，调用者可以通过\n" +
        " * 这个Uri找到对应的ContentProvider进行数据的增删改查\n" +
        " * 4.ContentProvider的用法：如果你想自己定义一个ContentProvider，你需要实现一个类去继承系统的ContentProvider，然后实现insert ,update ,delete,query等方法\n" +
        " * 这几个方法的具体实现可以根据你的实际情况，比如你可以存储到数据库，sp,文件等，此示例封装了db和sp的存储\n" +
        " * 5.ContentResolver：调用方使用ContentResolver根据Uri来进行数据的增删改查，还可以对数据的修改进行监听\n" +
        " * 6.此案例对于db和sp的封装,定义一个抽象接口IStorage，里面定义了对于各种数据存储和查询的方法，每种存储方式需实现此接口，方便后期扩展添加新的存储方式，例如DBStorage实现了\n" +
        " * IStorage，里面使用ContentResolver对数据进行存储和查询，每个存储方式都需实现一个自己的ContentProvider，在ContentProvider里面使用自己的存储方式对数据进行最后的增删改查\n" +
        " * ps：对Object对象的操作这里使用的是fastJson将对象转为JSON字符串，然后当做字符串存储")
@Schema(name = "ContentProvider")
public class Day7ContentProviderActivity extends BaseActivity {
    private static final int MSG_SHOW=1;
    private static final int MSG_CHANGE=2;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ContactListAdapter contactListAdapter;
    IStorage dbStorage;
    IStorage spStorage;
    List<Contact> contacts=new ArrayList<>();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg==null)return;
            switch (msg.what){
                case MSG_CHANGE:
                    break;
                case MSG_SHOW:
                    showContacts();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day7_contentprovider);
        ButterKnife.bind(this);
        dbStorage=new DBStorage(getContentResolver(),null);
        spStorage=new SPStorage(getContentResolver());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showContacts(){
        if(contactListAdapter==null){
            contactListAdapter=new ContactListAdapter(contacts);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(contactListAdapter);
        }else{
            contactListAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.tv_db_query,R.id.tv_db_add,R.id.tv_db_delete,R.id.tv_db_service_add,
                R.id.tv_sp_query,R.id.tv_sp_add,R.id.tv_sp_delete,R.id.tv_sp_service_add})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_db_query:
                queryAllContacts(dbStorage);
                break;
            case R.id.tv_db_add:
                addContact(dbStorage);
                break;
            case R.id.tv_db_delete:
                deleteContact(dbStorage);
                break;
            case R.id.tv_sp_query:
                queryAllContacts(spStorage);
                break;
            case R.id.tv_sp_add:
                addContact(spStorage);
                break;
            case R.id.tv_sp_delete:
                deleteContact(spStorage);
                break;
            case R.id.tv_db_service_add:
                Intent dbIntent=new Intent(this,Day7RemoteService.class);
                dbIntent.putExtra("storage_type","db");
                startService(dbIntent);
                break;
            case R.id.tv_sp_service_add:
                Intent spIntent=new Intent(this,Day7RemoteService.class);
                spIntent.putExtra("storage_type","sp");
                startService(spIntent);
                break;
        }
    }

    private void queryAllContacts(IStorage storage){
        contacts.clear();
        List<Map<String, Contact>> allObjects = storage.getAllObjects(Contact.class);
        if(allObjects!=null && allObjects.size()>0){
            for(Map<String,Contact> map:allObjects){
                if(map!=null && map.entrySet()!=null && map.entrySet().size()==1){
                    Iterator<Map.Entry<String, Contact>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Contact contact=iterator.next().getValue();
                        contacts.add(contact);
                    }
                }
            }
        }
        handler.sendMessage(handler.obtainMessage(MSG_SHOW));
    }

    private void addContact(IStorage storage){
        Contact contact=new Contact("张三","18325152512");
        storage.putObject(UUID.randomUUID().toString(),contact);
        queryAllContacts(storage);
    }


    private void deleteContact(IStorage storage){
        storage.clearAllObjects();
       queryAllContacts(storage);
    }

    class ContactContentObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public ContactContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            Message msg=handler.obtainMessage(MSG_CHANGE);
            handler.sendMessage(msg);
        }
    }
}
