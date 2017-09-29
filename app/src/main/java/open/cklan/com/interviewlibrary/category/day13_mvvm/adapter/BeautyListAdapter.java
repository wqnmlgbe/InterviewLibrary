package open.cklan.com.interviewlibrary.category.day13_mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day13_mvvm.entity.BeautyListItemEntity;
import open.cklan.com.interviewlibrary.databinding.ItemDay13BeautyBinding;

/**
 * AUTHOR：lanchuanke on 17/9/5 15:46
 */
public class BeautyListAdapter extends RecyclerView.Adapter {
    private List<BeautyListItemEntity> categoryItemList=new ArrayList();
    private Context context;
    public BeautyListAdapter(List<BeautyListItemEntity> categoryItemList, Context context) {
        this.categoryItemList=categoryItemList;
        this.context=context;
    }

    public void setCategoryItemList(List<BeautyListItemEntity> categoryItemList) {
        this.categoryItemList = categoryItemList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDay13BeautyBinding binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_day13_beauty,parent,false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder= (CategoryViewHolder) holder;
        viewHolder.getBinding().setUrl(categoryItemList.get(position).img);
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return categoryItemList==null?0:categoryItemList.size();
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder{
        ItemDay13BeautyBinding binding;

        public CategoryViewHolder(ItemDay13BeautyBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public ItemDay13BeautyBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemDay13BeautyBinding binding) {
            this.binding = binding;
        }
    }
}
