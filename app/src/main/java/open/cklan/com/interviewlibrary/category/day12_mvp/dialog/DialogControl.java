package open.cklan.com.interviewlibrary.category.day12_mvp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextThemeWrapper;

/**
 * AUTHOR：lanchuanke on 17/9/26 13:30
 */
public class DialogControl {
    private static final Handler mainHandler=new Handler(Looper.getMainLooper());

    public static void showDialog(final Dialog dialog){
        if(Looper.myLooper()==Looper.getMainLooper()){
            showDialogOnMainThread(dialog);
        }else{
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    showDialogOnMainThread(dialog);
                }
            });
        }
    }

    public static void  dismissDialog(final Dialog dialog){
        if(Looper.myLooper()==Looper.getMainLooper()){
            dismissDialogOnMainThread(dialog);
        }else{
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    dismissDialogOnMainThread(dialog);
                }
            });
        }
    }

    private static void showDialogOnMainThread(Dialog dialog){
        if (checkLifeCycle(dialog,true)) return;
        dialog.show();
    }

    private static void  dismissDialogOnMainThread(Dialog dialog){
        if (checkLifeCycle(dialog,false)) return;
        dialog.dismiss();
    }

    private static boolean checkLifeCycle(Dialog dialog,boolean show) {
        if(dialog==null){
            return true;
        }
        if(show && dialog.isShowing()){
            return true;
        }
        if(!show && !dialog.isShowing()){
            return true;
        }
        Activity activity=getActivity(dialog);
        if(activity==null || activity.isFinishing()){
            return true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if(activity.isDestroyed()){
                return true;
            }
        }
        return false;
    }

    private static Activity getActivity(Dialog dialog) {
        Activity bindAct = null;
        Context context = dialog.getContext();
        do {
            if (context instanceof Activity) {
                bindAct = (Activity) context;
                break;
            } else if (context instanceof ContextThemeWrapper) {
                context = ((ContextThemeWrapper) context).getBaseContext();
            } else {
                break;
            }
        } while (true);
        return bindAct;
    }
}
