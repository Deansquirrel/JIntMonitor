package com.yuansong.service;

import java.util.Set;

import com.yuansong.taskjob.TaskWorker;

public interface TaskWorkerManagerService {
	
	public Set<String> getTaskIdList();
	
	public void addTask(String taskId, TaskWorker taskWorker, String cron);
	
	public void cancelTask(String taskId);
	
	public void cancelAllTask();
	
	public void resetTask(String taskId, TaskWorker taskWorker, String cron);
	
	public boolean hasTask(String taskId);

}
