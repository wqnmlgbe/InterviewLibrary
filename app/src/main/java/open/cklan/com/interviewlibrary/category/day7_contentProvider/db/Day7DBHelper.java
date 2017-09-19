package open.cklan.com.interviewlibrary.category.day7_contentProvider.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * AUTHOR：lanchuanke on 17/9/14 15:54
 */
public class Day7DBHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_KEY="key";
    public static final String COLUMN_VALUE="value";

    private static final String SQL_CREATE = "create table %S("+COLUMN_ID+" integer primary key autoincrement," +
            COLUMN_KEY+" text not null,"+COLUMN_VALUE+" text not null);";
    private static final String DB_NAME="interview_library.db";
    private static final int DB_VERSION=1;
    private String[] tableNames;

    public Day7DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version,null);
    }

    public Day7DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public Day7DBHelper(Context context,String[] tableNames) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory)null, DB_VERSION);
        this.tableNames=tableNames;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        if(tableNames!=null && tableNames.length>0){
            for(String tableName:tableNames){
                db.execSQL(String.format(SQL_CREATE,tableName));
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
