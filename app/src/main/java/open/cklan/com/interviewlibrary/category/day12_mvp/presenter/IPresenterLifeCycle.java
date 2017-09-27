package open.cklan.com.interviewlibrary.category.day12_mvp.presenter;

import android.content.Intent;
import android.os.Bundle;

/**
 * AUTHOR：lanchuanke on 17/9/26 17:13
 */
public interface IPresenterLifeCycle {
    
    void onCreate(Intent intent);


    void onNewIntent(Intent intent);

    
    void onStart();

    
    void onResume();

    
    void onPause();

    
    void onStop();

    
    void onDestroy();

    
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
