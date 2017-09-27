package open.cklan.com.interviewlibrary.category.day11_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR：lanchuanke on 17/9/25 11:22
 */
public class AidlService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    List<Book> books=new ArrayList<>();

    final BookManager.Stub binder=new BookManager.Stub() {

        @Override
        public List<Book> getBooks() throws RemoteException {
            return books;
        }

        @Override
        public void addBookIn(Book book) throws RemoteException {
            book.setPrice("10");
            books.add(book);
        }

        @Override
        public void addBookOut(Book book) throws RemoteException {
            book.setPrice("20");
            books.add(book);
        }

        @Override
        public void addBookInOut(Book book) throws RemoteException {
            book.setPrice("30");
            books.add(book);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
}
