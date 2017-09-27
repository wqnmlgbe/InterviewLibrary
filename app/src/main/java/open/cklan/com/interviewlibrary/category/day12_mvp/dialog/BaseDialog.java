package open.cklan.com.interviewlibrary.category.day12_mvp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextThemeWrapper;

/**
 * AUTHOR：lanchuanke on 17/9/26 14:11
 */
public class BaseDialog extends Dialog {
    private Handler mainHandler=new Handler(Looper.getMainLooper());

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public void showSelf(){
        if(Looper.myLooper()==Looper.getMainLooper()){
            showDialogOnMainThread();
        }else{
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    showDialogOnMainThread();
                }
            });
        }
    }

    public void dismissSelf(){
        if(Looper.myLooper()==Looper.getMainLooper()){
            dismissDialogOnMainThread();
        }else{
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    dismissDialogOnMainThread();
                }
            });
        }
    }

    private  void showDialogOnMainThread(){
        if (checkLifeCycle()) return;
        show();
    }

    private  void  dismissDialogOnMainThread(){
        if (checkLifeCycle()) return;
        dismiss();
    }

    private  boolean checkLifeCycle() {
        if(this==null || !this.isShowing()){
            return true;
        }
        Activity activity=getActivity();
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

    private  Activity getActivity() {
        Activity bindAct = null;
        Context context = getContext();
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
