package com.yuansong.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class TaskManager {
	
	@Autowired
    TaskScheduler scheduler;
	
	private Map<String, ScheduledFuture<?>> list;
	
	public TaskManager() {
		list = new HashMap<String, ScheduledFuture<?>>();
	}
	
	public void addTask(String taskId, Runnable taskJob, String cron) {
		if(list.containsKey(taskId)) {
			throw new SchedulingException("the taskId[" + taskId + "] was added.");
		}
		
		ScheduledFuture<?> future = scheduler.schedule(taskJob, new CronTrigger(cron));
		list.put(taskId, future);
	}
	
	public void cancelTask(String taskId) {
		if(list.containsKey(taskId)) {
			list.get(taskId).cancel(true);
			list.remove(taskId);
		}
	}
	
	public void resetTriggerTask(String taskId, Runnable taskJob, String cron) {
		cancelTask(taskId);
		addTask(taskId, taskJob, cron);
	}
	
	public boolean hasTask(String taskId) {
		return list.containsKey(taskId);
	}

}
