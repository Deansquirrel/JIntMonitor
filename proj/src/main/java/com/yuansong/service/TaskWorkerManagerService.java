package com.yuansong.service;

import java.util.Set;

public interface TaskWorkerManagerService {
	
	public Set<String> getTaskIdList();
	
	public void addTask(String taskId, Runnable taskWorker, String cron);
	
	public void cancelTask(String taskId);
	
	public void cancelAllTask();
	
	public void resetTask(String taskId, Runnable taskWorker, String cron);
	
	public boolean hasTask(String taskId);

}
