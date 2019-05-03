package api.msg;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.User;
import domain.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserProducerTest {
	@Mock
	private SimpleKafkaProducer<String, User> kafkaProducer;
	@Mock
	private UserService userService;
	
	private String topic = "users";

	@InjectMocks
	private UserProducer producer;
	
	@Test
	void testSendAllUsers() {
		List<User> users = getUsers();
		when(userService.getAll()).thenReturn(users);
		producer.sendAllUsers(this.topic);
		verify(kafkaProducer, times(users.size())).send(eq("users"), any(User.class));
	}

	@Test
	void testSendUser() {
		User user = getRandomUser();
		producer.send(user, this.topic);
		verify(kafkaProducer, times(1)).send("users", user);
	}

	@Test
	void testSendLong() {
		User user = getRandomUser();
		when(userService.get(user.getId())).thenReturn(user);
		producer.send(user.getId(), this.topic);
		verify(kafkaProducer, times(1)).send("users", user);
	}

	@Test
	void testSendLongNull() {
		User user = getRandomUser();
		when(userService.get(user.getId())).thenReturn(null);
		producer.send(user.getId(), this.topic);
		verify(kafkaProducer, times(0)).send("users", user);
	}
	
	private List<User> getUsers() {

		List<User> users = new ArrayList<>();
		long numberOfUser = Math.round((Math.random() * 10));
		for (int i = 0; i < numberOfUser; i++) {
			users.add(getRandomUser());
		}
		return users;
	}
	
	private User getRandomUser() {
		User user = new User();
		user.setUsername(UUID.randomUUID().toString());
		user.setDescription(UUID.randomUUID().toString());
		
		Random r = new Random();
		user.setRating(r.nextInt(6));
		

		return user;
	}
}
