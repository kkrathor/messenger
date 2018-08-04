 package io.kkrathore.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import io.kkrathore.messenger.database.DatabaseClass;
import io.kkrathore.messenger.model.Message;


public class MessageService {
	
	private Map<Long, Message> messages =  DatabaseClass.getMessages();
	
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageList= new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		
		for(Message message:messages.values()) {
			cal.setTime(message.getCreate());
			if(cal.get(Calendar.YEAR) == year) {
				messageList.add(message);
			}
		}
		return messageList;
	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > messages.size()) return new ArrayList<Message>();
		
		return list.subList(start, start+size);
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() +1);
		
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}
		
		messages.put(message.getId(), message);
		return message;
	}
	public Message  removeMessage(long id) {
		return messages.remove(id);
	}

	public MessageService() {
		
		messages.put(1L, new Message(1, "Hello World m1!", "kkrathor"));
		messages.put(2L, new Message(2, "Hello Jersey m2", "kkrathor"));
	}
	
//	
//	public List<Message> getMessages(){
//		Message m1 = new Message(1, "Hello World m1!", "kkrathor");
//		Message m2 = new Message(2, "Hello Jersey m2", "kkrathor");
//		List<Message> list = new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		return list;
//	}

}
