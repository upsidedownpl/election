package pl.mm.election.service.encryption;

import pl.mm.election.model.po.HasPassword;

public interface EncryptionService {

	void setPassword(HasPassword target, String password) throws EncryptionException;

}
