package open.cklan.com.interviewlibrary.category.day8_launchmode;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.Note;
import com.example.Schema;

import butterknife.BindView;
import butterknife.ButterKnife;
import open.cklan.com.interviewlibrary.BaseActivity;
import open.cklan.com.interviewlibrary.R;

@Note("*LaunchMode:\n" +
        " *   standard:默认的启动模式，每次启动都会创建新的实例，并放到当前任务中，一个任务中可以有多个这种Activity实例\n" +
        " *   singleTop:如果当前任务中存在这种实例，并且在栈顶，就不会创建实例，会调用该实例的OnNewIntent方法，否则会创建实例，一个任务中可以有多个这种Activity实例\n" +
        " *   singleTask:如果现有任务（不止当前任务）中存在这种实例，会把该任务放到前台，并把之上的Activity销毁，声明成这种的Activity，一个任务中只会有一个实例，\n" +
        " *      注意：如果没有指定taskAffinity,默认为包名，在当前应用中打开是不会新建任务，如果指定了taskAffinity，则会打开新的任务（现有任务中不存在实例的情况下）\n" +
        " *   singleInstance:一个任务中只会含有一个实例，和singleTask类似，和singleTask不同的是：1，singleTask打开其他的页面会继续在当前任务中打开，singleInstance因任务中只能存在他一个实例\n" +
        " *   ，打开其他的页面在其他任务打开。2，即使不指定taskAffinity，singleInstance也是在其他任务打开\n" +
        " *\n" +
        " *Intent Flag:\n" +
        " *  FLAG_ACTIVITY_NEW_TASK:1.如果现有任务中已经有该实例，就把该任务带到前台，不会销毁之上的Activity,如果没有该实例，就会在该taskAffinity的任务中新建实例\n" +
        " *      2.如果taskAffinity和默认的一样，就和standard一样启动一个新的Activity\n" +
        " *  FLAG_ACTIVITY_SINGLE_TOP:和singleTop效果一样\n" +
        " *  FLAG_ACTIVITY_CLEAR_TOP:如果当前任务中已有该实例，则会新建该实例，并将原实例和之上的Activity销毁，如果没有，会新建实例，如果希望与singleTask效果相同,可以加入FLAG_ACTIVITY_SINGLE_TOP\n" +
        " *Task属性：系统默认行为是：用户将任务切换到后台很长一段时间，系统会将除了最底层的Activity之外全部销毁，用户切换回来，再将最底层的Activity恢复\n" +
        " *  alwaysRetainTaskState：如果设为true，即使用户离开了任务很久，也不会销毁任务中的Activity\n" +
        " *  clearTaskOnLaunch：如果将最底层的Activity设为true，只要离开了该任务，系统会销毁之上的Activity\n" +
        " *  finishOnTaskLaunch：这个属性是作用于Acvitity上，不是任务上，某个Activity设置了该属性为true，用户只要离开了该任务，此Activity就会被销毁\n" +
        " *\n" +
        " *  TaskStackBuilder:实现类似QQ,微信点击通知栏，点击back返回到主界面，也可采用采用PendingIntent.getActivities()方法 \n" +
        " *")
public abstract class LaunchModeBaseActivity extends BaseActivity implements Constants {

	private final int DISPLAY_STACK_DELAY = 500;
    
    private String[] intentFlagsText = { "CLEAR_TOP", "CLEAR_WHEN_TASK_RESET", "EXCLUDE_FROM_RECENTS",
            "FORWARD_RESULT", "MULTIPLE_TASK", "NEW_TASK", "NO_HISTORY", "NO_USER_ACTION", "PREVIOUS_IS_TOP",
            "REORDER_TO_FRONT", "RESET_TASK_IF_NEEDED", "SINGLE_TOP" };

    private int[] intentFlags = { Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET,
            Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS, Intent.FLAG_ACTIVITY_FORWARD_RESULT,
            Intent.FLAG_ACTIVITY_MULTIPLE_TASK, Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_NO_HISTORY,
            Intent.FLAG_ACTIVITY_NO_USER_ACTION, Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP,
            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT, Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED,
            Intent.FLAG_ACTIVITY_SINGLE_TOP };

