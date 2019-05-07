package domain.service;

import java.util.List;

import domain.model.Chat;

public interface ChatService {

	public void sendMessage(Chat chat);
	
	public void deleteMessage(Chat chat);
	
	public List<String> updateChat(Chat chat);
	
	//public void updateAllChat(Chat chat);
	
}
