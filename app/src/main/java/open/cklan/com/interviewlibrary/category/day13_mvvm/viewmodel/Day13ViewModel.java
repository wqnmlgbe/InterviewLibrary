package open.cklan.com.interviewlibrary.category.day13_mvvm.viewmodel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;

import open.cklan.com.interviewlibrary.category.day12_mvp.entity.HttpError;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.IRequestListener;
import open.cklan.com.interviewlibrary.category.day13_mvvm.adapter.BeautyListAdapter;
import open.cklan.com.interviewlibrary.category.day13_mvvm.api.TestService;
import open.cklan.com.interviewlibrary.category.day13_mvvm.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.category.day13_mvvm.entity.NewsListEntity;
import open.cklan.com.interviewlibrary.category.day13_mvvm.view.MVVMView;
import retrofit2.Response;

/**
 * AUTHOR：lanchuanke on 17/9/28 17:40
 */
public class Day13ViewModel extends BaseViewModel<MVVMView> {
    TestService testService;
    BeautyListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    public BeautyListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public Day13ViewModel(MVVMView view, TestService testService, Context context) {
        super(view);
        this.testService=testService;
        this.context=context;
        this.layoutManager=new LinearLayoutManager(context);
        this.adapter=new BeautyListAdapter(null,context);
    }

    public void loadData(){
        doHttpRequest(testService.newsList("T1456112189138", 0, 20), new IRequestListener<Response<NewsListEntity<BeautyListItemEntity>>>() {
            @Override
            public void success(Response<NewsListEntity<BeautyListItemEntity>> data) {
                adapter.setCategoryItemList(data.body().entities);
            }

            @Override
            public void error(HttpError error) {
                view.showToast(error.message);
            }
        });
    }
}
