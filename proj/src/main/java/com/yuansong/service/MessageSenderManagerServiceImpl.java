package com.yuansong.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yuansong.notify.MessageSender;

@Service
public class MessageSenderManagerServiceImpl implements MessageSenderManagerService {
	
	private final Logger logger = Logger.getLogger(MessageSenderManagerServiceImpl.class);
	
	private Map<String, MessageSender> map;
	
	public MessageSenderManagerServiceImpl() {
		map = new HashMap<String, MessageSender>();
	}

	@Override
	public Set<String> getMessageSenderIdList() {
		return map.keySet();
	}

	@Override
	public void add(String messageSenderId, MessageSender messageSender) {
		map.put(messageSenderId, messageSender);
		logger.info("MessageSender " + messageSenderId + "  is added");
	}

	@Override
	public void del(String messageSenderId) {
		if(map.containsKey(messageSenderId)) {
			map.remove(messageSenderId);
			logger.info("MessageSender " + messageSenderId + "  removed");
		}
	}

	@Override
	public void delAllMessageSender() {
		map.clear();
		logger.info("All MessageSender removed");
	}

	@Override
	public void resetMessageSender(String messageSenderId, MessageSender messageSender) {
		del(messageSenderId);
		add(messageSenderId, messageSender);
	}

	@Override
	public boolean hasMessageSender(String messageSenderId) {
		return map.containsKey(messageSenderId);
	}

	@Override
	public List<MessageSender> getMessageSenderList() {
		List<MessageSender> list = new ArrayList<MessageSender>();
		for(MessageSender messageSender : map.values()) {
			list.add(messageSender);
		}
		return list;
	}
}
