package open.cklan.com.interviewlibrary.category.day12_mvp.views;

import open.cklan.com.interviewlibrary.category.day12_mvp.dialog.MessageDialog;

/**
 * AUTHOR：lanchuanke on 17/9/26 10:07
 */
public interface BaseView {

    void showProgressDialog();

    void dismissProgressDialog();

    void showMessageDialog(MessageDialog.AlertParams params);

    void dismissMessageDialog();

    void showToast(CharSequence msg);

}
