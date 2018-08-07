package com.yuansong.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuansong.notify.DingMessageSender;
import com.yuansong.notify.MessageSender;
import com.yuansong.pojo.DingMessageConfig;

@Service
public class NotifyServiceImpl implements NotifyService {
	
	@Autowired
	private ConfigService<DingMessageConfig> dingMessageConfigService;
	
	private Map<String, MessageSender> map;
	
	public NotifyServiceImpl() {
		map = new HashMap<String, MessageSender>();
	}

	@Override
	public void refreshMessageSenderList() {
		Map<String, List<String>> changeMap = dingMessageConfigService.refreshConfigList("notifyConfig\\DingMessage");
		DingMessageSender dingMessageSender;
		for(String key : changeMap.get("add")) {
			dingMessageSender = new DingMessageSender(dingMessageConfigService.getConfig(key).getRobotToken());
			map.put(key, dingMessageSender);
		}
		for(String key : changeMap.get("cancel")) {
			dingMessageSender = new DingMessageSender(dingMessageConfigService.getConfig(key).getRobotToken());
			map.remove(key);
		}
		for(String key : changeMap.get("refresh")) {
			dingMessageSender = new DingMessageSender(dingMessageConfigService.getConfig(key).getRobotToken());
			map.put(key, dingMessageSender);
		}
	}

	@Override
	public Collection<MessageSender> getMessagerSenderList() {
		return map.values();
	}

	@Override
	public Map<String, MessageSender> getMessageSenderMap() {
		return map;
	}

	@Override
	public MessageSender getMessageSender(String key) {
		return map.get(key);
	}

}
