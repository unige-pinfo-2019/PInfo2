package domain.service;

import java.util.List;

import domain.model.Message;

public interface MessageService {

	// Add a new message (row) to the database
	public void sendMessage(Message message);
	
	// Delete a message (row) using the unique Id each message has
	public void deleteMessage(Message message);
	
	// Return an ordered list of messages between 2 users using their Id
	public List<Message> updateChat(int user1Id, int user2Id);
	
	// Return a list of chat, a chat being a list of messages
	public List<List<Message>> updateAllChat(int userId);
	
}
