package com.yuansong.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class TaskWorkerManagerServiceImpl implements TaskWorkerManagerService {
	
	private final Logger logger = Logger.getLogger(TaskWorkerManagerServiceImpl.class);
	
	@Autowired
    TaskScheduler scheduler;
	
	private Map<String, ScheduledFuture<?>> list;
	
	public TaskWorkerManagerServiceImpl() {
		list = new HashMap<String, ScheduledFuture<?>>();
	}
	
	@Override
	public void addTask(String taskId, Runnable taskWorker, String cron) {
		if(list.containsKey(taskId)) {
			throw new SchedulingException("the taskId[" + taskId + "] was added.");
		}
		
		ScheduledFuture<?> future = scheduler.schedule(taskWorker, new CronTrigger(cron));
		list.put(taskId, future);
		logger.info("Task " + taskId + "  is added");
	}
	
	@Override
	public void cancelTask(String taskId) {
		if(list.containsKey(taskId)) {
			list.get(taskId).cancel(true);
			list.remove(taskId);
			logger.info("Task " + taskId + "  canceled");
		}
	}
	
	@Override
	public void cancelAllTask() {
		for(String taskId :list.keySet()) {
			list.get(taskId).cancel(true);
		}
		list.clear();
		logger.info("All Task canceled");
	}
	
	@Override
	public void resetTask(String taskId, Runnable taskWorker, String cron) {
		cancelTask(taskId);
		addTask(taskId, taskWorker, cron);
	}
	
	@Override
	public boolean hasTask(String taskId) {
		return list.containsKey(taskId);
	}

	@Override
	public Set<String> getTaskIdList() {
		return list.keySet();
	}

}
