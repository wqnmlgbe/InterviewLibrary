package open.cklan.com.interviewlibrary.category.day13_mvvm.test;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BR;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day13_mvvm.entity.User;
import open.cklan.com.interviewlibrary.databinding.ActivityDay13MvvmSimpleBinding;

/**
 * AUTHOR：lanchuanke on 17/9/26 14:31
 */
@Schema(name = "Simple_Databinding")
public class Day13SimpleMVVMActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDay13MvvmSimpleBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_day13_mvvm_simple);
        ButterKnife.bind(this);
//        viewDataBinding.setVariable(BR.user,new User("zhangsan","110"));
        viewDataBinding.setUser(new User("lisi","123"));
    }

}
