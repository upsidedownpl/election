package pl.mm.election.model.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Election implements Persistent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_election")
	@SequenceGenerator(name="seq_election", sequenceName = "seq_election", allocationSize=50)
	private Integer id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Date start;

	@Column(nullable = false)
	private Date end;

	@OneToMany
	private List<Candidate> candidates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
