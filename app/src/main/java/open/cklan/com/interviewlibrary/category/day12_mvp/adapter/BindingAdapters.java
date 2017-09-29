package open.cklan.com.interviewlibrary.category.day12_mvp.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * AUTHOR：lanchuanke on 17/9/29 09:25
 */
public class BindingAdapters {
    @BindingAdapter("url")
    public static void setUrl(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }
}
