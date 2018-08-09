package com.yuansong.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.pojo.WebStateTaskConfig;

@Service
public class ConfigServiceWebStateImpl extends ConfigServiceAbstractImpl<WebStateTaskConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceWebStateImpl.class);
	
	private Gson mGson = new Gson();
	
	public ConfigServiceWebStateImpl() {
		super();
		logger.debug("ConfigServiceWebStateImpl Init");
	}

	@Override
	protected WebStateTaskConfig getConfigFromStr(String str) {
		return mGson.fromJson(str, WebStateTaskConfig.class);
	}

	@Override
	protected boolean checkConfig(String fileName, WebStateTaskConfig config) {
		if(config == null) {
			logger.error("WebStateTaskConfig is null.【" + fileName + "】");
			return false;
		}
		if(config.getUrl().equals("")) {
			logger.error("WebStateTaskConfig url can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getCorn().equals("")) {
			logger.error("WebStateTaskConfig corn can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getMsgContent().equals("")) {
			logger.error("WebStateTaskConfig msgContent can not be null.【" + fileName + "】");
			return false;
		}
		return true;
	}

}
