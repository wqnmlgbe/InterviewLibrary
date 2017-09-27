package open.cklan.com.interviewlibrary.category.day12_mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.HttpError;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.IRequestListener;
import open.cklan.com.interviewlibrary.category.day12_mvp.views.BaseView;

/**
 * AUTHOR：lanchuanke on 17/9/26 10:08
 */
public abstract class BasePresenter<V extends BaseView> implements IPresenterLifeCycle {
    protected V view;
    CompositeDisposable compositeDisposable;

    public BasePresenter(V view) {
        this.view = view;
        compositeDisposable=new CompositeDisposable();
    }

    public void attachView(V view){
        this.view=view;
    }

    public void detachView(){
        this.view=null;
    }

    public <T>void doHttpRequest(Observable<T> observable, final IRequestListener<T> listener){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                        compositeDisposable.add(disposable);
                        view.showProgressDialog();
                    }

                    @Override
                    public void onNext(T t) {
                        listener.success(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.error(new HttpError(e.getMessage(), HttpError.Action.TOAST,-1));
                    }

                    @Override
                    public void onComplete() {
                        view.dismissProgressDialog();
                        compositeDisposable.delete(disposable);
                    }
                });
    }

    @Override
    public void onCreate(Intent intent) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        if(compositeDisposable!=null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
            compositeDisposable=null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}