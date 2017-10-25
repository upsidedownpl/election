package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Numbers;
import pl.mm.election.model.to.NumbersTo;

class NumbersMapper implements Mapper<NumbersTo, Numbers>{
	
	NumbersMapper() {
		//
	}
	
	public Numbers toPersistent(NumbersTo to) {
		if(to == null) {
			return null;
		}
		
		Numbers po = new Numbers();
		po.setPesel(to.getPesel());
		return po;
	}
	
	public NumbersTo toTransfer(Numbers po) {
		if(po == null) {
			return null;
		}
		
		NumbersTo to = new NumbersTo();
		to.setPesel(to.getPesel());
		return to;
	}
	
	@Override
	public Class<Numbers> persistentClass() {
		return Numbers.class;
	}
	
	@Override
	public Class<NumbersTo> transferClass() {
		return NumbersTo.class;
	}
}
