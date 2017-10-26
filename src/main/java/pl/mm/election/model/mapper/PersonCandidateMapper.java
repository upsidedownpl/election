package pl.mm.election.model.mapper;

import static pl.mm.election.model.mapper.MapperFactory.map;

import pl.mm.election.model.po.PersonCandidate;
import pl.mm.election.model.to.PersonCandidateTo;

class PersonCandidateMapper extends CandidateMapper<PersonCandidateTo, PersonCandidate> {
	
	@Override
	protected void mapTransferToPersistent(PersonCandidateTo to, PersonCandidate po) {
		super.mapTransferToPersistent(to, po);
		po.setCitizen(map(to.getCitizen()));
	}
	
	@Override
	protected void mapPersistentToTransfer(PersonCandidate po, PersonCandidateTo to) {
		super.mapPersistentToTransfer(po, to);
		to.setCitizen(map(po.getCitizen()));
	}
	
	@Override
	protected PersonCandidate createPersistent() {
		return new PersonCandidate();
	}
	
	@Override
	protected PersonCandidateTo createTransfer() {
		return new PersonCandidateTo();
	}
	
	@Override
	public Class<PersonCandidate> persistentClass() {
		return PersonCandidate.class;
	}
	
	@Override
	public Class<PersonCandidateTo> transferClass() {
		return PersonCandidateTo.class;
	}
	
}
