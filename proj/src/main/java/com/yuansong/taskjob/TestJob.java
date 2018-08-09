package com.yuansong.taskjob;

import org.apache.log4j.Logger;

import com.yuansong.common.DateTool;

public class TestJob implements  TaskWorker{
	
	private final Logger logger = Logger.getLogger(TestJob.class);

	@Override
	public void run() {
		logger.info("TestJob - " + DateTool.getDateStr());
	}

	@Override
	public String getConfigStr() {
		return "";
	}

}
