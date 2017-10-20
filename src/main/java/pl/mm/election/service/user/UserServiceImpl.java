package pl.mm.election.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mm.election.dao.UserDao;
import pl.mm.election.model.mapper.UserMapper;
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
	public UserTo create(UserTo userTo, String password) throws UserCreationException {
		User user = UserMapper.toPersistent(userTo);
		
		try {
			encryptionService.setPassword(user, password);
		} catch (EncryptionException e) {
			throw new UserCreationException();
		}
		userDAO.save(user);
		
		return UserMapper.toTransfer(user);
	}
	
}
