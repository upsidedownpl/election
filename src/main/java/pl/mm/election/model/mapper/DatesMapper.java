package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Dates;
import pl.mm.election.model.to.DatesTo;

class DatesMapper implements Mapper<DatesTo, Dates>{
	
	public Dates toPersistent(DatesTo to) {
		if(to == null) {
			return null;
		}
		
		Dates po = new Dates();
		po.setBirthDate(to.getBirthDate());
		po.setDeathDate(to.getDeathDate());
		return po;
	}
	
	public DatesTo toTransfer(Dates po) {
		if(po == null) {
			return null;
		}
		
		DatesTo to = new DatesTo();
		to.setBirthDate(po.getBirthDate());
		to.setDeathDate(po.getDeathDate());
		return to;
	}
	
	@Override
	public Class<Dates> persistentClass() {
		return Dates.class;
	}
	
	@Override
	public Class<DatesTo> transferClass() {
		return DatesTo.class;
	}
	
}
