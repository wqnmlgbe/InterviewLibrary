package open.cklan.com.interviewlibrary.category.day7_contentProvider.sp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import open.cklan.com.interviewlibrary.category.day7_contentProvider.IStorage;

/**
 * AUTHOR：lanchuanke on 17/9/15 11:03
 */
public class SPStorage implements IStorage{
    public static final String BOOLEAN_URI= SPContentProvider.HOST+SPContentProvider.AUTHORITY+SPContentProvider.BOOLEAN_PATH;
    public static final String INT_URI=SPContentProvider.HOST+SPContentProvider.AUTHORITY+SPContentProvider.INT_PATH;
    public static final String LONG_URI=SPContentProvider.HOST+SPContentProvider.AUTHORITY+SPContentProvider.LONG_PATH;
    public static final String STRING_URI=SPContentProvider.HOST+SPContentProvider.AUTHORITY+SPContentProvider.STRING_PATH;
    public static final String OBJECT_URI=SPContentProvider.HOST+SPContentProvider.AUTHORITY+SPContentProvider.OBJECT_PATH;
    ContentResolver contentResolver;

    public SPStorage(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void putInt(String key, int value) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY,key);
        contentValues.put(VALUE,value);
        this.contentResolver.insert(Uri.parse(INT_URI),contentValues);
    }

    @Override
    public int getInt(String key) {
        Cursor cursor=this.contentResolver.query(Uri.parse(INT_URI),null,null,new String[]{key},null);
        if(cursor!=null){
            if(cursor.moveToNext()){
                return cursor.getInt(cursor.getColumnIndex(VALUE));
            }
        }
        return 0;
    }

    @Override
    public void putLong(String key, long value) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY,key);
        contentValues.put(VALUE,value);
        this.contentResolver.insert(Uri.parse(LONG_URI),contentValues);
    }

    @Override
    public long getLong(String key) {
        Cursor cursor=this.contentResolver.query(Uri.parse(LONG_URI),null,null,new String[]{key},null);
        if(cursor!=null){
            if(cursor.moveToNext()){
                return cursor.getLong(cursor.getColumnIndex(VALUE));
            }
        }
        return 0L;
    }

    @Override
    public void putBoolean(String key, boolean value) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY,key);
        contentValues.put(VALUE,value);
        this.contentResolver.insert(Uri.parse(BOOLEAN_URI),contentValues);
    }

    @Override
    public boolean getBoolean(String key) {
        Cursor cursor=this.contentResolver.query(Uri.parse(BOOLEAN_URI),null,null,new String[]{key},null);
        if(cursor!=null){
            if(cursor.moveToNext()){
                return cursor.getInt(cursor.getColumnIndex(VALUE))>0;
            }
        }
        return false;
    }

    @Override
    public void putString(String key, String value) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY,key);
        contentValues.put(VALUE,value);
        this.contentResolver.insert(Uri.parse(STRING_URI),contentValues);
    }

    @Override
    public String getString(String key) {
        Cursor cursor=this.contentResolver.query(Uri.parse(STRING_URI),null,null,new String[]{key},null);
        if(cursor!=null){
            if(cursor.moveToNext()){
                return cursor.getString(cursor.getColumnIndex(VALUE));
            }
        }
        return null;
    }

    @Override
    public <V> void putObject(@NonNull String key, V value) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY,key);
        contentValues.put(VALUE, JSON.toJSONString(value));
        this.contentResolver.insert(Uri.parse(OBJECT_URI),contentValues);
    }

    @Override
    public <V> V getObject(@NonNull String key, @NonNull Class<V> clz ) {
        Cursor cursor=null;
        try {
            cursor=this.contentResolver.query(Uri.parse(OBJECT_URI),null,null,new String[]{key},null);
            if(cursor!=null){
                if(cursor.moveToNext()){
                    String value= cursor.getString(cursor.getColumnIndex(VALUE));
                    return JSON.parseObject(value,clz);
                }
            }
        } finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return null;
    }

    @Override
    public <V> List<Map<String,V>> getAllObjects(@NonNull Class<V> clz) {
        List<Map<String,V>> list=new ArrayList<>();
        Cursor cursor=null;
        try {
            cursor=this.contentResolver.query(Uri.parse(OBJECT_URI),null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String key=cursor.getString(cursor.getColumnIndex(KEY));
                    String value= cursor.getString(cursor.getColumnIndex(VALUE));
                    Map<String,V> map=new HashMap<>();
                    V v= JSON.parseObject(value,clz);
                    map.put(key,v);
                    list.add(map);
                }
            }
        } finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return list;
    }

    @Override
    public void clearAllObjects() {
        this.contentResolver.delete(Uri.parse(OBJECT_URI),null,null);
    }


}
