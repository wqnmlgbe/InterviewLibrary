package open.cklan.com.interviewlibrary.category.day14_customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.Schema;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/10/18 08:46
 */
@Schema(name = "StickyRecyclerView")
public class Day14StickyRecyclerViewActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day14_sticky_recyclerview);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<City> cityList=CityStorage.getCityList();
        recyclerView.addItemDecoration(new StickyItemDecoration(new StickyItemDecoration.StickyGroupInterface() {
            SparseIntArray groupHeight=new SparseIntArray(cityList.size());
            SparseArray<View> groupViews=new SparseArray<View>(cityList.size());
            @Override
            public View getGroupView(int position) {
                return groupViews.get(position);
            }

            @Override
            public String getGroupName(int position) {
                return cityList.get(position).getProvince();
            }

            @Override
            public int getGroupHeight(int position) {
                View view= getLayoutInflater().inflate(R.layout.item_city_group,null,false);
                TextView tvName= (TextView) view.findViewById(R.id.tv_name);
                tvName.setText(getGroupName(position));
                view.measure(0,0);
                groupViews.put(position,view);
                groupHeight.put(position,view.getMeasuredHeight());
                return groupHeight.get(position,DensityUtil.dip2px(Day14StickyRecyclerViewActivity.this,40));
            }
        }));
        recyclerView.setAdapter(new CityAdapter(cityList));
    }
}

