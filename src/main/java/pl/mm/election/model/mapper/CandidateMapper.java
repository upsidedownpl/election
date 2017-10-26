package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Candidate;
import pl.mm.election.model.to.CandidateTo;

abstract class CandidateMapper<T extends CandidateTo, P extends Candidate> extends MapperImpl<T, P> {
	
	@Override
	protected void mapPersistentToTransfer(P po, T to) {
		to.setId(po.getId());
	}
	
	@Override
	protected void mapTransferToPersistent(T to, P po) {
		po.setId(to.getId());
	}
	
}
