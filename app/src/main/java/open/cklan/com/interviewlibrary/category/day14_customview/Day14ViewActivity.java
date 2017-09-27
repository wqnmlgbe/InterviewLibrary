package open.cklan.com.interviewlibrary.category.day14_customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.Schema;

import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/27 14:33
 */
@Schema(name = "CustomView")
public class Day14ViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day14_view);
        ButterKnife.bind(this);
    }
}
