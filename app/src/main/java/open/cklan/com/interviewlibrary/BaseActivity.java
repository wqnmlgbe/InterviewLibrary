package open.cklan.com.interviewlibrary;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import open.cklan.com.interviewlibrary.category.day5_language.LanguageContextWrapper;
import open.cklan.com.interviewlibrary.home.MainActivity;
import open.cklan.com.interviewlibrary.home.NoteActivity;

/**
 * AUTHOR：lanchuanke on 17/9/6 10:10
 */
public class BaseActivity extends AppCompatActivity {
    protected static final String BUNDLE_ARG_TITLE="title";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle();
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null && !(this instanceof MainActivity)){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setTitle() {
        Intent intent = getIntent();
        if(intent!=null){
            String title=intent.getStringExtra(BUNDLE_ARG_TITLE);
            if(!TextUtils.isEmpty(title)){
                setTitle(title);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(R.id.action_help==id){
            NoteActivity.toNoteActivity(this,this.getClass().getPackage().getName());
        }else if(android.R.id.home==id){
            finish();
        }
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        //每次创建的时候指定本地化语言
//        super.attachBaseContext(LanguageContextWrapper.wrap(newBase,"zh"));
    }
}
