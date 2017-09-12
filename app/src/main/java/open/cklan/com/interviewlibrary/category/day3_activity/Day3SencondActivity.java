package open.cklan.com.interviewlibrary.category.day3_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/8 09:57
 */
public class Day3SencondActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day3_second_activity);
        ButterKnife.bind(this);
        setTitle("第二个界面");
    }

    @OnClick(R.id.tv_back)
    public void click(View view){
        finish();
    }
}
