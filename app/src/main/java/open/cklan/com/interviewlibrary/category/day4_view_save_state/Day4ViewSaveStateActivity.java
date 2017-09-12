package open.cklan.com.interviewlibrary.category.day4_view_save_state;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.Note;
import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/8 16:26
 */
@Note("* 1.6以前View的状态不会保存，但是之后Android做了改进\n" +
        " * 如果View设置了Id，在Activity容易被系统销毁的时候（不是用户自己退出）View会保存状态\n" +
        " * 如果没有设置Id，则不会保存\n" +
        " * 自定义View怎么保存呢：1.View有唯一的ID；2.View的初始化时要调用setSaveEnabled(true) ；\n" +
        " * Activity类的onSaveInstanceState默认实现会恢复Activity的状态，\n" +
        " * 默认实现会为布局中的每个View调用相应的 onSaveInstanceState方法，让每个View都能保存自身的信息。")
@Schema(name = "View_Save_State")
public class Day4ViewSaveStateActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day4_view_save_state);
    }
}
