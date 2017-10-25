package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Numbers;
import pl.mm.election.model.to.NumbersTo;

public class NumbersMapper {
	
	public static Numbers toPersistent(NumbersTo to) {
		if(to == null) {
			return null;
		}
		
		Numbers po = new Numbers();
		po.setPesel(to.getPesel());
		return po;
	}
	
	public static NumbersTo toTransfer(Numbers po) {
		if(po == null) {
			return null;
		}
		
		NumbersTo to = new NumbersTo();
		to.setPesel(to.getPesel());
		return to;
	}
	
}
