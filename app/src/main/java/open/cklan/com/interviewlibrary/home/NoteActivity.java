package open.cklan.com.interviewlibrary.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.R;
import open.cklan.com.interviewlibrary.utils.NoteUtil;

/**
 * AUTHOR：lanchuanke on 17/9/6 17:39
 */
public class NoteActivity extends AppCompatActivity{
    private static final String BUNDLE_ARG_PACKAGE_NAME="packageName";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String packageName;

    public static final void toNoteActivity(Context context,String packageName){
        if(context==null || packageName==null)return;
        Intent intent=new Intent();
        intent.setClass(context,NoteActivity.class);
        intent.putExtra(BUNDLE_ARG_PACKAGE_NAME,packageName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        setTitle("笔记");
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        packageName=getIntent().getStringExtra(BUNDLE_ARG_PACKAGE_NAME);
        NoteListAdapter adapter=new NoteListAdapter(NoteUtil.getNoteByPkg(this.getPackageCodePath(),packageName));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(android.R.id.home==item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
