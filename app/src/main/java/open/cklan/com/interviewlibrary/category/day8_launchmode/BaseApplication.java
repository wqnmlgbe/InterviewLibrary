package open.cklan.com.interviewlibrary.category.day8_launchmode;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BaseApplication extends Application{

	private HashMap<Integer, Stack<LaunchModeBaseActivity>> tasks;
	
	private ActivityManager manager;
	
	private boolean intentFilterMode;
	
	@Override
	public void onCreate() {
		super.onCreate();
		manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		tasks = new HashMap<Integer, Stack<LaunchModeBaseActivity>>();
	}
	
	public void pushToStack(LaunchModeBaseActivity activity){
		int currentTaskId = getCurrentTaskId();
		if(!tasks.containsKey(currentTaskId)){
			tasks.put(currentTaskId, new Stack<LaunchModeBaseActivity>());
		}
		Stack<LaunchModeBaseActivity> stack = tasks.get(currentTaskId);
		stack.add(activity);
	}
	
	public int getCurrentTaskId() {
		List<RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
		RunningTaskInfo runningTask = runningTasks.get(0);
		return runningTask.id;
	}

	public void removeFromStack(LaunchModeBaseActivity activity){
		Stack<LaunchModeBaseActivity> stack = tasks.get(getCurrentTaskId());
		if(stack != null){
			stack.remove(activity);
		}
	}
	
	public Stack<LaunchModeBaseActivity> getCurrentTask(){
		return tasks.get(getCurrentTaskId());
	}
	
	public void toggleIntentFilterMode(){
		intentFilterMode = !intentFilterMode;
	}
	
	public boolean isIntentFilterMode(){
		return intentFilterMode;
	}

	public HashMap<Integer, Stack<LaunchModeBaseActivity>> getTasks() {
		return tasks;
	}
}
