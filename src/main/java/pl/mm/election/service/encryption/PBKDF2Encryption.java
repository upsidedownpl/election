package pl.mm.election.service.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PBKDF2Encryption implements Encryption {

	private int iterations = 20000;
	private String algorithm = "PBKDF2WithHmacSHA1";
	
	@Override
	public byte[] encrypt(String source, byte[] salt, int derivedKeyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
		 KeySpec spec = new PBEKeySpec(source.toCharArray(), salt, 
				 iterations, derivedKeyLength);
		 
		 SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		 return f.generateSecret(spec).getEncoded();
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	
}
