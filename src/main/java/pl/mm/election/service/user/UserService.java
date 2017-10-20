package pl.mm.election.service.user;

import pl.mm.election.model.User;

public interface UserService {

	void create(User user, String password) throws UserCreationException;

}
