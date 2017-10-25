package pl.mm.election.model.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PersonCandidate extends Candidate{

	@ManyToOne(optional=false)
	@JoinColumn(name="citizen")
	private Citizen citizen;

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	
}
