package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="MESSAGES")
@Data
// ! The Messages database contains ALL messages exchanged between users !

// A message has a sender, a receiver and a message. Each message has a unique ID. e.g :

// senderId | receiverId | message      | messageId
//----------------------------------------------
//    1		|	   2	 |	Hello		|    1
// ----------------------------------------------
//    2		|	   1	 |	Hi		    |    2
// ----------------------------------------------
//    1		|	   3	 |	Baguette    |    3
// ----------------------------------------------
//    1		|	   2	 |	whatsup?    |    4

// If we want to retrieve a whole Chat between to users, we need to order each message by its ID (which is intrinsically its timestamp)
// containing the couple (senderId = user1Id, receiverId = user2Id)
// To display a chat between 2 users, the frontend needs the order of the couple (user1Id, user2Id) to know who is the sender and who is the receiver.

public class Message implements Serializable {

	private static final long serialVersionUID = 5220808821158624742L;
		
	@Column(name="SENDER")
	private Long senderId;
	
	@Column(name="RECEIVER")
	private Long receiverId;
	
	@Column(name="RECIPIENT")
	@NotNull
	private String recipient;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long messageId;

}