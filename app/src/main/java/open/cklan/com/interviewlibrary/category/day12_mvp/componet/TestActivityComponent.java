package open.cklan.com.interviewlibrary.category.day12_mvp.componet;

import android.app.Activity;

import dagger.Component;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.api.TestService;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.TestActivityModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.test.Day12Presenter;

/**
 * AUTHOR：lanchuanke on 17/9/27 15:35
 */
@PerActivity
@Component(dependencies = {ActivityComponent.class},
        modules = {TestActivityModule.class})
public interface TestActivityComponent extends ActivityComponent {

    void inject(Activity activity);

    TestService getTestService();

    Day12Presenter getTestPresenter();

}
