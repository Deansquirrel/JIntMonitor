package com.yuansong.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.common.CommonFun;
import com.yuansong.service.TaskManager;
import com.yuansong.taskjob.TestJob;

@Controller
@RequestMapping(value="/")
public class RootController {

	private final Logger logger = Logger.getLogger(RootController.class);
	
	private final Gson mGson = new Gson();
	
	private String taskId;
	
	@Autowired
	TaskManager taskManager;
	
//	@RequestMapping(value="/")
//	public ModelAndView defaultPage(Map<String, Object> model){
//		logger.debug("跳转到 rootPage");
//		System.out.println("跳转到 rootPage");
//		return new ModelAndView("redirect:/rootPage");
//	}
	
	@RequestMapping(value="/PageNotFound")
	public ModelAndView pageNotFound(Map<String, Object> model){
		logger.info("RootController PageNotFound");
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "404");
		data.put("errDesc","Page not found.");
		
		model.put("info", mGson.toJson(data));
		
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/testPage")
	public ModelAndView testPage(Map<String, Object> model){
		logger.info("RootController testPage");
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "0");
		data.put("errDesc","");
		data.put("data", "testPage");
		
		model.put("info", mGson.toJson(data));

		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/testPageOne")
	public ModelAndView testPageOne(Map<String, Object> model){
		logger.info("RootController testPageOne");
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "0");
		data.put("errDesc","");
		data.put("data", "testPageOne");
		
		model.put("info", mGson.toJson(data));

		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/testErrorPage")
	public ModelAndView testErrorPage(Map<String, Object> model){
		logger.info("RootController testErrorPage");
		
		throw new RuntimeException("testErrorPage");
	}
	
	@RequestMapping(value="/addTask")
	public ModelAndView addTask(Map<String, Object> model) {
		taskId = CommonFun.UUID();
		taskManager.addTask(taskId, new TestJob(), "0/1 0/1 * * * ?");
		
		model.put("info", taskId);

		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/stopTask")
	public ModelAndView stopTask(Map<String, Object> model) {
		
		taskManager.cancelTask(taskId);
		
		model.put("info", taskId);

		return new ModelAndView("responsePage", model);
	}
	
}
