package open.cklan.com.interviewlibrary.category.day11_aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AUTHOR：lanchuanke on 17/9/25 11:12
 */
public class Book implements Parcelable{
    private String name;
    private String price;

    protected Book(Parcel in) {
        readFromParcel(in);
    }

    public Book(){};

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        price = in.readString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
