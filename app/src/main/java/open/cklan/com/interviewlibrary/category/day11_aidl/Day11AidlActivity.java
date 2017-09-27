package open.cklan.com.interviewlibrary.category.day11_aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.Note;
import com.example.Schema;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/25 10:59
 */
@Note("* AIDL创建过程：1.如果你的实体类型不是基本数据类型和String等，需要实现Parcelable接口，并在src/main/aidl 与实体类同包名下新建实体类.aidl，比如Book.aidl\n" +
        " *             2.新建你的具体业务的Aidl文件，注意方法参数的数据导向，in,out,inout，比如BookManager.aidl\n" +
        " *             3.在你的远程service中，在onBind方法中返回new BookManager.Stub(){};里面具体实现你定义的方法\n" +
        " *             4.在client端，比如Activity，通过bindService来得到bookManager,BookManager.Stub.asInterface(service);\n" +
        " *             5.和service链接成功后得到了BookManager，就可以通过此对象具体交互了\n" +
        "*  定向Tag详解：讨论的前提是实体类实现了readFromParcel，如果没有实现，out和inout Tag 服务端收不到客户端传来的数据,如果实现的是个空方法，三种tag都收不到值\n" +
        " *      1.in:表示客户端可以向服务端传输数据，服务端修改该数据对客户端没有影响\n" +
        " *      2.out:表示服务端向客户端传输数据，如果客户端使用此定向像服务端传输数据，服务端会自己new一个该对象，属性值为null，如果服务端修改了该对象，客户端同样生效\n" +
        " *      3.inout:表示客户端和服务端双向传输数据，并且服务端修改了数据对客户端同样生效")
@Schema(name = "AIDL")
public class Day11AidlActivity extends BaseActivity {
    @BindView(R.id.tv_show)
    TextView tvShow;

    private StringBuilder stringBuilder=new StringBuilder();
    private BookManager bookManager;
    private boolean isConnected;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager=BookManager.Stub.asInterface(service);
            isConnected=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected=false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day11_aidl);
        ButterKnife.bind(this);
        bindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    private void bindService(){
        Intent intent=new Intent(this,AidlService.class);
        intent.setAction("open.clkan.com.day11_aidl");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @OnClick({R.id.tv_add_book_in,R.id.tv_add_book_out,R.id.tv_add_book_inout,R.id.tv_query})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_add_book_in:
                addBookIn();
                break;
            case R.id.tv_add_book_out:
                addBookOut();
                break;
            case R.id.tv_add_book_inout:
                addBookInOut();
                break;
            case R.id.tv_query:
                getBooks();
                break;
        }
    }

    private void addBookIn(){
        Book book = buildBook();
        try {
            bookManager.addBookIn(book);
            stringBuilder.append("In添加成功:"+book.toString()+"\n");
            show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addBookOut(){
        Book book = buildBook();
        try {
            bookManager.addBookOut(book);
            stringBuilder.append("Out添加成功:"+book.toString()+"\n");
            show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addBookInOut(){
        Book book = buildBook();
        try {
            bookManager.addBookInOut(book);
            stringBuilder.append("InOut添加成功:"+book.toString()+"\n");
            show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private Book buildBook() {
        Book book=new Book();
        book.setName("Android开发艺术");
        book.setPrice("50");
        return book;
    }

    private void getBooks(){
        try {
            List<Book> books = bookManager.getBooks();
            stringBuilder.append("查询结果:\n");
            for(Book book:books){
                stringBuilder.append(book.toString()+"\n");
            }
            show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void show(){
        tvShow.setText(stringBuilder.toString());
    }
}
