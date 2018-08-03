package com.yuansong.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.yuansong.common.DateTool;

@Component
public class TestTask {
	
	private final Logger logger = Logger.getLogger(TestTask.class);
	
	@Autowired
    TaskScheduler scheduler;
	
//	private boolean started = false;
	
	public TestTask() {
		logger.info("Scheduled TestTask Init  - " + DateTool.getDateStr());
	}

	
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void checkStart() {
//		startSubJob();
//	}
//	
//	public void startSubJob() {
//		if(!started) {
//			scheduler.schedule(new TestJob(), new CronTrigger("0 0/1 * * * ?"));
//		}
//	}
	

//	@Scheduled(fixedDelay = 5000)
//  public void testFixedDelay() {
//		logger.info("testFixedDelay - " + DateTool.getDateStr());		
//	}
//	
//	@Scheduled(fixedRate = 6000)
//  public void testFixedRate(){
//      logger.info("testFixedRate - " + DateTool.getDateStr());
//  }
//	
//	@Scheduled(initialDelay = 3000, fixedRate = 7000)
//  public void testInitialDelay(){
//      logger.info("testInitialDelay - " + DateTool.getDateStr());
//  }
	
//	@Scheduled(cron = "0 0/1 * * * ?")
//  public void testCron(){
//      logger.info("testCron - " + DateTool.getDateStr());
//  }

}
