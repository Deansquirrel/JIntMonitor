package com.yuansong.service;

import java.util.List;
import java.util.Set;

import com.yuansong.notify.MessageSender;

public interface MessageSenderManagerService {
	
	public Set<String> getMessageSenderIdList();
	
	public List<MessageSender> getMessageSenderList();
	
	public void add(String messageSenderId, MessageSender messageSender);
	
	public void del(String messageSenderId);
	
	public void delAllMessageSender();
	
	public void resetMessageSender(String messageSenderId, MessageSender messageSender);
	
	public boolean hasMessageSender(String messageSenderId);

}
