package open.cklan.com.interviewlibrary.category.day13_mvvm.component;

import android.app.Activity;

import dagger.Component;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.ActivityComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.AppComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
import open.cklan.com.interviewlibrary.category.day13_mvvm.api.TestService;
import open.cklan.com.interviewlibrary.category.day13_mvvm.module.Day13ViewModelModule;
import open.cklan.com.interviewlibrary.category.day13_mvvm.viewmodel.Day13ViewModel;

/**
 * AUTHOR：lanchuanke on 17/9/27 15:35
 */
@PerActivity
@Component(dependencies = {ActivityComponent.class},
        modules = {Day13ViewModelModule.class})
public interface Day13MvvmComponent extends ActivityComponent {

    void inject(Activity activity);

    TestService getTestService();

    Day13ViewModel getViewModel();

}
