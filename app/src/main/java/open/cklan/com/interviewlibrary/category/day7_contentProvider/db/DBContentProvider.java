package open.cklan.com.interviewlibrary.category.day7_contentProvider.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import open.cklan.com.interviewlibrary.category.day7_contentProvider.BaseContentProvider;

/**
 * AUTHOR：lanchuanke on 17/9/14 14:03
 */
public class DBContentProvider extends BaseContentProvider {
    public static final String AUTHORITY="open.cklan.com.interviewlibrary.category.day7_db_contentprovider";
    static {
        sUriMatcher.addURI(AUTHORITY,BOOLEAN_PATH,BOOLEAN_CODE);
        sUriMatcher.addURI(AUTHORITY,STRING_PATH,STRING_CODE);
        sUriMatcher.addURI(AUTHORITY,INT_PATH,INT_CODE);
        sUriMatcher.addURI(AUTHORITY,LONG_PATH,LONG_CODE);
        sUriMatcher.addURI(AUTHORITY,OBJECT_PATH,OBJECT_CODE);
    }

    private Context context;
    private SQLiteOpenHelper dbHelper;
    private ContentResolver contentResolver;


    @Override
    public boolean onCreate() {
        context=getContext();
        contentResolver=context.getContentResolver();
        dbHelper=new Day7DBHelper(context,new String[]{BOOLEAN_NAME,INT_NAME,STRING_NAME,LONG_NAME,OBJECT_NAME});
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor=null;
        String key=null;
        if(selectionArgs!=null && selectionArgs.length==1){
            key=selectionArgs[0];
        }
        switch (sUriMatcher.match(uri)) {
            case BOOLEAN_CODE:
                cursor=query(BOOLEAN_NAME,key);
                break;
            case LONG_CODE:
                cursor=query(LONG_NAME,key);
                break;
            case STRING_CODE:
                cursor=query(STRING_NAME,key);
                break;
            case INT_CODE:
                cursor=query(INT_NAME,key);
                break;
            case OBJECT_CODE:
                cursor=query(OBJECT_NAME,key);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return cursor;
    }
    private Cursor query(String tableName,String key){
        Cursor cursor=null;
        SQLiteDatabase readableDatabase=null;
        try {
            readableDatabase = dbHelper.getReadableDatabase();
            String selection=" key = ? ";
            String[] selectionArgs={key};
            if(TextUtils.isEmpty(key)){
                selection=null;
                selectionArgs=null;
            }
            cursor=readableDatabase.query(tableName,null,selection,selectionArgs,null,null,null);

        } finally {

        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resultUri=null;
        switch (sUriMatcher.match(uri)) {
            case BOOLEAN_CODE:
                insert(BOOLEAN_NAME,values);
                break;
            case LONG_CODE:
                insert(LONG_NAME,values);
                break;
            case STRING_CODE:
                insert(STRING_NAME,values);
                break;
            case INT_CODE:
                insert(INT_NAME,values);
                break;
            case OBJECT_CODE:
                insert(OBJECT_NAME,values);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return resultUri;
    }

    private void insert(String tableName,ContentValues values){
        SQLiteDatabase writableDatabase=null;
        try {
            writableDatabase = dbHelper.getWritableDatabase();
            long id=writableDatabase.insert(tableName,Day7DBHelper.COLUMN_ID,values);
            if(id!=-1){

            }
        } finally {
            if(writableDatabase!=null){
                writableDatabase.close();
            }
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleteCount=0;
        String key=null;
        if(selectionArgs!=null && selectionArgs.length==1){
            key=selectionArgs[0];
        }
        switch (sUriMatcher.match(uri)) {
            case BOOLEAN_CODE:
                delete(BOOLEAN_NAME,key);
                break;
            case LONG_CODE:
                delete(LONG_NAME,key);
                break;
            case STRING_CODE:
                delete(STRING_NAME,key);
                break;
            case INT_CODE:
                delete(INT_NAME,key);
                break;
            case OBJECT_CODE:
                delete(OBJECT_NAME,key);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return deleteCount;
    }

    private int delete(String tableName,String key){
        SQLiteDatabase writableDatabase=null;
        try {
            writableDatabase = dbHelper.getWritableDatabase();
            String selection=" key = ? ";
            String[] selectionArgs={key};
            if(TextUtils.isEmpty(key)){
                selection=null;
                selectionArgs=null;
            }
            int deleteCount=writableDatabase.delete(tableName,selection,selectionArgs);
            if(deleteCount>0){
                return deleteCount;
            }
        } finally {
            if(writableDatabase!=null){
                writableDatabase.close();
            }
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount=0;
        if(selectionArgs==null || selectionArgs.length!=1){
            throw new IllegalArgumentException("selectionArgs must only contains key");
        }
        String key=selectionArgs[0];
        switch (sUriMatcher.match(uri)) {
            case BOOLEAN_CODE:
                update(BOOLEAN_NAME,key,values);
                break;
            case LONG_CODE:
                update(LONG_NAME,key,values);
                break;
            case STRING_CODE:
                update(STRING_NAME,key,values);
                break;
            case INT_CODE:
                update(INT_NAME,key,values);
                break;
            case OBJECT_CODE:
                update(OBJECT_NAME,key,values);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return updateCount;
    }
    private int update(String tableName,String key,ContentValues values){
        SQLiteDatabase writableDatabase=null;
        try {
            writableDatabase = dbHelper.getWritableDatabase();
            String selection=" key = ? ";
            String[] selectionArgs={key};
            int deleteCount=writableDatabase.update(tableName,values,selection,selectionArgs);
            if(deleteCount>0){

                return deleteCount;
            }
        } finally {
            if(writableDatabase!=null){
                writableDatabase.close();
            }
        }
        return 0;
    }
    private void notifyChange(Uri uri){
        contentResolver.notifyChange(uri,null);
    }
}
