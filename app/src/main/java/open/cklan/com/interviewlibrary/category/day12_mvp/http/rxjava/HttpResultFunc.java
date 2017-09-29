package open.cklan.com.interviewlibrary.category.day12_mvp.http.rxjava;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.exception.ExceptionEngine;

/**
 * AUTHOR：lanchuanke on 17/9/28 10:14
 */

public class HttpResultFunc<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
