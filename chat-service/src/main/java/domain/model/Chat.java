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
@Table(name="CHAT")
@Data
// A chat has a sender, a receiver and a message. Each sender and receiver are users represented by a unique id.
public class Chat implements Serializable {

	private static final long serialVersionUID = 5220808821158624742L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SENDER")
	private Long senderId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RECEIVER")
	private Long receiverId;
	
	@Column(name="MESSAGE")
	@NotNull
	private String name;
	
	
	
}