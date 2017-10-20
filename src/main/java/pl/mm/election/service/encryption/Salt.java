package pl.mm.election.service.encryption;

import java.security.NoSuchAlgorithmException;

public interface Salt {

	byte[] generateSalt() throws NoSuchAlgorithmException;
	
}
