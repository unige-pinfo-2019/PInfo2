package domain.service;

import java.util.List;

import domain.model.User;

public interface UserService {
	
	public long create(User user);
	
	public void delete(User user);
	
	public void update(User user);
	
	public User get(Long userId);
	
	public List<User> getAll();
	
}
