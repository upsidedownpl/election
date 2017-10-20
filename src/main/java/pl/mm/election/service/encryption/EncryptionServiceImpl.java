package pl.mm.election.service.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mm.election.model.HasPassword;

@Service
public class EncryptionServiceImpl implements EncryptionService {
	
	private static final Logger log = LoggerFactory.getLogger(EncryptionServiceImpl.class);

	@Autowired
	Encryption encryption;
	
	@Autowired
	Salt salt;
	
	private int derivedKeyLength = 160;

	public boolean matchEncrypted(String attempted, byte[] encrypted, byte[] salt) throws EncryptionException {
		try {
			byte[] attemptedEncrypted = encryption.encrypt(attempted, salt, derivedKeyLength);
			return Arrays.equals(encrypted, attemptedEncrypted);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("Encryption error", e);
			throw new EncryptionException();
		}
	}
	
	@Override
	public void setPassword(HasPassword target, String password) throws EncryptionException {
		try {
			byte[] generatedSalt = salt.generateSalt();
			byte[] encrypted = encryption.encrypt(password, generatedSalt, derivedKeyLength);
			
			target.setPassword(encrypted);
			target.setSalt(generatedSalt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("Encryption error", e);
			throw new EncryptionException();
		}
	}

	public void setDerivedKeyLength(int derivedKeyLength) {
		this.derivedKeyLength = derivedKeyLength;
	}
	
}
