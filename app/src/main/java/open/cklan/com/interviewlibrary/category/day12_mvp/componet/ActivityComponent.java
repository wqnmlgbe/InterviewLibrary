package open.cklan.com.interviewlibrary.category.day12_mvp.componet;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.ActivityModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.AppModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.ActivityScope;
import retrofit2.Retrofit;

/**
 * AUTHOR：lanchuanke on 17/9/27 10:28
 */
@ActivityScope
@Component(modules = {ActivityModule.class},dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent{
  Activity getActivity();
}
