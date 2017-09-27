package open.cklan.com.interviewlibrary.category.day12_mvp.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * AUTHOR：lanchuanke on 17/9/26 17:43
 */
@Module(includes = {
        OkHttpModule.class,
        RetrofitModule.class,
        SPModule.class})
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return this.application;
    }
}
