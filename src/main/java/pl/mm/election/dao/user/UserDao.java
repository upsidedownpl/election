package pl.mm.election.dao.user;

import java.util.List;

import pl.mm.election.model.po.User;

public interface UserDao {

	User read(String login);

	void save(User user);

	List<User> read();
	
	long count();
} 
