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
import com.yuansong.pojo.DingMessageConfig;
import com.yuansong.pojo.HealthTaskConfig;
import com.yuansong.pojo.IntTaskConfig;
import com.yuansong.service.ConfigService;
import com.yuansong.task.ConfigRefreshTask;

@Controller
@RequestMapping(value="/Config")
public class ConfigController {
	
	private final Logger logger = Logger.getLogger(ConfigController.class);
	
	private Gson mGson = new Gson();
	
	@Autowired
	private ConfigService<IntTaskConfig> taskConfigService;
	
	@Autowired
	private ConfigService<HealthTaskConfig> healthConfigService;
	
	@Autowired
	private ConfigService<DingMessageConfig> dingMessageConfigService;
	
	@Autowired
	private ConfigRefreshTask configRefreshTask;
	
	@RequestMapping(value="/Refresh")
	public ModelAndView refresh(Map<String, Object> model){
		logger.debug("ConfigController Refresh");
		
		configRefreshTask.configReresh();
		
		model.put("info", "Refresh Complete");
		
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/List")
	public ModelAndView list(Map<String, Object> model){
		logger.debug("ConfigController List");
		
		Set<String> list;
		StringBuilder sb = new StringBuilder();
		sb.append("当前加载文件列表").append("<br />");
		
		sb.append("IntTaskCofig").append("<br />");
		list = taskConfigService.getConfigKeyList();
		for(String key : list) {
			sb.append(key).append("<br />");
		}
		
		sb.append("HealthConfig").append("<br />");
		list = healthConfigService.getConfigKeyList();
		for(String key : list) {
			sb.append(key).append("<br />");
		}
		
		sb.append("DingMessageSenderConfig").append("<br />");
		list = dingMessageConfigService.getConfigKeyList();
		for(String key : list) {
			sb.append(key).append("<br />");
		}
		
		model.put("info", sb.toString());
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/{configKey}")
	public ModelAndView taskConfig(@PathVariable String configKey, Map<String, Object> model){
		logger.debug("ConfigController taskConfig " + configKey);
		
		String result = "";
		
		if(taskConfigService.getConfigKeyList().contains(configKey)) {
			result = result + "IntTaskConfig" + "<br />";
			result = result + mGson.toJson(taskConfigService.getConfig(configKey)) + "<br />";
		}
		if(healthConfigService.getConfigKeyList().contains(configKey)) {
			result = result + "HealthConfig" + "<br />";
			result = result + mGson.toJson(healthConfigService.getConfig(configKey)) + "<br />";
		}
		
		if(dingMessageConfigService.getConfigKeyList().contains(configKey)) {
			result = result + "DingMessageConfig" + "<br />";
			result = result + mGson.toJson(dingMessageConfigService.getConfig(configKey)) + "<br />";
		}
		
		if(result.equals("")) {
			model.put("info", "Config is not exist.【" + configKey + "】");
		}
		else {
			model.put("info", result);			
		}
		return new ModelAndView("responsePage", model);
	}

}
