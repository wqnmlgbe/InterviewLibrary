package open.cklan.com.interviewlibrary.category.day12_mvp.http.api;


import io.reactivex.Observable;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.NewsListEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * AUTHOR：lanchuanke on 17/5/3 18:29
 * 视频频道api请求
 */
public interface TestService {
    String listUrl ="/recommend/getChanListNews?fn=1&passport=&devId=%2FZFMLJiODEH2ZhofA%2FMvRr6zMupdTQUacn5iBuq96YWxyB7oNj%2BThcWw2mgKWOnbIIGNeE0nI41SFrBIaL1THA%3D%3D&lat=&lon=&version=23.0&net=wifi&ts=1494215420&sign=q4ECrdrAvfZPiDtoROr9%2B%2F1ffld87Rf%2FLIfr7P4NyZ148ErR02zJ6%2FKXOnxX046I&encryption=1&canal=jinli_store2014_news&mac=racUMC0A9havm%2BHe6jH3YAvVdjgSXYDtwEDZ03eH1l8%3D&open=&openpath=HTTP/1.1";

    @GET(listUrl)
    Observable<Response<NewsListEntity<BeautyListItemEntity>>> newsList(@Query("channel") String tid, @Query("offset") long offset, @Query("size") long size);
}
