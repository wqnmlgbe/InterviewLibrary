package open.cklan.com.interviewlibrary.category.day13_mvvm.entity;

import java.io.Serializable;

/**
 * AUTHOR：lanchuanke on 17/9/27 16:20
 */
public class HttpError implements Serializable{
    public String message;
    public Action action;
    public int code;

    public HttpError(String message, Action action, int code) {
        this.message = message;
        this.action = action;
        this.code = code;
    }

    public enum  Action{
        TOAST,DIALOG
    }
}
