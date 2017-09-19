package open.cklan.com.interviewlibrary.category.day7_contentProvider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

/**
 * AUTHOR：lanchuanke on 17/9/15 10:24
 */
public interface IStorage{
    String KEY = "key";
    String VALUE = "value";

    void putInt(String key,int value);
    int getInt(String key);

    void putLong(String key,long value);
    long getLong(String key);

    void putBoolean(String key,boolean value);
    boolean getBoolean(String key);

    void putString(String key,String value);
    String getString(String key);

    <V> void putObject(@Nullable String key, V value);
    <V> V getObject(@NonNull String key,@NonNull Class<V> clz);

    <V> List<Map<String,V>> getAllObjects(@NonNull Class<V> clz);
    void clearAllObjects();
}
