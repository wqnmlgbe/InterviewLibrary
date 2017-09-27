package open.cklan.com.interviewlibrary.category.day12_mvp.componet;

import dagger.Component;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.api.TestService;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.TestActivityModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.test.Day12Presenter;

/**
 * AUTHOR：lanchuanke on 17/9/27 15:35
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = TestActivityModule.class)
public interface TestActivityComponent {

    TestService getTestService();

    Day12Presenter getTestPresenter();

}
