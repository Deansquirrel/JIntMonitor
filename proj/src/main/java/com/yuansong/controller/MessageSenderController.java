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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yuansong.common.CommonFun;
import com.yuansong.pojo.DingMessageConfig;
import com.yuansong.service.ConfigService;

@Controller
@RequestMapping(value="/MessageSender/Ding")
public class MessageSenderController {

	private final Logger logger = Logger.getLogger(MessageSenderController.class);

	private Gson mGson = new Gson();	
	
	@Autowired
	private ConfigService<DingMessageConfig> dingMessageConfigService;
	
	@RequestMapping(value="/List")
	public ModelAndView dingMessageConfigList(Map<String, Object> model) {
		logger.debug("RootController dingMessageConfigList");
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> listItem;
		for(DingMessageConfig config : dingMessageConfigService.getSetConfigList()) {
			listItem = new HashMap<String, String>();
			listItem.put("id", config.getId());
			listItem.put("title", config.getTitle());
			listItem.put("remark", config.getRemark());
			list.add(listItem);
		}
		list.sort(new Comparator<Map<String, String>>(){
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				String str1 = o1.get("title") + o1.get("remark");
				String str2 = o2.get("title") + o2.get("remark");
				Collator instance = Collator.getInstance(Locale.CHINA);
				return instance.compare(str1, str2);
			}
		});
		model.put("list", list);
		
		List<String> menuList = new ArrayList<String>();
		menuList.add("MessageSender");
		menuList.add("Ding");
		menuList.add("List");
		
		model.put("menulist", mGson.toJson(menuList));
		
		return new ModelAndView("MessageSenderConfigDingList", model);
	}
	
	
	@RequestMapping(value="/Add",method=RequestMethod.GET)
	public ModelAndView dingMessageConfigAdd(Map<String, Object> model) {
		logger.debug("RootController dingMessageConfigAdd");
		
		List<String> menuList = new ArrayList<String>();
		menuList.add("MessageSender");
		menuList.add("Ding");
		menuList.add("Add");
		
		model.put("menulist", mGson.toJson(menuList));
		
		return new ModelAndView("MessageSenderConfigDingAdd", model);
	}
	
	@Transactional
	@RequestMapping(value="/Add",method=RequestMethod.POST)
	public ModelAndView dingMessageConfigAddAction(
			@RequestParam("i-title") String title,
			@RequestParam("i-remark") String remark,
			@RequestParam("i-robottoken") String robotToken,
			Map<String, Object> model) {
		logger.debug("RootController dingMessageConfigAddAction");
		
		DingMessageConfig config = new DingMessageConfig();
		config.setId(CommonFun.UUID());
		config.setTitle(title.trim());
		config.setRemark(remark.trim());
		config.setRobotToken(robotToken);
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "0");
		data.put("errDesc","success");
		
		try {
			dingMessageConfigService.add(config);
		}catch(Exception ex) {
			data.put("errCode", "-1");
			data.put("errDesc",ex.getMessage());
			ex.printStackTrace();
		}
		
		model.put("info", mGson.toJson(data));
		
		return new ModelAndView("responsePage", model);
	}
	
	@Transactional
	@RequestMapping(value="/Del",method=RequestMethod.POST)
	public ModelAndView webStateTaskConfigDelAction(
			@RequestParam("i-id") String id,
			Map<String, Object> model) {
		logger.debug("RootController webStateTaskConfigDelAction");
		logger.debug(id);
		
		DingMessageConfig config = new DingMessageConfig();
		config.setId(id);
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "0");
		data.put("errDesc","success");
		
		try {
			dingMessageConfigService.del(config);
		}catch(Exception ex) {
			data.put("errCode", "-1");
			data.put("errDesc",ex.getMessage());
			ex.printStackTrace();
		}
		
		model.put("info", mGson.toJson(data));
		
		return new ModelAndView("responsePage", model);
	}
	
	@RequestMapping(value="/Detail/{taskId}")
	public ModelAndView dingMessageConfigDetail(@PathVariable String taskId, Map<String, Object> model) {
		logger.debug("RootController webStateTaskConfigDetail - " + taskId);
		
		if(!taskId.trim().equals("")) {
			DingMessageConfig config = dingMessageConfigService.getSetConfig(taskId);
			if(config != null) {
				model.put("config",config);
			}
		}
		
		List<String> menuList = new ArrayList<String>();
		menuList.add("MessageSender");
		menuList.add("Ding");
		menuList.add("List");
		menuList.add("Detail");
		
		model.put("menulist", mGson.toJson(menuList));
		
		return new ModelAndView("MessageSenderConfigDingDetail", model);
	}
	
}
