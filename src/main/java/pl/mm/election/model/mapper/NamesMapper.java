package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Names;
import pl.mm.election.model.to.NamesTo;

class NamesMapper extends MapperImpl<NamesTo, Names> implements Mapper<NamesTo, Names>{
	
	NamesMapper() {
		//
	}
	
	@Override
	protected void mapTransferToPersistent(NamesTo to, Names po) {
		po.setFirstName(to.getFirstName());
		po.setLastName(to.getLastName());
	}
	
	@Override
	protected void mapPersistentToTransfer(Names po, NamesTo to) {
		to.setFirstName(po.getFirstName());
		to.setLastName(po.getLastName());
	}
	
	@Override
	public Class<Names> persistentClass() {
		return Names.class;
	}
	
	@Override
	public Class<NamesTo> transferClass() {
		return NamesTo.class;
	}
	
	@Override
	protected Names createPersistent() {
		return new Names();
	}
	
	@Override
	protected NamesTo createTransfer() {
		return new NamesTo();
	}
}
