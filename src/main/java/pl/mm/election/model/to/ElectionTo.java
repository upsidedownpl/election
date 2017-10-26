package pl.mm.election.model.to;

import java.util.Date;
import java.util.List;

public class ElectionTo implements Transfer {

	private Integer id;
	
	private String name;

	private Date start;

	private Date end;

	private List<CandidateTo> candidates;

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

	public List<CandidateTo> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateTo> candidates) {
		this.candidates = candidates;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
