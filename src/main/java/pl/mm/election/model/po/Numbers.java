package pl.mm.election.model.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Numbers {

	@Column(nullable = false)
	private String pesel;

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

}
