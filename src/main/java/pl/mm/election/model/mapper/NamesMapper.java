package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Names;
import pl.mm.election.model.to.NamesTo;

class NamesMapper implements Mapper<NamesTo, Names>{
	
	NamesMapper() {
		//
	}
	
	public Names toPersistent(NamesTo to) {
		if(to == null) {
			return null;
		}
		
		Names po = new Names();
		po.setFirstName(to.getFirstName());
		po.setLastName(to.getLastName());
		return po;
	}
	
	public NamesTo toTransfer(Names po) {
		if(po == null) {
			return null;
		}
		
		NamesTo to = new NamesTo();
		to.setFirstName(po.getFirstName());
		to.setLastName(po.getLastName());
		return to;
	}
	
	@Override
	public Class<Names> persistentClass() {
		return Names.class;
	}
	
	@Override
	public Class<NamesTo> transferClass() {
		return NamesTo.class;
	}
	
}
