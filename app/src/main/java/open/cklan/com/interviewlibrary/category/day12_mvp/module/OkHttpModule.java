package open.cklan.com.interviewlibrary.category.day12_mvp.module;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * AUTHOR：lanchuanke on 17/9/27 09:34
 */
@Module
public class OkHttpModule {
    private static final long CONNECT_TIMEOUT = 30_000;
    private static final long WRITE_TIMEOUT = 30_000;
    private static final long READ_TIMEOUT = 30_000;
    private static final int REQUEST_CACHE_SIZE = 10 * 1024 * 1024;
    private static final int POOLING_MAX_CONNECTIONS = 5;
    private static final int REQUEST_KEEP_ALIVE_DEFAULT = 30000;

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT,TimeUnit.MILLISECONDS);
        builder.writeTimeout(WRITE_TIMEOUT,TimeUnit.MILLISECONDS);
        builder.connectionPool(new ConnectionPool(POOLING_MAX_CONNECTIONS,REQUEST_KEEP_ALIVE_DEFAULT,TimeUnit.MILLISECONDS));
        return builder.build();
    }

    Interceptor HEADER_INTERCEPTOR=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            request.newBuilder()
                    .addHeader("data4-Sent-Millis",System.currentTimeMillis()+"")
                    .addHeader("Add-To_Queue-Millis",System.currentTimeMillis()+"")
                    .addHeader("User-D","/ZFMLJiODEH2ZhofA/MvRr6zMupdTQUacn5iBuq96YWxyB7oNj+ThcWw2mgKWOnbIIGNeE0nI41SFrBIaL1THA==")
                    .addHeader("User_Agent","NewsApp/23.0 Android/6.0 (GIONEE/GN9012)")
                    .addHeader("User-N","J7j1Vq5rEW2YFBkjmfW6Bxb3VOTQjtndD5Qg328+Wm1OiOmXxQJ2MFkIjnBMtM41")
                    .addHeader("httpDNSIP","223.252.199.12")
                    .addHeader("User-C","5aS05p2h")
                    .addHeader("X-NR-Trace_Id",System.currentTimeMillis()+"_150006538_CQk3ZjVmYzk0OTYzZTEwZDgyCU5GQkVWU0lGSE1MTk5SUks%3D")
                    .addHeader("Host","c.m.163.com")
                    .addHeader("Connection","Keep-Alive")
                    .addHeader("Accept-Encoding","gzip")
                    .build();
            return chain.proceed(request);
        }
    };

}
