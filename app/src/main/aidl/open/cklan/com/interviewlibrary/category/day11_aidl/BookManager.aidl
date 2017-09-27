// BookManager.aidl
package open.cklan.com.interviewlibrary.category.day11_aidl;

// Declare any non-default types here with import statements
import open.cklan.com.interviewlibrary.category.day11_aidl.Book;

interface BookManager {

    List<Book> getBooks();

    void addBookIn(in Book book);

    void addBookOut(out Book book);

    void addBookInOut(inout Book book);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
