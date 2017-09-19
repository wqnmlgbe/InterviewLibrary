package open.cklan.com.interviewlibrary.category.day7_contentProvider;

import android.content.ContentProvider;
import android.content.UriMatcher;

/**
 * AUTHOR：lanchuanke on 17/9/15 11:08
 */
public abstract class BaseContentProvider extends ContentProvider {
    public static final String HOST="content://";

    protected static final String SEPARATOR="/";

    public static final String BOOLEAN_NAME = "boolean";
    public static final String STRING_NAME = "string";
    public static final String INT_NAME = "integer";
    public static final String LONG_NAME = "long";
    public static final String OBJECT_NAME = "object";

    public static final String BOOLEAN_PATH = SEPARATOR+BOOLEAN_NAME;
    public static final String STRING_PATH = SEPARATOR+STRING_NAME;
    public static final String INT_PATH = SEPARATOR+INT_NAME;
    public static final String LONG_PATH = SEPARATOR+LONG_NAME;
    public static final String OBJECT_PATH = SEPARATOR+OBJECT_NAME;

    protected static final int BOOLEAN_CODE = 1;
    protected static final int STRING_CODE = 2;
    protected static final int INT_CODE = 3;
    protected static final int LONG_CODE = 4;
    protected static final int OBJECT_CODE=5;

    protected static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    }

    public static final String PREF_KEY = "key";
    public static final String PREF_VALUE = "value";

}
