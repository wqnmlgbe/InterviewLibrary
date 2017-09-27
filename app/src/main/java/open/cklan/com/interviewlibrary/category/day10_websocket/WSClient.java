package open.cklan.com.interviewlibrary.category.day10_websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

/**
 * AUTHOR：lanchuanke on 17/9/22 11:17
 */
public class WSClient extends WebSocketClient {
    private IWSCallBack wsCallBack;

    public WSClient(URI serverUri,IWSCallBack wsCallBack){
        super(serverUri);
        this.wsCallBack=wsCallBack;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if(wsCallBack!=null){
            wsCallBack.onOpen(handshakedata);
        }
    }

    @Override
    public void onMessage(String message) {
        if(wsCallBack!=null){
            wsCallBack.onMessage(message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        if(wsCallBack!=null){
            wsCallBack.onClose(code,reason,remote);
        }
    }

    @Override
    public void onError(Exception ex) {
        if(wsCallBack!=null){
            wsCallBack.onError(ex);
        }
    }
}
