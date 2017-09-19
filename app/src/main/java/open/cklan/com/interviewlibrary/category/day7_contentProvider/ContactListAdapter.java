package open.cklan.com.interviewlibrary.category.day7_contentProvider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.category.day7_contentProvider.bean.Contact;

/**
 * AUTHOR：lanchuanke on 17/9/5 15:46
 */
public class ContactListAdapter extends RecyclerView.Adapter {
    private List<Contact> contacts =new ArrayList();
    public ContactListAdapter(List<Contact> notes) {
        this.contacts =notes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder viewHolder= (ContactViewHolder) holder;
        viewHolder.bindData(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts ==null?0: contacts.size();
    }

    public  class ContactViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_contact_name)
        TextView tvContactName;
        @BindView(R.id.tv_contact_phone)
        TextView tvContactPhone;
        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bindData(Contact contact){
            tvContactName.setText(contact.name);
            tvContactPhone.setText(contact.phone);
        }
    }
}
