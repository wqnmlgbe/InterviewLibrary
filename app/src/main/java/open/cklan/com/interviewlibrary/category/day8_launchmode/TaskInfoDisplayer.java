package open.cklan.com.interviewlibrary.category.day8_launchmode;

import android.support.v4.app.AppLaunchChecker;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class TaskInfoDisplayer implements Runnable, Constants {

    private final BaseApplication app;

    public TaskInfoDisplayer(LaunchModeBaseActivity launchModeBaseActivity) {
        app = (BaseApplication) launchModeBaseActivity.getApplication();
    }

    private void showTaskInfo() {
        HashMap<Integer, Stack<LaunchModeBaseActivity>> tasks = app.getTasks();
        StringBuilder stringBuilder=new StringBuilder();
        if(tasks!=null && tasks.keySet()!=null && tasks.keySet().size()>0){
            Set<Integer> taskIds = tasks.keySet();
            for(Integer taskId:taskIds){
                stringBuilder.append("taskId:  "+taskId+"\n");
                Stack<LaunchModeBaseActivity> stack = tasks.get(taskId);
                if(stack!=null && stack.size()>0){
                    for(LaunchModeBaseActivity activity:stack){
                        stringBuilder.append("\t \t \t"+activity.getClass().getSimpleName()+"\n");
                    }
                }
            }
        }
        Stack<LaunchModeBaseActivity> task = app.getCurrentTask();
        if(task!=null && task.size()>0){
            for (int location = task.size() - 1; location >= 0; location--) {
                LaunchModeBaseActivity activity = task.get(location);
                activity.showTaskInfo(stringBuilder.toString());
            }
        }
    }

    @Override
    public void run() {
       showTaskInfo();
    }


}
