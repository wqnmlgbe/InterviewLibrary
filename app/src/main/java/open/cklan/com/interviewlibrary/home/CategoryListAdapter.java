package open.cklan.com.interviewlibrary.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.bean.CategoryItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/5 15:46
 */
public class CategoryListAdapter extends RecyclerView.Adapter {
    private List<CategoryItem> categoryItemList=new ArrayList();
    private OnItemClickListener itemClickListener;
    public CategoryListAdapter(List<CategoryItem> categoryItemList,OnItemClickListener itemClickListener) {
        this.categoryItemList=categoryItemList;
        this.itemClickListener=itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder= (CategoryViewHolder) holder;
        viewHolder.bindData(categoryItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryItemList==null?0:categoryItemList.size();
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_category)
        TextView tvCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bindData(final CategoryItem categoryItem){
            tvCategory.setText(categoryItem.name);
            tvCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(categoryItem);
                }
            });
        }
    }
}
