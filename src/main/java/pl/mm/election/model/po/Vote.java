package pl.mm.election.model.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"voter", "election", "candidate"})
	)
@Entity
public class Vote implements Persistent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vote")
	@SequenceGenerator(name="seq_vote", sequenceName = "seq_vote", allocationSize=50)
	private Long id;
	
	@OneToOne(optional=false)
	@JoinColumn(name="voter")
	private Citizen voter;
	
	@OneToOne(optional=false)
	@JoinColumn(name="election")
	private Election election;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="candidate")
	private Candidate candidate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Citizen getVoter() {
		return voter;
	}

	public void setVoter(Citizen voter) {
		this.voter = voter;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
}
