package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.User;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Spy
	@PersistenceContext(unitName = "UserPUTest")
	EntityManager em;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	public void testGetAll() {
		int size = initDataStore();
		assertEquals(size, userServiceImpl.getAll().size());
	}

	@Test
	public void testGet() {
		initDataStore();
		List<User> users = userServiceImpl.getAll();
		if (users.isEmpty())
			initDataStore();
		Long id = users.get(0).getId();
		User user = userServiceImpl.get(id);

		assertEquals(users.get(0).getId(), user.getId());
		assertEquals(users.get(0).getUsername(), user.getUsername());
	}

	@Test
	public void testCreate() {
		User user = getRandomUser();
		userServiceImpl.create(user);

		User i = em.find(User.class, user.getId());

		assertTrue(user.equals(i));
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> userServiceImpl.create(user),
				"user already exists");

		    assertTrue(thrown.getMessage().contains("user already exists"));
	}

	@Test
	public void testDelete() {

		User user = getRandomUser();
		userServiceImpl.create(user);
		userServiceImpl.delete(user);

		User i = em.find(User.class, user.getId());

		assertTrue(i == null);
	}

	@Test
	public void testUpdate() {

		User user1 = getRandomUser();
		userServiceImpl.create(user1);
		User user2 = getRandomUser();
		user2.setId(user1.getId());

		userServiceImpl.update(user2);

		User i = em.find(User.class, user1.getId());
		assertTrue(user2.equals(i));
	}


	private List<User> getUsers() {

		List<User> users = new ArrayList<>();
		long numberOfUser = Math.round((Math.random() * 10)) + 1;
		for (int i = 0; i < numberOfUser; i++) {
			users.add(getRandomUser());
		}
		return users;
	}

	private int initDataStore() {
		int size = userServiceImpl.getAll().size();
		List<User> users = getUsers();
		for (User user : users) {
			userServiceImpl.create(user);
		}
		return size + users.size();
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
