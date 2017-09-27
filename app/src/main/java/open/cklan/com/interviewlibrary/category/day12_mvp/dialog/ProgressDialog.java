package open.cklan.com.interviewlibrary.category.day12_mvp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;

import dagger.Provides;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day9_pathmeasure.PathMeasureView;

/**
 * AUTHOR：lanchuanke on 17/9/26 13:21
 */
public class ProgressDialog extends Dialog{
    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static ProgressDialog newInstance(Context context){
        ProgressDialog progressDialog=new ProgressDialog(context, R.style.ProgressDialogStyle);
        progressDialog.setContentView(R.layout.dialog_progress);
        return progressDialog;
    }
}
