package io.kkrathore.messenger.service;

import java.util.ArrayList;
import java.util.List;

import io.kkrathore.messenger.model.Message;

public class MessageService {
	
	
	public List<Message> getMessages(){
		Message m1 = new Message(1, "Hello World m1!", "kkrathor");
		Message m2 = new Message(2, "Hello Jersey m2", "kkrathor");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		return list;
	}

}
