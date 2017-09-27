package open.cklan.com.interviewlibrary.category.day12_mvp.http;

import open.cklan.com.interviewlibrary.category.day12_mvp.entity.HttpError;

/**
 * AUTHOR：lanchuanke on 17/9/27 16:19
 */
public interface IRequestListener<T> {

    void success(T data);

    void error(HttpError error);
}
