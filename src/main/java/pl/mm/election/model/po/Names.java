package pl.mm.election.model.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Names  implements Persistent {

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
