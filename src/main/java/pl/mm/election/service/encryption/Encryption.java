package pl.mm.election.service.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Encryption {

	byte[] encrypt(String source, byte[] salt, int derivedKeyLength)
			throws NoSuchAlgorithmException, InvalidKeySpecException;

}
