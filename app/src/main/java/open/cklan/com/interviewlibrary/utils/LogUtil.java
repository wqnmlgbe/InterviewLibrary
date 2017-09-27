package open.cklan.com.interviewlibrary.utils;

import android.util.Log;

/**
 * AUTHOR：lanchuanke on 17/9/21 14:59
 */
public class LogUtil {
    private static final String TAG="InterviewLibrary";

    public static final void e(String msg){
        Log.e(TAG,msg);
    }

    public static final void i(String msg){
        Log.i(TAG,msg);
    }
}
