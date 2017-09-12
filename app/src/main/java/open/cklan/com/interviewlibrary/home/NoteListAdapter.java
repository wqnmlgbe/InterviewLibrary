package open.cklan.com.interviewlibrary.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class NoteListAdapter extends RecyclerView.Adapter {
    private List<String> notes=new ArrayList();
    public NoteListAdapter(List<String> notes) {
        this.notes=notes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder= (CategoryViewHolder) holder;
        viewHolder.bindData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes==null?0:notes.size();
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_note)
        TextView tvNote;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bindData(String note){
            tvNote.setText(note);
        }
    }
}
