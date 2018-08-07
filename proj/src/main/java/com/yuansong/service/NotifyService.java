package com.yuansong.service;

import java.util.Collection;
import java.util.Map;

import com.yuansong.notify.MessageSender;

public interface NotifyService {
	
	public void refreshMessageSenderList();
	
	public Collection<MessageSender> getMessagerSenderList();
	
	public Map<String, MessageSender> getMessageSenderMap();
	
	public MessageSender getMessageSender(String key);

}
