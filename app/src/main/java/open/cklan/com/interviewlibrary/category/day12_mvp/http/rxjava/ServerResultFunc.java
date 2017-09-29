package open.cklan.com.interviewlibrary.category.day12_mvp.http.rxjava;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.exception.ServerException;
import retrofit2.Response;

/**
 * AUTHOR：lanchuanke on 17/9/28 10:14
 */
public class ServerResultFunc<T> implements Function<Response<T>, T> {

    @Override
    public T apply(@NonNull Response<T> tResponse) throws Exception {
        if (tResponse.code() != 200) {
            throw new ServerException(tResponse.code(),tResponse.message());
        }
        return tResponse.body();
    }
}

