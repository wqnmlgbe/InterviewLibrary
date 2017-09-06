package open.cklan.com.interviewlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * AUTHOR：lanchuanke on 17/9/6 10:10
 */
public class BaseActivity extends AppCompatActivity {
    protected static final String BUNDLE_ARG_TITLE="title";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle();
    }

    private void setTitle() {
        Intent intent = getIntent();
        if(intent!=null){
            String title=intent.getStringExtra(BUNDLE_ARG_TITLE);
            if(!TextUtils.isEmpty(title)){
                setTitle(title);
            }
        }
    }
}
