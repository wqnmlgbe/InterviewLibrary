package open.cklan.com.interviewlibrary.category.day12_mvp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/26 10:31
 */
public class MessageDialog extends Dialog{

    public MessageDialog(Context context) {
        super(context);
    }

    public MessageDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        AlertParams params;
        Context context;
        public Builder(Context context){
            this.params=new AlertParams();
            this.context=context;
        }

        public Builder(Context context,AlertParams params){
            this.params=params;
            this.context=context;
        }

        public MessageDialog create(){
            if(context==null){
                throw new IllegalArgumentException("Context is null");
            }
            MessageDialog dialog=new MessageDialog(this.context, R.style.MessageDialogStyle);
            this.params.apply(dialog,context);
            return dialog;
        }




    }

    public static class AlertParams{
        private String title;
        private String msg;
        private String confirm;
        private String cancel;
        private View.OnClickListener confirmListener;
        private View.OnClickListener cancelListener;

        public static AlertParams newInstance(String msg){
            return new AlertParams().setTitle("温馨提示").setMsg(msg).setConfirm("确定").setConfirmListener(null);
        }

        public AlertParams setTitle(String title){
            this.title=title;
            return this;
        }

        public AlertParams setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public AlertParams setConfirm(String confirm) {
            this.confirm = confirm;
            return this;
        }

        public AlertParams setCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public AlertParams setConfirmListener(View.OnClickListener confirmListener) {
            this.confirmListener = confirmListener;
            return this;
        }

        public AlertParams setCancelListener(View.OnClickListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public void apply(final MessageDialog dialog, Context context){
            LayoutInflater inflater=LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.dialog_message,null);
            dialog.setContentView(view);
            TextView tvTitle= (TextView) view.findViewById(R.id.tv_title);
            if(!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
                tvTitle.setVisibility(View.VISIBLE);
            }else{
                tvTitle.setVisibility(View.GONE);
            }
            TextView tvMessage= (TextView) view.findViewById(R.id.tv_message);
            if(TextUtils.isEmpty(msg)){
                throw new IllegalArgumentException("message is null");
            }else{
                tvMessage.setText(msg);
            }

            Button btnConfirm= (Button) view.findViewById(R.id.btn_confirm);
            if(!TextUtils.isEmpty(confirm)){
                btnConfirm.setText(confirm);
                btnConfirm.setVisibility(View.VISIBLE);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialog!=null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                        if(confirmListener!=null){
                            confirmListener.onClick(v);
                        }
                    }
                });
            }else{
                btnConfirm.setVisibility(View.GONE);
            }
            Button btnCancel= (Button) view.findViewById(R.id.btn_cancle);
            if(!TextUtils.isEmpty(cancel)){
                btnCancel.setText(cancel);
                btnCancel.setVisibility(View.VISIBLE);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialog!=null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                        if(cancelListener!=null){
                            cancelListener.onClick(v);
                        }
                    }
                });
            }else{
                btnCancel.setVisibility(View.GONE);
            }
        }
    }
}
