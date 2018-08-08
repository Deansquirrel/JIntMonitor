package com.yuansong.taskjob;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.yuansong.notify.MessageSender;

public abstract class TaskWorkerAbstractImpl<Config> implements TaskWorker {
	
	private final Logger logger = Logger.getLogger(TaskWorkerAbstractImpl.class);
	
	private Gson mGson = new Gson();
	
	private Config config;
	private List<MessageSender> list;
	
	public TaskWorkerAbstractImpl(Config config, List<MessageSender> list) {
		this.config = config;
		this.list = list;
	}

	@Override
	public void run() {
		String msg = check();
		if(!msg.equals("")) {
			logger.info(msg);
			for(MessageSender msd : list) {
				msd.send(msg);
			}
		}
	}

	@Override
	public String getConfigStr() {
		return mGson.toJson(config);
	}
	
	protected Config getConfig() {
		return config;
	}
	
	/**
	 * 检查过程
	 * @return 空字符串：无异常；非空字符串：提示消息
	 */
	protected abstract String check();

}
