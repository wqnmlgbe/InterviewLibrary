package open.cklan.com.interviewlibrary.category.day12_mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.CategoryItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day12_mvp.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.home.OnItemClickListener;

/**
 * AUTHOR：lanchuanke on 17/9/5 15:46
 */
public class BeautyListAdapter extends RecyclerView.Adapter {
    private List<BeautyListItemEntity> categoryItemList=new ArrayList();
    public BeautyListAdapter(List<BeautyListItemEntity> categoryItemList) {
        this.categoryItemList=categoryItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day12_beauty,parent,false);
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
        @BindView(R.id.imageview)
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bindData(final BeautyListItemEntity categoryItem){
            Glide.with(imageView.getContext()).load(categoryItem.img).crossFade().into(imageView);
        }
    }
}
