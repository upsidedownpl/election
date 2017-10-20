package pl.mm.election.dao;

import java.util.List;

import pl.mm.election.model.User;

public interface UserDao {

	User read(String login);

	void save(User user);

	List<User> read();
	
	long count();
} 
