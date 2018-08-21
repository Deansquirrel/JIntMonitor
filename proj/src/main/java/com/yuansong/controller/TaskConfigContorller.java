package com.yuansong.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/TaskConfig")
public class TaskConfigContorller {
	
	private final Logger logger = Logger.getLogger(TaskConfigContorller.class);
	
	@RequestMapping(value="/IntTaskConfig/List")
	public ModelAndView intTaskConfigList(Map<String, Object> model) {
		logger.debug("RootController pageTest");
		
		return new ModelAndView("IntTaskConfigList", model);
	}

	@RequestMapping(value="/IntTaskConfig/Add")
	public ModelAndView intTaskConfigAdd(Map<String, Object> model) {
		logger.debug("RootController pageTest");
		
		return new ModelAndView("IntTaskConfigAdd", model);
	}
}
