package pl.mm.election.service.user;

import static pl.mm.election.model.mapper.MapperFactory.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mm.election.dao.user.UserDao;
import pl.mm.election.model.po.User;
import pl.mm.election.model.to.UserTo;
import pl.mm.election.service.encryption.EncryptionException;
import pl.mm.election.service.encryption.EncryptionServiceImpl;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDAO;

	@Autowired
	EncryptionServiceImpl encryptionService;
	
	@Override
	@Transactional
	public UserTo create(UserTo user, byte[] password, byte[] salt) {
		User persistent = map(user);
		persistent.setPassword(password);
		persistent.setSalt(salt);
		userDAO.save(persistent);
		
		return map(persistent);
	}
	
	@Override
	@Transactional
	public UserTo create(UserTo userTo, String password) throws UserCreationException {
		User user = map(userTo);
		
		try {
			encryptionService.setPassword(user, password);
		} catch (EncryptionException e) {
			throw new UserCreationException();
		}
		userDAO.save(user);
		
		return map(user);
	}
	
	@Override
	public UserTo getByLogin(String login) {
		return map(userDAO.read(login));
	}
	
}
