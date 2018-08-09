package com.yuansong.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.pojo.HealthTaskConfig;

@Service
public class ConfigServiceHealthImpl extends ConfigServiceAbstractImpl<HealthTaskConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceHealthImpl.class);
	
	private Gson mGson = new Gson();
	
	public ConfigServiceHealthImpl() {
		super();
		logger.debug("ConfigServiceHealthImpl Init. ");
	}

	@Override
	protected HealthTaskConfig getConfigFromStr(String str) {
		return mGson.fromJson(str, HealthTaskConfig.class);
	}

	@Override
	protected boolean checkConfig(String fileName, HealthTaskConfig config) {
		if(config.getCorn().equals("")) {
			logger.error("HealthConfig Corn can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getMsgContent().equals("")) {
			logger.error("HealthConfig MsgContent can not be null.【" + fileName + "】");
			return false;
		}
		return true;
	}
	
	

}
