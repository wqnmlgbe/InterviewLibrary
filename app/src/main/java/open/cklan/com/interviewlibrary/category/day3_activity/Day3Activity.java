package open.cklan.com.interviewlibrary.category.day3_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Note;
import com.example.Priority;
import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/8 09:31
 */
@Note("* Activity生命周期\n" +
        " *  1.新建Activity ->   onCreate onStart onResume  销毁-> onPause onStop onDestroy\n" +
        " *  2.跳转界面,按HOME键切到后台,锁屏->  onPause  onStop  返回-> onRestart onStart onResume\n" +
        " *  3.在页面内弹出Dialog样式的Activity ->  onPause 返回-> onResume\n" +
        " *  生命周期方法详解：\n" +
        " *  onCreate: 只会执行一次，Activity新建的时候执行\n" +
        " *  onRestart:  用户从不可见回来会执行\n" +
        " *  onStart: 界面可见不可操作，适合执行注册\n" +
        " *  onResume: 界面可操作\n" +
        " *  onPause: 界面可见不可操作，可以执行一些数据的永久保存，不能执行太耗时操作，否则会影响下一个生命周期的调用，" +
        "       因为这个方法一定会执行，某些异常情况onStop时会被系统销毁\n" +
        " *  onStop: 界面不可见，适合执行取消注册\n" +
        " *  onDestroy:  只会执行一次，Activity销毁时调用\n" +
        " *  onSaveInstanceState: 在Activity变得容易被系统销毁时会被调用，适合保存一些临时数据\n" +
        "判断Activity是否正在运行：if (activity == null || activity.isDestroyed() || activity.isFinishing())")
@Schema(name = "Activity")
public class Day3Activity extends BaseActivity {
    @BindView(R.id.tv_show)
    TextView tvShow;

    StringBuilder stringBuilder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day3_activity);
        ButterKnife.bind(this);
        stringBuilder=new StringBuilder();
        stringBuilder.append("onCreate\n");
        showLog();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        stringBuilder.append("onRestart\n");
        showLog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        stringBuilder.append("onStart\n");
        showLog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stringBuilder.append("onResume\n");
        showLog();
    }


    @Override
    protected void onPause() {
        super.onPause();
        stringBuilder.append("onPause\n");
        showLog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stringBuilder.append("onStop\n");
        showLog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        stringBuilder.append("onSaveInstanceState\n");
        showLog();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        stringBuilder.append("onRestoreInstanceState\n");
        showLog();
    }

    @OnClick({R.id.tv_clear_console,R.id.tv_redirect,R.id.tv_alert_dialog})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_clear_console:
                stringBuilder.delete(0,stringBuilder.length());
                showLog();
                break;
            case R.id.tv_redirect:
                startActivity(new Intent(this,Day3SencondActivity.class));
                break;
            case R.id.tv_alert_dialog:
                startActivity(new Intent(this,Day3DialogActivity.class));
                break;
        }
    }

    public void showLog(){
        tvShow.setText(stringBuilder.toString());
    }


}
