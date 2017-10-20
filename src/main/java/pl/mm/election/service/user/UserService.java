package pl.mm.election.service.user;

import pl.mm.election.model.to.UserTo;

public interface UserService {

	UserTo create(UserTo user, String password) throws UserCreationException;

}
