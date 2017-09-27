package open.cklan.com.interviewlibrary.category.day12_mvp.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * AUTHOR：lanchuanke on 17/9/26 18:22
 */
@Module
public class SPModule {

    private static final String SP_NAME="common";

    @Provides
    @Singleton
    public SharedPreferences provideSP(Application context){
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
}
