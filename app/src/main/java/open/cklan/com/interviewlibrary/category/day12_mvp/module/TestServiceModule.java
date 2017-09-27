package open.cklan.com.interviewlibrary.category.day12_mvp.module;

import dagger.Module;
import dagger.Provides;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.api.TestService;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
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
