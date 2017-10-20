package pl.mm.election.service.encryption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mm.election.dao.UserDaoImpl;
import pl.mm.election.model.po.User;

@Service
public class AuthenticateService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticateService.class);
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Autowired
	private EncryptionServiceImpl encryptionService;
	
	public boolean authenticateUser(String login, String password) {
		User user = userDao.read(login);
		
		if(user != null) {
			if(user.getPassword() == null || user.getSalt() == null) {
				return false;
			}
			
			try {
				return encryptionService.matchEncrypted(password, user.getPassword(), user.getSalt());
			} catch (EncryptionException e) {
				log.info("Error authenticating user {}", login);
				return false;
			}
		} else {
			return false;
		}
	}
	

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}


	public void setEncryptionService(EncryptionServiceImpl encryptionService) {
		this.encryptionService = encryptionService;
	}
	
}
