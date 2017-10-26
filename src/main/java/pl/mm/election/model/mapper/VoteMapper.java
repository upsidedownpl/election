package pl.mm.election.model.mapper;

import static pl.mm.election.model.mapper.MapperFactory.map;

import pl.mm.election.model.po.Vote;
import pl.mm.election.model.to.VoteTo;

class VoteMapper extends MapperImpl<VoteTo, Vote> implements Mapper<VoteTo, Vote>{
	
	@Override
	protected void mapTransferToPersistent(VoteTo to, Vote po) {
		po.setId(to.getId());
		po.setCandidate(map(to.getCandidate()));
		po.setElection(map(to.getElection()));
		po.setVoter(map(to.getVoter()));
	}
	
	@Override
	protected void mapPersistentToTransfer(Vote po, VoteTo to) {
		to.setId(po.getId());
		to.setCandidate(map(po.getCandidate()));
		to.setElection(map(po.getElection()));
		to.setVoter(map(po.getVoter()));
	}
	
	@Override
	protected Vote createPersistent() {
		return new Vote();
	}
	
	@Override
	protected VoteTo createTransfer() {
		return new VoteTo();
	}
	
	@Override
	public Class<Vote> persistentClass() {
		return Vote.class;
	}
	
	@Override
	public Class<VoteTo> transferClass() {
		return VoteTo.class;
	}
	
}
