package open.cklan.com.interviewlibrary.category.day14_customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.Schema;

import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/10/13 18:26
 */
@Schema(name = "Signature")
public class Day14SignatureViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day14_signature);
    }
}
