package open.cklan.com.interviewlibrary.category.day12_mvp.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day12_mvp.adapter.BeautyListAdapter;
import open.cklan.com.interviewlibrary.category.day12_mvp.base.BaseMVPActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.base.BaseMVPDaggerActivity;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.DaggerTestActivityComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.componet.TestActivityComponent;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.NewsListEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.TestActivityModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.module.TestServiceModule;
import open.cklan.com.interviewlibrary.category.day12_mvp.presenter.BasePresenter;

/**
 * AUTHOR：lanchuanke on 17/9/26 14:31
 */
@Schema(name = "MVP+Dagger2+Retrofit+Rxjava")
public class Day12MVPDaggerActivity extends BaseMVPDaggerActivity<Day12Presenter> implements Day12View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void inject() {
        TestActivityComponent component=DaggerTestActivityComponent.builder()
                .activityComponent(getActivityComp())
                .testActivityModule(new TestActivityModule(this))
                .testServiceModule(new TestServiceModule()).build();
        presenter=component.getTestPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day12_mvp);
        ButterKnife.bind(this);
        presenter.loadData();
    }

    @Override
    public void showList(NewsListEntity<BeautyListItemEntity> data) {
        if(data!=null && data.entities!=null){
            BeautyListAdapter adapter=new BeautyListAdapter(data.entities);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

        }
    }

    //    @Override
//    protected Day12Presenter createPresenter() {
//        return new Day12Presenter(this);
//    }
}