    @BindView(R.id.tv_show)
    TextView tvShow;

    private TextView lifecycle;
    private StringBuilder lifecycleFlow = new StringBuilder();
    private Handler handler = new Handler();
    private BaseApplication app;

    private int chosenFlags;
    
    private void logMethodName() {
        String methodName = getMethodName();
        Log.v(LOG_TAG, getLaunchMode() + ": " + methodName);
        lifecycleFlow.append(methodName).append("\n");
        if (lifecycle != null) {
            lifecycle.setText(lifecycleFlow.toString());
        }
    }

    private String getMethodName() {
        Thread current = Thread.currentThread();
        StackTraceElement trace = current.getStackTrace()[4];
        return trace.getMethodName();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logMethodName();
        setContentView(R.layout.activity_day8_launchmode);
        ButterKnife.bind(this);
        setTitle(this.getClass().getSimpleName());
        app = (BaseApplication) getApplication();
        app.pushToStack(this);
    }

    
    @Override
    protected void onResume() {
        logMethodName();
        checkIfReorderToFront();
        Runnable taskInfoDisplayer = new TaskInfoDisplayer(this);
        handler.postDelayed(taskInfoDisplayer, DISPLAY_STACK_DELAY);
        super.onResume();
    }

    private void checkIfReorderToFront() {
    	Intent intent = getIntent();
    	if (shouldReorderToFront(intent)){
    		app.removeFromStack(this);
    		app.pushToStack(this);
    	}
	}

	private boolean shouldReorderToFront(Intent intent) {
		int flags = intent.getFlags();
		return (flags & Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) > 0;
	}

	private String getLaunchMode() {
        return "[" + hashCode() + "] " + getClass().getSimpleName();
    }

    public void generalOnClick(View v) {
        if (app.isIntentFilterMode()) {
            showIntentFilterDialog(v);
        } else {
            startActivity(getNextIntent(v));
        }
    }

    private void showIntentFilterDialog(final View nextActivityBtn) {
        chosenFlags = 0;
        Builder builder = new Builder(this);
        prepareIntentFiltedDialog(builder);
        builder.setTitle("List selection");
        builder.setCancelable(true);
        final OnMultiChoiceClickListener onClick = new OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				chosenFlags |= intentFlags[which];
            }
        };
        builder.setMultiChoiceItems(intentFlagsText, null, onClick);
        builder.setPositiveButton("Done", new OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                Intent intent = getNextIntent(nextActivityBtn);
                intent.setFlags(chosenFlags);
                startActivity(intent);
            }
        });
        builder.show();
    }

    private void prepareIntentFiltedDialog(Builder build) {
    }

    private Intent getNextIntent(View v) {
        Class<? extends LaunchModeBaseActivity> nextActivity = null;
        switch (v.getId()) {
        case R.id.btn_standard:
            nextActivity = StandardActivity.class;
            break;
        case R.id.btn_singletop:
            nextActivity = SingleTopActivity.class;
            break;
        case R.id.btn_singletask:
            nextActivity = SingleTaskActivity.class;
            break;
        case R.id.btn_singleInstance:
            nextActivity = SingleInstanceActivity.class;
            break;
        }
        return new Intent(this, nextActivity);
    }

    @Override
    public void onContentChanged() {
        logMethodName();
        super.onContentChanged();
    }

    @Override
    protected void onDestroy() {
        logMethodName();
        app.removeFromStack(this);
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent newIntent) {
        logMethodName();
        super.onNewIntent(newIntent);
        setIntent(newIntent);
    }

    @Override
    protected void onPause() {
        logMethodName();
        super.onPause();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        logMethodName();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        logMethodName();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        logMethodName();
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        logMethodName();
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        logMethodName();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        logMethodName();
        super.onStart();
    }

    @Override
    protected void onStop() {
        logMethodName();
        super.onStop();
    }

    public void showTaskInfo(String info){
        tvShow.setText(info);
    }
    
}
