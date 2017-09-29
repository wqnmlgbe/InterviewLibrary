package open.cklan.com.interviewlibrary.category.day13_mvvm.module;

import dagger.Module;
import dagger.Provides;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
import open.cklan.com.interviewlibrary.category.day13_mvvm.api.TestService;
import retrofit2.Retrofit;

/**
 * AUTHOR：lanchuanke on 17/9/27 10:24
 */
@Module
public class TestServiceModule {

    @Provides
    @PerActivity
    public TestService provideTestService(Retrofit retrofit){
        return retrofit.create(TestService.class);
    }
}
