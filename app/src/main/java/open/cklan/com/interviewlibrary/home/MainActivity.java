package open.cklan.com.interviewlibrary.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bean.CategoryItem;
import com.example.generate.helper.SchemaRepositoryHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

public class MainActivity extends BaseActivity implements OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<CategoryItem> categoryItemList;
    CategoryListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        categoryItemList= SchemaRepositoryHelper.initCategoryData();
        adapter=new CategoryListAdapter(categoryItemList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(CategoryItem categoryItem) {
        Intent intent=new Intent();
        intent.putExtra(BUNDLE_ARG_TITLE,categoryItem.name);
        intent.setClassName(this,categoryItem.className);
        startActivity(intent);

    }
}
