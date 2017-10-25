package pl.mm.election.model.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class User implements HasPassword, Persistent {

	@Id
	private String login;

	@Lob
	private byte[] password;
	
	@Lob
	private byte[] salt;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public byte[] getPassword() {
		return password;
	}

	@Override
	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Override
	public byte[] getSalt() {
		return salt;
	}

	@Override
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("login", login).toString();
	}
}
