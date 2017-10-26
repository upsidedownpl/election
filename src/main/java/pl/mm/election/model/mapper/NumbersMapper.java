package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Numbers;
import pl.mm.election.model.to.NumbersTo;

class NumbersMapper extends MapperImpl<NumbersTo, Numbers> implements Mapper<NumbersTo, Numbers>{
	
	NumbersMapper() {
		//
	}
	
	@Override
	protected void mapTransferToPersistent(NumbersTo to, Numbers po) {
		po.setPesel(to.getPesel());
	}
	
	@Override
	protected void mapPersistentToTransfer(Numbers po, NumbersTo to) {
		to.setPesel(to.getPesel());
	}
	
	@Override
	public Class<Numbers> persistentClass() {
		return Numbers.class;
	}
	
	@Override
	public Class<NumbersTo> transferClass() {
		return NumbersTo.class;
	}
	
	@Override
	protected Numbers createPersistent() {
		return new Numbers();
	}
	
	@Override
	protected NumbersTo createTransfer() {
		return new NumbersTo();
	}
}
