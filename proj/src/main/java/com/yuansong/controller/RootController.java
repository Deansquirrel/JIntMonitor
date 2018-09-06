package com.yuansong.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.pojo.HealthTaskConfig;
import com.yuansong.pojo.IntTaskConfig;
import com.yuansong.pojo.JiraSearchConfig;
import com.yuansong.pojo.WebStateTaskConfig;
import com.yuansong.service.ConfigService;
import com.yuansong.service.TaskWorkerManagerServiceImpl;

@Controller
@RequestMapping(value="/")
public class RootController {

	private final Logger logger = Logger.getLogger(RootController.class);
	
	private final Gson mGson = new Gson();
	
	@Autowired
	TaskWorkerManagerServiceImpl taskManager;
	
	@Autowired
	private ConfigService<IntTaskConfig> intTaskConfigService;
	
	@Autowired
	private ConfigService<HealthTaskConfig> healthTaskConfigService;
	
	@Autowired
	private ConfigService<WebStateTaskConfig> webStateTaskConfigService;
	
	@Autowired
	private ConfigService<JiraSearchConfig> jiraSearchConfigService;
	
	@RequestMapping(value="/")
	public ModelAndView defaultPage(Map<String, Object> model){
		logger.debug("go to Current");
		return new ModelAndView("redirect:/Current");
	}
	
	@RequestMapping(value="/Current")
	public ModelAndView currentPage(Map<String, Object> model){
		logger.debug("RootController currentPage");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> listItem;
		
		for(HealthTaskConfig config : healthTaskConfigService.getConfigMap().values()) {
			listItem = new HashMap<String, String>();
			listItem.put("id", config.getId());
			listItem.put("title", config.getTitle());
			listItem.put("remark", config.getRemark());
			listItem.put("type", "Health");
			listItem.put("configUrl", "/TaskConfig/Health/Detail/" + config.getId());
			list.add(listItem);
		}
		
		for(IntTaskConfig config : intTaskConfigService.getConfigMap().values()) {
			listItem = new HashMap<String, String>();
			listItem.put("id", config.getId());
			listItem.put("title", config.getTitle());
			listItem.put("remark", config.getRemark());
			listItem.put("type", "Int");
			listItem.put("configUrl", "/TaskConfig/Int/Detail/" + config.getId());
			list.add(listItem);
		}
		
		for(WebStateTaskConfig config : webStateTaskConfigService.getConfigMap().values()) {
			listItem = new HashMap<String, String>();
			listItem.put("id", config.getId());
			listItem.put("title", config.getTitle());
			listItem.put("remark", config.getRemark());
			listItem.put("type", "WebState");
			listItem.put("configUrl", "/TaskConfig/WebState/Detail/" + config.getId());
			list.add(listItem);
		}
		
		for(JiraSearchConfig config : jiraSearchConfigService.getConfigMap().values()) {
			listItem = new HashMap<String, String>();
			listItem.put("id", config.getId());
			listItem.put("title", config.getTitle());
			listItem.put("remark", config.getRemark());
			listItem.put("type", "JiraSearch");
			listItem.put("configUrl", "/TaskConfig/JiraSearch/Detail/" + config.getId());
			list.add(listItem);
		}
		
		list.sort(new Comparator<Map<String, String>>(){
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				String str1 = o1.get("type") + o1.get("title") + o1.get("remark");
				String str2 = o2.get("type") + o2.get("title") + o2.get("remark");
				Collator instance = Collator.getInstance(Locale.CHINA);
				return instance.compare(str1, str2);
			}
		});
		
		model.put("list", list);
		
		
		
		List<String> menuList = new ArrayList<String>();
		menuList.add("Current");
		
		model.put("menulist", mGson.toJson(menuList));
		
		return new ModelAndView("CurrList", model);
	}
	
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
	
	
	@RequestMapping(value="/Test")
	public ModelAndView test(Map<String, Object> model){
		logger.info("RootController Test");
		
		model.put("info", "Test");
		
		return new ModelAndView("responsePage", model);
	}
	
}