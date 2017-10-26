package pl.mm.election.model.to;

public class VoteTo implements Transfer {

	private Long id;

	private CitizenTo voter;

	private ElectionTo election;

	private CandidateTo candidate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CitizenTo getVoter() {
		return voter;
	}

	public void setVoter(CitizenTo voter) {
		this.voter = voter;
	}

	public ElectionTo getElection() {
		return election;
	}

	public void setElection(ElectionTo election) {
		this.election = election;
	}

	public CandidateTo getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateTo candidate) {
		this.candidate = candidate;
	}

}
