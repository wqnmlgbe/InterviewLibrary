package open.cklan.com.interviewlibrary.category.day12_mvp.componet;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.AppModule;
import retrofit2.Retrofit;

/**
 * AUTHOR：lanchuanke on 17/9/27 10:28
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Application getApplication();

    OkHttpClient getOkHttpClient();

    Retrofit getRetrofit();

    SharedPreferences getSP();
}
