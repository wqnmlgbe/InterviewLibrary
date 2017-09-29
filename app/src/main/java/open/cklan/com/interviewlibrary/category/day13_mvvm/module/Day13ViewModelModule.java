package open.cklan.com.interviewlibrary.category.day13_mvvm.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import open.cklan.com.interviewlibrary.category.day12_mvp.scope.PerActivity;
import open.cklan.com.interviewlibrary.category.day13_mvvm.api.TestService;
import open.cklan.com.interviewlibrary.category.day13_mvvm.view.MVVMView;
import open.cklan.com.interviewlibrary.category.day13_mvvm.viewmodel.Day13ViewModel;

/**
 * AUTHOR：lanchuanke on 17/9/28 17:33
 */
@Module(includes = {TestServiceModule.class})
public class Day13ViewModelModule {

    MVVMView view;
    Context context;

    public Day13ViewModelModule(MVVMView view,Context context) {
        this.view = view;
        this.context=context;
    }

    @Provides
    @PerActivity
    public Day13ViewModel provideViewModel(TestService testService){
        return new Day13ViewModel(this.view,testService,context);
    }
}
