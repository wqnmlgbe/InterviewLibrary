package open.cklan.com.interviewlibrary.category.day5_language;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.Note;
import com.example.Schema;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/12 14:10
 */
@Note("* * 设置应用内多语言支持：\n" +
        " * 1.新建values-zh,values-en等文件夹，里面新建strings.xml，配置文本内容\n" +
        " * 2.切换语言后，系统版本25之后，使用Context.createConfigurationContext\n" +
        " *    之前使用Resources.updateConfiguration(已过时)\n" +
        " * 3.已经切换了，如何生效呢？1.简单粗暴，杀掉应用，重启  2.类似微信，重新跳转到首页，应用内界面比较多，其实还是可取的  3.页面较少，reCreate()重新创建")
@Schema(name = "language")
public class Day5LanguageActivity extends BaseActivity {
    @BindView(R.id.tv_show)
    TextView tvShow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day5_language);
        ButterKnife.bind(this);
        show();
    }

    private void show() {
        tvShow.setText(R.string.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Locale locale=null;
        switch (item.getItemId()){
            case R.id.action_en:
                locale=Locale.ENGLISH;
                break;
            case R.id.action_zh:
                locale=Locale.CHINA;
                break;
        }
        Resources resources=getResources();
        Configuration config=resources.getConfiguration();
        if(locale==null){
            return super.onOptionsItemSelected(item);
        }else{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                config.setLocale(locale);
                createConfigurationContext(config);
                recreate();
            }else{
                config.locale=locale;
                resources.updateConfiguration(config,resources.getDisplayMetrics());
                recreate();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
