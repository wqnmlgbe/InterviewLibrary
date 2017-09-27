package open.cklan.com.interviewlibrary.category.day12_mvp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.dialog.MessageDialog;
import open.cklan.com.interviewlibrary.category.day12_mvp.presenter.BasePresenter;
import open.cklan.com.interviewlibrary.category.day12_mvp.views.BaseView;

/**
 * AUTHOR：lanchuanke on 17/9/26 10:08
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView{
    PagePrompt pagePrompt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagePrompt=new PagePrompt(this);
        presenter=createPresenter();
        if(presenter!=null){
            presenter.attachView(this);
            presenter.onCreate(getIntent());
        }
    }

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void showProgressDialog() {
        pagePrompt.showProgressDialog();
    }

    @Override
    public void dismissProgressDialog() {
        pagePrompt.dismissProgressDialog();
    }

    @Override
    public void showMessageDialog(MessageDialog.AlertParams params) {
        pagePrompt.showMessageDialog(params);
    }

    public void showMessageDialog(String msg){
        MessageDialog.AlertParams params= MessageDialog.AlertParams.newInstance(msg);
        showMessageDialog(params);
    }

    @Override
    public void dismissMessageDialog() {
        pagePrompt.dismissMessageDialog();
    }

    @Override
    public void showToast(CharSequence msg) {
        pagePrompt.showToast(msg);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(presenter!=null){
            presenter.onNewIntent(getIntent());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(presenter!=null){
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter!=null){
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(presenter!=null){
            presenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(presenter!=null){
            presenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.onDestroy();
            presenter.detachView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(presenter!=null){
            presenter.onActivityResult(requestCode,resultCode,data);
        }
    }

}
