package com.yuansong.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.pojo.IntTaskConfig;

@Service
public class ConfigServiceIntTaskImpl extends ConfigServiceAbstractImpl<IntTaskConfig>{
	
	private final Logger logger = Logger.getLogger(ConfigServiceIntTaskImpl.class);
	
	private Gson mGson = new Gson();
	
	public ConfigServiceIntTaskImpl() {
		super();
		logger.debug("ConfigServiceIntTaskImpl Init. ");
	}

	@Override
	protected IntTaskConfig getConfigFromStr(String str) {
		return mGson.fromJson(str, IntTaskConfig.class);
	}

	@Override
	protected boolean checkConfig(String fileName, IntTaskConfig config) {
		if(config == null) {
			logger.error("IntTaskConfig is null.【" + fileName + "】");
			return false;
		}
		if(config.getServer().equals("")) {
			logger.error("IntTaskConfig Server can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getDbName().equals("")) {
			logger.error("IntTaskConfig DbName can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getUser().equals("")) {
			logger.error("IntTaskConfig User can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getSearch().equals("")) {
			logger.error("IntTaskConfig Search can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getCorn().equals("")) {
			logger.error("IntTaskConfig Corn can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getMsgContent().equals("")) {
			logger.error("IntTaskConfig MsgContent can not be null.【" + fileName + "】");
			return false;
		}
		return true;
	}
	
	
	
}
