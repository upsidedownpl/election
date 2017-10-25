package pl.mm.election.imports.user;

import pl.mm.election.model.to.UserTo;

public class UserImportTo {
	
	private UserTo user;
	private byte[] password;
	private byte[] salt;

	public UserTo getUser() {
		return user;
	}

	public void setUser(UserTo user) {
		this.user = user;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

}