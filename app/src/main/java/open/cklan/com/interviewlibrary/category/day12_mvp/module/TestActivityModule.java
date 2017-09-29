package open.cklan.com.interviewlibrary.category.day12_mvp.module;

import dagger.Module;
import dagger.Provides;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.api.TestService;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.test.Day12Presenter;
import open.cklan.com.interviewlibrary.category.day12_mvp.test.Day12View;

/**
 * AUTHOR：lanchuanke on 17/9/27 15:36
 */
@Module(includes = {TestServiceModule.class})
public class TestActivityModule {
    Day12View view;

    public TestActivityModule(Day12View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    public Day12Presenter provideTestPresneter(TestService testService){
        return new Day12Presenter(view,testService);
    }

}
