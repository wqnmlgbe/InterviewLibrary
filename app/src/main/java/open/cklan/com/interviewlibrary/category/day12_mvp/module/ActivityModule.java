package open.cklan.com.interviewlibrary.category.day12_mvp.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.ActivityScope;

/**
 * AUTHOR：lanchuanke on 17/9/28 21:29
 */
@Module
public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return this.activity;
    }
}
