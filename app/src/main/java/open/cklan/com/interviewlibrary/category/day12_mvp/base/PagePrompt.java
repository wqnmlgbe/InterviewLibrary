package open.cklan.com.interviewlibrary.category.day12_mvp.base;

import android.content.Context;
import android.widget.Toast;

import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day12_mvp.dialog.DialogControl;
import open.cklan.com.interviewlibrary.category.day12_mvp.dialog.MessageDialog;
import open.cklan.com.interviewlibrary.category.day12_mvp.dialog.ProgressDialog;
import open.cklan.com.interviewlibrary.category.day12_mvp.views.BaseView;

/**
 * AUTHOR：lanchuanke on 17/9/26 13:20
 */
public class PagePrompt implements BaseView {
    private Context context;
    private ProgressDialog progressDialog;
    private MessageDialog messageDialog;

    public PagePrompt(Context context){
        this.context=context;
        progressDialog=ProgressDialog.newInstance(context);
    }

    @Override
    public void showProgressDialog() {
        DialogControl.showDialog(progressDialog);
    }

    @Override
    public void dismissProgressDialog() {
        DialogControl.dismissDialog(progressDialog);
    }

    @Override
    public void showMessageDialog(MessageDialog.AlertParams params) {
        MessageDialog.Builder builder=new MessageDialog.Builder(this.context,params);
        messageDialog=builder.create();
        DialogControl.showDialog(messageDialog);
    }

    @Override
    public void dismissMessageDialog() {
        DialogControl.dismissDialog(messageDialog);
    }

    @Override
    public void showToast(CharSequence msg) {
        Toast.makeText(this.context,msg,Toast.LENGTH_SHORT).show();
    }
}
