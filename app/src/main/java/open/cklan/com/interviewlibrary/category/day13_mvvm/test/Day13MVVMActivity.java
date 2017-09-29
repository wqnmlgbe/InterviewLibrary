package open.cklan.com.interviewlibrary.category.day13_mvvm.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.Schema;

import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day13_mvvm.base.BaseMVVMDaggerActivity;
import open.cklan.com.interviewlibrary.category.day13_mvvm.component.DaggerDay13MvvmComponent;
import open.cklan.com.interviewlibrary.category.day13_mvvm.component.Day13MvvmComponent;
import open.cklan.com.interviewlibrary.category.day13_mvvm.module.Day13ViewModelModule;
import open.cklan.com.interviewlibrary.category.day13_mvvm.module.TestServiceModule;
import open.cklan.com.interviewlibrary.category.day13_mvvm.view.MVVMView;
import open.cklan.com.interviewlibrary.category.day13_mvvm.viewmodel.Day13ViewModel;
import open.cklan.com.interviewlibrary.databinding.ActivityDay13MvvmBinding;

/**
 * AUTHOR：lanchuanke on 17/9/28 17:52
 */
@Schema(name = "MVVM+Dagger")
public class Day13MVVMActivity extends BaseMVVMDaggerActivity<Day13ViewModel> implements MVVMView {

    @Override
    protected void inject() {
        Day13MvvmComponent com = DaggerDay13MvvmComponent.builder()
                .activityComponent(getActivityComp())
                .day13ViewModelModule(new Day13ViewModelModule(this,this))
                .testServiceModule(new TestServiceModule())
                .build();
        viewModel=com.getViewModel();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDay13MvvmBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_day13_mvvm);
        binding.setViewModel(viewModel);
        ButterKnife.bind(this);
        viewModel.loadData();
    }

}
