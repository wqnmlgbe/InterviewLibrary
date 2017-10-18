package open.cklan.com.interviewlibrary.category.day14_customview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/10/18 09:01
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private List<City> datas;

    public CityAdapter(List<City> datas) {
        this.datas = datas;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city,parent,false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bindData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_city)
        TextView tvCity;

        public CityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindData(City city){
            tvCity.setText(city.getName());
        }
    }
}
