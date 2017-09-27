package open.cklan.com.interviewlibrary.category.day10_websocket;

import org.java_websocket.handshake.ServerHandshake;

/**
 * AUTHOR：lanchuanke on 17/9/22 11:19
 */
public interface IWSCallBack {

    void onOpen(ServerHandshake handshakedata);

    void onMessage(String message);

    void onClose(int code, String reason, boolean remote);

    void onError(Exception ex);
}
