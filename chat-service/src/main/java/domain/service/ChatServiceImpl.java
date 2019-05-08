package domain.service;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import domain.model.Category;
import domain.model.Message;

public class ChatServiceImpl implements ChatService {

	@PersistenceContext(unitName = "InmemoryPU")
	private EntityManager em;
	
	// Add a new message (row) to the database
	public void sendMessage(Message message) {
		em.persist(message);
		em.flush();
	};
	
	// Delete a message (row)
	public void deleteMessage(Message message) {
		em.remove(em.contains(message) ? message : em.merge(message));
	};
	
	// Return an ordered list of messages between 2 users using their Id : This creates a chat
	// SELECT * WHERE (senderId = user1Id AND receiverId = user2Id) OR (senderId = user2Id AND receiverId = user1Id) ORDER BY messageId;
	public List<Message> updateChat(int user1Id, int user2Id) {
		TypedQuery<Message> query = em.createQuery("SELECT * WHERE (senderId = user1Id AND receiverId = user2Id) OR (senderId = user2Id AND receiverId = user1Id) ORDER BY messageId", Message.class);	
		List<Message> chat = query.getResultList();
		return chat;
	};
	
	// Return a list of chat, a chat being a list of messages
	public List<List<Message>> updateAllChat(int userId){
		return null;
	};
	
}
