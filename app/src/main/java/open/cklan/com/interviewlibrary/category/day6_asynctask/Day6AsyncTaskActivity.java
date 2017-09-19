package open.cklan.com.interviewlibrary.category.day6_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Note;
import com.example.Schema;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

/**
 * AUTHOR：lanchuanke on 17/9/12 17:45
 */
@Note("* AsyncTask的用法：自定义类继承AsyncTask，并传入三种类型：Param(参数类型，execute时传入实参),Progress(进度类型，一般为Integer),Result(返回结果类型)\n" +
        " * doInBackground()是必须实现的，为工作线程，其他为可选，在UI线程\n" +
        " * 1.onPreExecute()  在执行doInBackground之前调用\n" +
        " * 2.doInBackground();  在工作线程执行具体任务\n" +
        " * 3.onProgressUpdate() 回调显示进度\n" +
        " * 4.onPostExecute()  任务执行完成，在UI线程执行最后操作\n" +
        " * 5.onCancelled() 任务取消的回调\n" +
        " * 6.publishProgress（）手动发布当前进度，在doInBackground()里面调用，在onProgressUpdate里面接收\n" +
        " * AsyncTask的原理：\n" +
        " * 1.AsyncTask内部有一个线程池(默认串行执行,一个进程所有的AsyncTask都在此线程池执行)和一个InternalHandler\n" +
        " * （获取主线程Looper，解决4.1之前在工作线程执行execute的bug,4.1-5.1之间是通过ActivityThread.main里面通过AsyncTask.init解决，因不可靠，5.1之后直接显示传入）\n" +
        " * 首先系统会把AsyncTask的参数封装为FutureTask对象，FutureTask是一个并发类，在这里它充当了Runnable的作用。\n" +
        " * 接着这个FutureTask会交给SerialExecutor的execute方法处理，execute方法会先把FutureTask对象插入到任务队列mTask中，\n" +
        " * 如果这时没有正在活动的任务，那么就会调用SeriaExecutor的scheduleNext()类执行下个任务。同一个AsyncTask任务执行完后，\n" +
        " * AsyncTask会继续执行其他任务知道所有任务都被执行完，从这一点可以看出，默认情况下AsyncTask是串行执行的。通过指定线程池，可以实现并行执行\n" +
        " * 通过handler发送和处理消息，执行回调\n" +
        " * AsyncTask的弊端：\n" +
        " * 内存泄漏：如果自定义类是非静态内部类，因非静态内部类含有对Activity的引用，有可能Activity销毁了，但是AsyncTask并没有执行完，保有对Activity的引用，导致Activity不能正常销毁\n" +
        " * 解决方法：可以使用WeakReference和静态内部类，注意使用多线程也会有同样的问题")
@Schema(name = "AsyncTask")
public class Day6AsyncTaskActivity extends BaseActivity {
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.tv_show_progress)
    TextView tvShowProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day6_asynctask);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_execute)
    public void execute(View view){
        new Day6AsyncTask(this).execute();
    }

    public void showProgress(int progress,String progressStr){
        progressBar.setProgress(progress);
        tvShowProgress.setText(progressStr);
    }

    static class Day6AsyncTask extends AsyncTask<Void,Integer,String>{
        WeakReference<Day6AsyncTaskActivity> activityWeakReference;

        public Day6AsyncTask(Day6AsyncTaskActivity activity) {
            activityWeakReference=new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(activityWeakReference.get()!=null){
                activityWeakReference.get().showProgress(100,s);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values!=null && values.length>0){
                if(activityWeakReference.get()!=null){
                    activityWeakReference.get().showProgress(values[0],"进度:"+values[0]+"%");
                }
            }
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(Void... params) {
            for(int i=1;i<=100;i++){
                try {
                    Thread.sleep(200);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "done";
        }
    }
}
