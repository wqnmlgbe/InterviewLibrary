package open.cklan.com.interviewlibrary.category.day12_mvp.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.converter.FastJsonConvertFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * AUTHOR：lanchuanke on 17/9/27 09:35
 */
@Module
public class RetrofitModule {

    private static final String BASE_URL = "http://c.m.163.com/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new FastJsonConvertFactory())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

}
