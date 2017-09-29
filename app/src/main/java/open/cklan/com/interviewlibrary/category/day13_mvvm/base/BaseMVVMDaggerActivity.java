package open.cklan.com.interviewlibrary.category.day13_mvvm.base;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.base.PagePrompt;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.ActivityComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.AppComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.DaggerActivityComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.dialog.MessageDialog;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.ActivityModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.views.BaseView;
import open.cklan.com.interviewlibrary.category.day13_mvvm.viewmodel.BaseViewModel;
import open.cklan.com.interviewlibrary.category.day8_launchmode.BaseApplication;

/**
 * AUTHOR：lanchuanke on 17/9/26 10:08
 */
public abstract class BaseMVVMDaggerActivity<V extends BaseViewModel> extends BaseActivity implements BaseView{
    PagePrompt pagePrompt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagePrompt=new PagePrompt(this);
        inject();
    }

    @Inject
    protected V viewModel;

    protected abstract void inject();

    protected AppComponent getAppComponent(){
        Application application=getApplication();
        if(application!=null && application instanceof BaseApplication){
            BaseApplication baseApplication= (BaseApplication) application;
            return baseApplication.getAppComponent();
        }
        return null;
    }

    protected ActivityComponent getActivityComp(){
        return DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .appComponent(getAppComponent()).build();
    }


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
        if(viewModel!=null){
            viewModel.onNewIntent(getIntent());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(viewModel!=null){
            viewModel.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(viewModel!=null){
            viewModel.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(viewModel!=null){
            viewModel.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(viewModel!=null){
            viewModel.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(viewModel!=null){
            viewModel.onDestroy();
            viewModel.detachView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(viewModel!=null){
            viewModel.onActivityResult(requestCode,resultCode,data);
        }
    }

}
