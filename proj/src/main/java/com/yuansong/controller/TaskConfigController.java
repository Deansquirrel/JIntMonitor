package com.yuansong.controller;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.pojo.TaskConfig;
import com.yuansong.service.TaskConfigServiceImpl;

@Controller
@RequestMapping(value="/TaskConfig")
public class TaskConfigController {
	
	private final Logger logger = Logger.getLogger(TaskConfigController.class);
	
	private Gson mGson = new Gson();
	
	@Autowired
	private TaskConfigServiceImpl taskConfigService;
	
	@RequestMapping(value="/Refresh")
	public ModelAndView refresh(Map<String, Object> model){
		logger.debug("TaskConfigController Refresh");
		
		taskConfigService.refreshConfigList();
		
		model.put("info", "Refresh Complete");
		
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/List")
	public ModelAndView list(Map<String, Object> model){
		logger.debug("TaskConfigController List");
		
		Set<String> list = taskConfigService.getTaskConfigKeyList();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("当前加载文件列表：").append("<br />");
		for(String key : list) {
			sb.append(key).append("<br />");
		}
		model.put("info", sb.toString());
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/{configKey}")
	public ModelAndView taskConfig(@PathVariable String configKey, Map<String, Object> model){
		logger.debug("TaskConfigController taskConfig " + configKey);
		
		TaskConfig taskConfig = taskConfigService.getConfig(configKey);
		if(taskConfig == null) {
			model.put("info", "TaskConfig is not exist.【" + configKey + "】");
		}
		else {
			logger.debug(mGson.toJson(taskConfig));
			model.put("info", mGson.toJson(taskConfig));			
		}
		return new ModelAndView("responsePage", model);
	}

}
