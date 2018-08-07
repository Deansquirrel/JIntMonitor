package com.yuansong.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.pojo.DingMessageConfig;

@Service
public class ConfigServiceDingMessageImpl extends ConfigServiceAbstractImpl<DingMessageConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceDingMessageImpl.class);
	
	private Gson mGson = new Gson();

	public ConfigServiceDingMessageImpl() {
		super();
		logger.debug("ConfigServiceDingMessageImpl Init. ");
	}

	@Override
	protected DingMessageConfig getConfigFromStr(String str) {
		return mGson.fromJson(str, DingMessageConfig.class);
	}

	@Override
	protected boolean checkConfig(String fileName, DingMessageConfig config) {
		if(config.getRobotToken().equals("")) {
			logger.error("DingMessageConfig RobotToken can not be null.【" + fileName + "】");
			return false;
		}
		return true;
	}

}
