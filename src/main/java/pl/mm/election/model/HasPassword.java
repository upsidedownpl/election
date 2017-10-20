package pl.mm.election.model;

public interface HasPassword {

	byte[] getPassword();

	void setPassword(byte[] password);

	byte[] getSalt();

	void setSalt(byte[] salt);

}
