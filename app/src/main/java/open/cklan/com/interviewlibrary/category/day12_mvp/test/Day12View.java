package open.cklan.com.interviewlibrary.category.day12_mvp.test;

import open.cklan.com.interviewlibrary.category.day12_mvp.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.NewsListEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.views.BaseView;

/**
 * AUTHOR：lanchuanke on 17/9/26 17:38
 */
public interface Day12View extends BaseView {

    void showList(NewsListEntity<BeautyListItemEntity> data);

}
