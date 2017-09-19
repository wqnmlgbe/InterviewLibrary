package open.cklan.com.interviewlibrary.category.day7_contentProvider.sp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import open.cklan.com.interviewlibrary.category.day7_contentProvider.BaseContentProvider;

/**
 * AUTHOR：lanchuanke on 17/9/14 14:03
 */
public class SPContentProvider extends BaseContentProvider {
    public static final String AUTHORITY="open.cklan.com.interviewlibrary.category.day7_sp_contentprovider";

    static {
        sUriMatcher.addURI(AUTHORITY,BOOLEAN_PATH,BOOLEAN_CODE);
        sUriMatcher.addURI(AUTHORITY,STRING_PATH,STRING_CODE);
        sUriMatcher.addURI(AUTHORITY,INT_PATH,INT_CODE);
        sUriMatcher.addURI(AUTHORITY,LONG_PATH,LONG_CODE);
        sUriMatcher.addURI(AUTHORITY,OBJECT_PATH,OBJECT_CODE);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = null;
        String key=null;
        if(selectionArgs!=null && selectionArgs.length ==1){
            key=selectionArgs[0];
        }
        switch (sUriMatcher.match(uri)) {
            case BOOLEAN_CODE:
                cursor=preferenceToCursor(getSp(BOOLEAN_NAME).getPrefBoolean(key,false));
                break;
            case STRING_CODE:
                cursor=preferenceToCursor(getSp(STRING_NAME).getPrefString(key,""));
                break;
            case INT_CODE:
                cursor=preferenceToCursor(getSp(INT_NAME).getPrefInt(key,0));
                break;
            case LONG_CODE:
                cursor=preferenceToCursor(getSp(LONG_NAME).getPrefLong(key,0L));
                break;
            case OBJECT_CODE:
                if(!TextUtils.isEmpty(key)){
                    cursor=preferenceToCursor(getSp(OBJECT_NAME).getPrefString(key,"{}"));
                }else{
                    Map<String,?> all=getSp(OBJECT_NAME).getAll();
                    if(all!=null && all.keySet()!=null){
                        Set<String> keySet = all.keySet();
                        MatrixCursor matrixCursor = new MatrixCursor(new String[]{PREF_KEY,PREF_VALUE}, 2);
                        MatrixCursor.RowBuilder builder = null;
                        for(String str:keySet){
                            builder=matrixCursor.newRow();
                            builder.add(str);
                            builder.add(all.get(str));
                        }
                        cursor=matrixCursor;
                    }
                }
                break;
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
        Uri resultUri=uri;
        if(values==null)return resultUri;
        switch (sUriMatcher.match(uri)) {
            case BOOLEAN_CODE:
                persistBoolean(values);
                break;
            case LONG_CODE:
                persistLong(values);
                break;
            case STRING_CODE:
                persistString(values);
                break;
            case INT_CODE:
                persistInt(values);
                break;
            case OBJECT_CODE:
                persistObject(values);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleteCount=0;

        try {
            String key=null;
            if(selectionArgs!=null && selectionArgs.length==1){
                key=selectionArgs[0];
            }
            switch (sUriMatcher.match(uri)) {
                case BOOLEAN_CODE:
                    getSp(BOOLEAN_NAME).removePreference(key);
                    deleteCount=1;
                    break;
                case LONG_CODE:
                    getSp(LONG_NAME).removePreference(key);
                    deleteCount=1;
                    break;
                case STRING_CODE:
                    getSp(STRING_NAME).removePreference(key);
                    deleteCount=1;
                    break;
                case INT_CODE:
                    getSp(INT_NAME).removePreference(key);
                    deleteCount=1;
                    break;
                case OBJECT_CODE:
                    getSp(OBJECT_NAME).removePreference(key);
                    deleteCount=1;
                    break;
                default:
                    throw new IllegalStateException(" unsupported uri : " + uri);
            }
        } finally {

        }
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount=0;
        try {
            insert(uri,values);
            updateCount=1;
        } finally {

        }
        return updateCount;
    }

    private static String[] PREFERENCE_COLUMNS = {PREF_VALUE};

    private <T> MatrixCursor preferenceToCursor(T value) {
        MatrixCursor matrixCursor = new MatrixCursor(PREFERENCE_COLUMNS, 1);
        MatrixCursor.RowBuilder builder = matrixCursor.newRow();
        builder.add(value);
        return matrixCursor;
    }

    private void persistInt(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kInteger = values.getAsString(PREF_KEY);
        int vInteger = values.getAsInteger(PREF_VALUE);
        getSp(INT_NAME).setPrefInt(kInteger, vInteger);
    }

    private void persistBoolean(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kBoolean = values.getAsString(PREF_KEY);
        boolean vBoolean = values.getAsBoolean(PREF_VALUE);
        getSp(BOOLEAN_NAME).setPrefBoolean(kBoolean, vBoolean);
    }

    private void persistLong(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kLong = values.getAsString(PREF_KEY);
        long vLong = values.getAsLong(PREF_VALUE);
        getSp(LONG_NAME).setPrefLong(kLong, vLong);
    }

    private void persistString(ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kString = values.getAsString(PREF_KEY);
        String vString = values.getAsString(PREF_VALUE);
        getSp(STRING_NAME).setPrefString(kString, vString);
    }

    private void persistObject(ContentValues values){
        if (values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kString = values.getAsString(PREF_KEY);
        String vString = values.getAsString(PREF_VALUE);
        getSp(OBJECT_NAME).setPrefString(kString, vString);
    }

    private static Map<String, IPrefImpl> sPreferences = new HashMap<>();

    private IPrefImpl getSp(String name) {
        if (TextUtils.isEmpty(name)) {
            throw new IllegalArgumentException("getSp name is null!!!");
        }
        if (sPreferences.get(name) == null) {
            IPrefImpl pref = new PreferenceImpl(getContext(), name);
            sPreferences.put(name, pref);
        }
        return sPreferences.get(name);
    }

}