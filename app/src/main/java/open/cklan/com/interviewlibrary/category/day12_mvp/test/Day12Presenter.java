package open.cklan.com.interviewlibrary.category.day12_mvp.test;

import javax.inject.Inject;

import open.cklan.com.interviewlibrary.category.day12_mvp.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.HttpError;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.NewsListEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.IRequestListener;
import open.cklan.com.interviewlibrary.category.day12_mvp.http.api.TestService;
import open.cklan.com.interviewlibrary.category.day12_mvp.presenter.BasePresenter;
import retrofit2.Response;

/**
 * AUTHOR：lanchuanke on 17/9/26 17:38
 */
public class Day12Presenter extends BasePresenter<Day12View> {
    TestService testService;

    @Inject
    public Day12Presenter(Day12View view,TestService testService) {
        super(view);
        this.testService=testService;
    }

    public void loadData(){
        doHttpRequest(testService.newsList("T1456112189138", 0, 20), new IRequestListener<Response<NewsListEntity<BeautyListItemEntity>>>() {
            @Override
            public void success(Response<NewsListEntity<BeautyListItemEntity>> data) {
                view.showList(data.body());
            }

            @Override
            public void error(HttpError error) {
                view.showToast(error.message);
            }
        });
    }
}
