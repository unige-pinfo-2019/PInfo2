package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.model.Chat;

public class ChatServiceImpl implements ChatService {

	public void sendMessage(Chat chat) {};
	
	public void deleteMessage(Chat chat) {};
	
	public List<String> updateChat(Chat chat) {
		List <String> emptyTestList = new ArrayList<String>();
		emptyTestList.add("test");
		
		return emptyTestList;
	};
	
	//public void updateAllChat(Chat chat);
	
}
